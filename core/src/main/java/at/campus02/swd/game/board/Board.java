package at.campus02.swd.game.board;

import at.campus02.swd.game.board.FloorObserver.Action;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    private final GameObjectPositioner gameObjectPositioner;
    private final PlayerFactory playerFactory;
    private final TileFactory tileFactory;

    private final Set<MovementObserver> movementObservers = new HashSet<>();
    private final Set<FloorObserver> floorObservers = new HashSet<>();

    private final Random randomNumberGenerator = new Random();

    private final Player player;
    private Position playerPosition;

    private final Tile finish;
    private final Position finishPosition;

    private final Field[][] deathLayer = new Field[10][10];
    private final Field[][] floorLayer = new Field[10][10];

    public Board(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory) {
        this.gameObjectPositioner = gameObjectPositioner;
        this.playerFactory = playerFactory;
        this.tileFactory = tileFactory;

        this.player = createPlayer();
        this.playerPosition = new Position(0, 0);

        this.finish = createFinish();
        this.finishPosition = new Position(8, 8);

        createFloorLayer();
        createDeathLayer();

        refresh();
    }

    private Player createPlayer() {
        return playerFactory.create(PlayerType.READY_PLAYER_ONE);
    }

    private Tile createFinish() {
        return tileFactory.create(TileType.FINISH);
    }

    private void createDeathLayer() {
        fillLayerWithTile(deathLayer, TileType.CERTAIN_DEATH);
    }

    private void createFloorLayer() {
        fillLayerWithTile(floorLayer, TileType.FLOOR);
    }

    private void fillLayerWithTile(Field[][] layer, TileType tileType) {
        for (int column = 0; column < layer.length; column++)
            for (int row = 0; row < layer[column].length; row++)
                layer[column][row] = new Field(tileFactory.create(tileType));
    }


    private void refresh() {
        refreshPlayer();
        refreshFinish();
        refreshLayer(deathLayer);
        refreshLayer(floorLayer);
    }

    private void refreshPlayer() {
        gameObjectPositioner.setPosition(player, playerPosition);
    }

    private void refreshFinish() {
        gameObjectPositioner.setPosition(finish, finishPosition);
    }

    private void refreshLayer(Field[][] layer) {
        for (int column = 0; column < layer.length; column++)
            for (int row = 0; row < layer[column].length; row++) {
                Field field = layer[column][row];
                field.tile.setVisible(field.exists());
                gameObjectPositioner.setPosition(field.tile, new Position(column, row));
            }
    }


    public Array<GameObject> getGameObjects() {
        Array<GameObject> gameObjects = new Array<>();
        gameObjects.addAll(getGameObjects(deathLayer));
        gameObjects.addAll(getGameObjects(floorLayer));
        gameObjects.add(finish);
        gameObjects.add(player);
        return gameObjects;
    }

    private Array<GameObject> getGameObjects(Field[][] layer) {
        Array<GameObject> gameObjects = new Array<>();
        for(Field[] column : layer)
            for (Field field : column)
                gameObjects.add(field.tile);
        return gameObjects;
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
        Position newPosition = new Position(playerPosition.column() + offsetColumn, playerPosition.row() + offsetRow);

        if (!isOnBoard(newPosition))
            return;

        playerPosition = newPosition;
        notifyMovementObservers();

        if (hasPlayerWon())
            System.out.println("You have won the game!!!!");
        else if (hasPlayerDied())
            System.out.println("You died miserably :(");
        else
            attackPlayer();

        refresh();
    }

    private boolean hasPlayerDied() {
        return !floorLayer[playerPosition.column()][playerPosition.row()].exists();
    }

    private boolean isDestructible(Position position) {
        return !isPlayerPosition(position)
            && !isFinishPosition(position)
            && floorLayer[position.column()][position.row()].exists();
    }

    private boolean isFinishPosition(Position position) {
        return finishPosition.equals(position);
    }

    private boolean isPlayerPosition(Position position) {
        return playerPosition.equals(position);
    }

    private boolean hasPlayerWon() {
        return isFinishPosition(playerPosition);
    }

    private static boolean isOnBoard(Position position) {
        return position.column() >= 0 && position.column() <= 9 && position.row() >= 0 && position.row() <= 9;
    }

    private boolean hasDestructibleFields() {
        for (int column = 0; column < floorLayer.length; column++)
            for (int row = 0; row < floorLayer[column].length; row++)
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

        floorLayer[position.column()][position.row()] = new Field(floorLayer[position.column()][position.row()].tile(), false);
        notifyFloorObservers(Action.DESTROY, position);
    }

    private Position getRandomPosition() {
        int column = randomNumberGenerator.nextInt(floorLayer.length);
        int row = randomNumberGenerator.nextInt(floorLayer[column].length);
        return new Position(column, row);
    }


    public void subscribe(MovementObserver observer) {
        movementObservers.add(observer);
        observer.updatePosition(playerPosition);
    }

    private void notifyMovementObservers() {
        movementObservers.forEach(observer -> observer.updatePosition(playerPosition));
    }


    public void subscribe(FloorObserver observer) {
        floorObservers.add(observer);
    }

    private void notifyFloorObservers(Action action, Position position) {
        floorObservers.forEach(observer -> observer.updateFloor(action, position));
    }


    private record Field(Tile tile, boolean exists) {
        public Field(Tile tile) {
            this(tile, true);
        }
    }
}
