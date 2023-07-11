package at.campus02.swd.game.board;

import at.campus02.swd.game.game.Board;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.Objects;

public class BasicBoard implements Board {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;

    private final GameObjectPositioner gameObjectPositioner;
    private final TileFactory tileFactory;

    private final Player player;
    private Position playerPosition;

    private final Tile finish;
    private Position finishPosition;

    private final Field[][] deathLayer = new Field[BOARD_WIDTH][BOARD_HEIGHT];
    private final Field[][] floorLayer = new Field[BOARD_WIDTH][BOARD_HEIGHT];

    public BasicBoard(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory, PlayerType player, TileType finish) {
        this.gameObjectPositioner = Objects.requireNonNull(gameObjectPositioner);
        this.tileFactory = Objects.requireNonNull(tileFactory);
        this.player = playerFactory.create(player);
        this.finish = tileFactory.create(finish);

        createDeathLayer();
        createFloorLayer();
    }

    private void createDeathLayer() {
        fillLayerWithTile(deathLayer, TileType.CERTAIN_DEATH);
    }

    private void createFloorLayer() {
        fillLayerWithTile(floorLayer, TileType.FLOOR);
    }

    private void fillLayerWithTile(Field[][] layer, TileType tileType) {
        for (int column = 0; column < getWidth(); column++)
            for (int row = 0; row < getHeight(); row++)
                setTile(layer, new Position(column, row), tileType);
    }

    private void setTile(Field[][] layer, Position position, TileType tileType) {
        layer[position.column()][position.row()] = new Field(tileFactory.create(tileType));
        refreshField(layer, position);
    }


    @Override
    public boolean destroyFloorTile(Position position) {
        return destroyTile(floorLayer, position);
    }

    private boolean destroyTile(Field[][] layer, Position position) {
        checkBounds(position);
        if (!layer[position.column()][position.row()].exists())
            return false;

        layer[position.column()][position.row()] = new Field(layer[position.column()][position.row()].tile(), false);
        refreshField(layer, position);
        return true;
    }

    @Override
    public boolean restoreFloorTile(Position position) {
        return restoreTile(floorLayer, position);
    }

    private boolean restoreTile(Field[][] layer, Position position) {
        checkBounds(position);
        if (layer[position.column()][position.row()].exists())
            return false;

        layer[position.column()][position.row()] = new Field(layer[position.column()][position.row()].tile(), true);
        refreshField(layer, position);
        return true;
    }

    private void refreshField(Field[][] layer, Position position) {
        Field field = layer[position.column()][position.row()];
        field.tile.setVisible(field.exists());
        gameObjectPositioner.setPosition(field.tile(), position);
    }

    private void refreshPlayer() {
        gameObjectPositioner.setPosition(player, playerPosition);
    }

    private void refreshFinish() {
        gameObjectPositioner.setPosition(finish, finishPosition);
    }


    @Override
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


    @Override
    public Position getPlayerPosition() {
        return playerPosition;
    }

    @Override
    public boolean isPlayer(Position position) {
        return playerPosition.equals(position);
    }

    @Override
    public void setPlayerPosition(Position playerPosition) {
        checkBounds(playerPosition);
        this.playerPosition = playerPosition;
        refreshPlayer();
    }

    @Override
    public Position getFinishPosition() {
        return finishPosition;
    }

    @Override
    public boolean isFinish(Position position) {
        return finishPosition.equals(position);
    }

    @Override
    public void setFinishPosition(Position finishPosition) {
        checkBounds(finishPosition);
        this.finishPosition = finishPosition;
        refreshFinish();
    }


    @Override
    public boolean isDeadly(Position position) {
        return isOnBoard(position) && !floorLayer[position.column()][position.row()].exists();
    }

    @Override
    public boolean isDamageable(Position position) {
        return isOnBoard(position) && floorLayer[position.column()][position.row()].exists();
    }

    @Override
    public boolean isRepairable(Position position) {
        return isOnBoard(position) && !floorLayer[position.column()][position.row()].exists();
    }

    @Override
    public boolean isOnBoard(Position position) {
        return position.column() >= 0 && position.column() < getWidth()
            && position.row() >= 0 && position.row() < getHeight();
    }

    @Override
    public int getWidth() {
        return BOARD_WIDTH;
    }

    @Override
    public int getHeight() {
        return BOARD_HEIGHT;
    }


    private void checkBounds(Position position) {
        if (position.column() < 0 || position.column() >= getWidth())
            throw new IllegalArgumentException("Column out of bound: " + position.column() + " not in [" + 0 + ", " + (getWidth() - 1) + "]");
        if (position.row() < 0 || position.row() >= getHeight())
            throw new IllegalArgumentException("Row out of bound: " + position.row() + " not in [" + 0 + ", " + (getHeight() - 1) + "]");
    }


    private record Field(Tile tile, boolean exists) {
        public Field(Tile tile) {
            this(tile, true);
        }
    }
}
