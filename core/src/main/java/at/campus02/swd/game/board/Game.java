package at.campus02.swd.game.board;

import at.campus02.swd.game.board.FloorObserver.Action;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.threats.NoDamage;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.*;
import java.util.function.BiConsumer;

public class Game {
    private static final Position INITIAL_PLAYER_POSITION = new Position(0, 0);
    private static final Position INITIAL_FINISH_POSITION = new Position(8, 8);

    private final Set<MovementObserver> movementObservers = new HashSet<>();
    private final Set<FloorObserver> floorObservers = new HashSet<>();
    private final List<ThreatStrategy> threatStrategies = new ArrayList<>();
    private final ThreatStrategy alternatingStrategy = new NoDamage();

    private final Board board;
    private int currentRound = 1;
    private boolean isGameOver = false;

    public Game(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory) {
        board = new Board(
            gameObjectPositioner,
            playerFactory,
            tileFactory,
            PlayerType.READY_PLAYER_ONE, INITIAL_PLAYER_POSITION,
            TileType.FINISH, INITIAL_FINISH_POSITION
        );

        createFloorLayer();
        createDeathLayer();
    }

    private void createDeathLayer() {
        fillLayerWithTile(board::setDeathTile, TileType.CERTAIN_DEATH);
    }

    private void createFloorLayer() {
        fillLayerWithTile(board::setFloorTile, TileType.FLOOR);
    }

    private void fillLayerWithTile(BiConsumer<Position, TileType> tileSetter, TileType tileType) {
        for (int column = 0; column < board.getWidth(); column++)
            for (int row = 0; row < board.getHeight(); row++)
                tileSetter.accept(new Position(column, row), tileType);
    }


    public Array<GameObject> getGameObjects() {
        return board.getGameObjects();
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

        endRound();
    }

    private void updateGameStatus() {
        if (hasPlayerWon())
            winGame();
        else if (hasPlayerDied())
            looseLife();
    }

    private void endRound() {
        currentRound++;
    }

    private void looseLife() {
        System.out.println("You died miserably :(");
        isGameOver = true;
    }

    private void winGame() {
        System.out.println("You have won the game!!!!");
        isGameOver = true;
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

    private void attackPlayer() {
        if (currentRound % 2 == 0)
            examineDamage(alternatingStrategy);
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


    public void subscribe(MovementObserver observer) {
        movementObservers.add(observer);
        observer.updatePosition(board.getPlayerPosition());
    }

    private void notifyMovementObservers() {
        movementObservers.forEach(observer -> observer.updatePosition(board.getPlayerPosition()));
    }


    public void subscribe(FloorObserver observer) {
        floorObservers.add(observer);
    }

    private void notifyFloorObservers(Action action, Position position) {
        floorObservers.forEach(observer -> observer.updateFloor(action, position));
    }
}
