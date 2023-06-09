package at.campus02.swd.game.board;

import at.campus02.swd.game.board.FloorObserver.Action;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class Game {
    public static final Position INITIAL_PLAYER_POSITION = new Position(0, 0);
    public static final Position INITIAL_FINISH_POSITION = new Position(8, 8);

    private final Set<MovementObserver> movementObservers = new HashSet<>();
    private final Set<FloorObserver> floorObservers = new HashSet<>();

    private final Random randomNumberGenerator = new Random();

    private final GameBoard board;

    public Game(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory) {
        board = new GameBoard(
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
        for (int column = 0; column < GameBoard.BOARD_WIDTH; column++)
            for (int row = 0; row < GameBoard.BOARD_HEIGHT; row++)
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
            System.out.println("You have won the game!!!!");
        else if (hasPlayerDied())
            System.out.println("You died miserably :(");
        else
            attackPlayer();
    }

    private boolean hasPlayerDied() {
        return board.isDeadly(board.getPlayerPosition());
    }

    private boolean isDestructible(Position position) {
        return !isPlayerPosition(position)
            && !isFinishPosition(position)
            && !board.isDeadly(position);
    }

    private boolean isFinishPosition(Position position) {
        return board.getFinishPosition().equals(position);
    }

    private boolean isPlayerPosition(Position position) {
        return board.getPlayerPosition().equals(position);
    }

    private boolean hasPlayerWon() {
        return isFinishPosition(board.getPlayerPosition());
    }

    private static boolean isOnBoard(Position position) {
        return position.column() >= 0 && position.column() < GameBoard.BOARD_WIDTH
            && position.row() >= 0 && position.row() < GameBoard.BOARD_HEIGHT;
    }

    private boolean hasDestructibleFields() {
        for (int column = 0; column < GameBoard.BOARD_WIDTH; column++)
            for (int row = 0; row < GameBoard.BOARD_HEIGHT; row++)
                if (isDestructible(new Position(column, row)))
                    return true;
        return false;
    }


    private void attackPlayer() {
        destroyRandomFloor();
        destroyRandomFloor();
        destroyRandomFloor();
    }

    private void destroyRandomFloor() {
        if (!hasDestructibleFields())
            return;

        Position position = getRandomPosition();
        while (!isDestructible(position)) {
            position = getRandomPosition();
        }

        board.destroyFloorTile(position);
        notifyFloorObservers(Action.DESTROY, position);
    }

    private Position getRandomPosition() {
        int column = randomNumberGenerator.nextInt(GameBoard.BOARD_WIDTH);
        int row = randomNumberGenerator.nextInt(GameBoard.BOARD_HEIGHT);
        return new Position(column, row);
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
