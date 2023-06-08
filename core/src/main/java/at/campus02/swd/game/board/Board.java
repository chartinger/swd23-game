package at.campus02.swd.game.board;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    final GameObjectPositioner gameObjectPositioner;
    final PlayerFactory playerFactory;
    final TileFactory tileFactory;
    final Set<MovementObserver> movementObservers = new HashSet<>();

    final Player player;
    private final Random randomNumberGenerator = new Random();
    int playerColumn;
    int playerRow;

    final Tile finish;
    int finishColumn;
    int finishRow;

    final Field[][] deathLayer = new Field[10][10];
    final Field[][] floorLayer = new Field[10][10];

    public Board(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory) {
        this.gameObjectPositioner = gameObjectPositioner;
        this.playerFactory = playerFactory;
        this.tileFactory = tileFactory;

        this.player = createPlayer();
        this.playerColumn = 0;
        this.playerRow = 0;

        this.finish = createFinish();
        this.finishColumn = 8;
        this.finishRow = 8;

        createFloorLayer();
        createDeathLayer();

        refresh();
    }

    private void refresh() {
        gameObjectPositioner.setPosition(player, playerColumn, playerRow);
        gameObjectPositioner.setPosition(finish, finishColumn, finishRow);
        refreshLayer(deathLayer);
        refreshLayer(floorLayer);
    }

    private void refreshLayer(Field[][] layer) {
        for (int column = 0; column < layer.length; column++)
            for (int row = 0; row < layer[column].length; row++) {
                Field field = layer[column][row];
                Tile tile = field.tile;
                tile.setVisible(field.exists());
                gameObjectPositioner.setPosition(tile, column, row);
            }
    }

    private Player createPlayer() {
        return playerFactory.create(PlayerType.READY_PLAYER_ONE);
    }

    private Tile createFinish() {
        return tileFactory.create(TileType.FINISH);
    }

    public Array<GameObject> getGameObjects() {
        Array<GameObject> gameObjects = new Array<>();
        gameObjects.addAll(getLayerObjects(deathLayer));
        gameObjects.addAll(getLayerObjects(floorLayer));
        gameObjects.add(finish);
        gameObjects.add(player);
        return gameObjects;
    }

    private Array<GameObject> getLayerObjects(Field[][] layer) {
        Array<GameObject> gameObjects = new Array<>();
        for(Field[] column : layer)
            for (Field field : column)
                gameObjects.add(field.tile);
        return gameObjects;
    }

    private void movePlayer(int offsetColumn, int offsetRow) {
        int newColumn = playerColumn + offsetColumn;
        int newRow = playerRow + offsetRow;

        if (!isOnBoard(newColumn, newRow))
            return;

        playerColumn = newColumn;
        playerRow = newRow;
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
        return !floorLayer[playerColumn][playerRow].exists();
    }

    private void attackPlayer() {
        destroyRandomFloor();
        destroyRandomFloor();
        destroyRandomFloor();
    }

    private void destroyRandomFloor() {
        int column = randomNumberGenerator.nextInt(floorLayer.length);
        int row = randomNumberGenerator.nextInt(floorLayer[column].length);
        while (!isDestructible(column, row)) {
            if (!hasDestructibleFields())
                return;
            column = randomNumberGenerator.nextInt(floorLayer.length);
            row = randomNumberGenerator.nextInt(floorLayer[column].length);
        }

        floorLayer[column][row] = new Field(floorLayer[column][row].tile(), false);
    }

    private boolean hasDestructibleFields() {
        for (int column = 0; column < floorLayer.length; column++)
            for (int row = 0; row < floorLayer[column].length; row++)
                if (isDestructible(column, row))
                    return true;
        return false;
    }

    private boolean isDestructible(int column, int row) {
        return !isPlayerPosition(column, row)
            && !isFinishPosition(column, row)
            && floorLayer[column][row].exists();
    }

    private boolean isFinishPosition(int column, int row) {
        return column == finishColumn && row == finishRow;
    }

    private boolean isPlayerPosition(int column, int row) {
        return column == playerColumn && row == playerRow;
    }

    private boolean hasPlayerWon() {
        return playerColumn == finishColumn && playerRow == finishRow;
    }

    private static boolean isOnBoard(int newColumn, int newRow) {
        return newColumn >= 0 && newColumn <= 9 && newRow >= 0 && newRow <= 9;
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

    public void subscribe(MovementObserver observer) {
        movementObservers.add(observer);
        observer.updatePosition(playerColumn, playerRow);
    }

    private void notifyMovementObservers() {
        movementObservers.forEach(observer -> observer.updatePosition(playerColumn, playerRow));
    }

    private record Field(Tile tile, boolean exists) {
        public Field(Tile tile) {
            this(tile, true);
        }
    }
}
