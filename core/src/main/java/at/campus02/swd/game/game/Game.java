package at.campus02.swd.game.game;

import at.campus02.swd.game.game.FloorObserver.Action;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class Game {
    private static final Position INITIAL_PLAYER_POSITION = new Position(0, 0);
    private static final Position INITIAL_FINISH_POSITION = new Position(8, 8);
    private static final int DEFAULT_BUDGET = 3;

    private final Set<BudgetObserver> budgetObservers = new HashSet<>();
    private final Set<MovementObserver> movementObservers = new HashSet<>();
    private final Set<FloorObserver> floorObservers = new HashSet<>();
    private final List<ThreatStrategy> threatStrategies = new ArrayList<>();
    private ThreatStrategy activeStrategy = null;
    private final Map<DefenceType, DefenceStrategy> defenceStrategies = new HashMap<>();

    private final Board board;
    private boolean isGameOver = false;
    private int budget = DEFAULT_BUDGET;

    public Game(Board board, int budget) {
        this(board);
        this.budget = budget;
    }

    public Game(Board board) {
        this.board = Objects.requireNonNull(board);
        board.setPlayerPosition(INITIAL_PLAYER_POSITION);
        board.setFinishPosition(INITIAL_FINISH_POSITION);
    }


    public void moveNorth() {
        movePlayer(0, -1);
    }
    public void moveSouth() {
        movePlayer(0, 1);
    }
    public void moveWest() {
        movePlayer(-1, 0);
    }
    public void moveEast() {
        movePlayer(1, 0);
    }

    private void movePlayer(int offsetColumn, int offsetRow) {
        if (isGameOver)
            return;

        Position playerPosition = board.getPlayerPosition();
        Position newPosition = new Position(playerPosition.column() + offsetColumn, playerPosition.row() + offsetRow);

        if (!board.isOnBoard(newPosition))
            return;

        board.setPlayerPosition(newPosition);
        notifyMovementObservers();

        updateGameStatus();
        if (!isGameOver)
            attackPlayer();
    }

    private void updateGameStatus() {
        if (hasPlayerWon())
            winGame();
        else if (hasPlayerDied())
            looseLife();
    }

    private void looseLife() {
        System.out.println("You died miserably :(");
        isGameOver = true;
    }

    private void winGame() {
        System.out.println("You have won the game!!!!");
        isGameOver = true;
    }

    private boolean pay(int amount) {
        if (budget < amount)
            return false;

        budget -= amount;
        notifyBudgetObservers();
        return true;
    }


    private boolean hasPlayerDied() {
        return board.isDeadly(board.getPlayerPosition());
    }

    private boolean hasPlayerWon() {
        return board.isFinish(board.getPlayerPosition());
    }


    public void addThreat(ThreatStrategy.Builder damageProvider) {
        threatStrategies.add(damageProvider.forBoard(board));
    }

    public void setActiveThreat(int strategyIndex) {
        if (strategyIndex >= threatStrategies.size())
            return;
        activeStrategy = threatStrategies.get(strategyIndex);
    }

    public void activateAllThreats() {
        activeStrategy = null;
    }

    private void attackPlayer() {
        if (activeStrategy != null)
            examineDamage(activeStrategy);
        else
            threatStrategies.forEach(this::examineDamage);
    }

    private void examineDamage(ThreatStrategy damageProvider) {
        damageProvider.wreakHavoc()
            .forEach(this::destroyFloorTile);
    }

    private void destroyFloorTile(Position position) {
        // Finish is indestructible
        if (board.isFinish(position))
            return;

        if (board.destroyFloorTile(position))
            notifyFloorObservers(FloorObserver.Action.DESTROY, position);

        if (board.isPlayer(position))
            looseLife();
    }


    public void setDefence(DefenceType defenceType, DefenceStrategy.Builder landscapeReviver) {
        defenceStrategies.put(defenceType, landscapeReviver.forBoard(board));
    }

    public void defend(DefenceType defenceType) {
        if (!isGameOver)
            examineRepairs(defenceStrategies.getOrDefault(defenceType, AidPack::empty));
    }

    private void examineRepairs(DefenceStrategy landscapeReviver) {
        AidPack aidPack = landscapeReviver.restoreChaos();
        if (pay(aidPack.cost()))
            aidPack.repairs()
                .forEach(this::restoreFloorTile);
    }

    private void restoreFloorTile(Position position) {
        if (board.restoreFloorTile(position))
            notifyFloorObservers(Action.CREATE, position);
    }


    public void subscribeForBudget(BudgetObserver observer) {
        budgetObservers.add(Objects.requireNonNull(observer));
        observer.updateBudget(budget);
    }

    private void notifyBudgetObservers() {
        budgetObservers.forEach(observer -> observer.updateBudget(budget));
    }


    public void subscribeForMovement(MovementObserver observer) {
        movementObservers.add(Objects.requireNonNull(observer));
        observer.updatePosition(board.getPlayerPosition());
    }

    private void notifyMovementObservers() {
        movementObservers.forEach(observer -> observer.updatePosition(board.getPlayerPosition()));
    }


    public void subscribeForFloorActions(FloorObserver observer) {
        floorObservers.add(Objects.requireNonNull(observer));
    }

    private void notifyFloorObservers(Action action, Position position) {
        floorObservers.forEach(observer -> observer.updateFloor(action, position));
    }
}
