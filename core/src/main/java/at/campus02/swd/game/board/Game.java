package at.campus02.swd.game.board;

import at.campus02.swd.game.board.FloorObserver.Action;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.*;
import java.util.function.BiConsumer;

public class Game {
    public static final Position INITIAL_PLAYER_POSITION = new Position(0, 0);
    public static final Position INITIAL_FINISH_POSITION = new Position(8, 8);

    private final Set<MovementObserver> movementObservers = new HashSet<>();
    private final Set<FloorObserver> floorObservers = new HashSet<>();
    private final List<ThreatStrategy> threatStrategies = new ArrayList<>();

    private final Board board;

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
        for (int column = 0; column < Board.BOARD_WIDTH; column++)
            for (int row = 0; row < Board.BOARD_HEIGHT; row++)
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
        Position playerPosition = board.getPlayerPosition();
        Position newPosition = new Position(playerPosition.column() + offsetColumn, playerPosition.row() + offsetRow);

        if (!isOnBoard(newPosition))
            return;

        board.setPlayerPosition(newPosition);
        notifyMovementObservers();

        if (hasPlayerWon())
            winGame();
        else if (hasPlayerDied())
            looseLife();
        else
            attackPlayer();
    }

    private void looseLife() {
        System.out.println("You died miserably :(");
    }

    private void winGame() {
        System.out.println("You have won the game!!!!");
    }


    private boolean hasPlayerDied() {
        return board.isDeadly(board.getPlayerPosition());
    }

    private boolean hasPlayerWon() {
        return board.isFinish(board.getPlayerPosition());
    }

    private static boolean isOnBoard(Position position) {
        return position.column() >= 0 && position.column() < Board.BOARD_WIDTH
            && position.row() >= 0 && position.row() < Board.BOARD_HEIGHT;
    }


    public void addThreat(ThreatStrategy damageProvider) {
        threatStrategies.add(damageProvider);
    }

    private void attackPlayer() {
        threatStrategies.forEach(this::examineDamage);
    }

    private void examineDamage(ThreatStrategy damageProvider) {
        damageProvider.wreakHavoc(board)
            .forEach(this::destroyFloorTile);
    }

    private void destroyFloorTile(Position position) {
        // Finish is indestructible
        if (board.isFinish(position))
            return;

        if (!board.isDeadly(position)) {
            board.destroyFloorTile(position);
            notifyFloorObservers(FloorObserver.Action.DESTROY, position);
        }

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
