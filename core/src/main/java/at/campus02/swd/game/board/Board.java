package at.campus02.swd.game.board;

import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

public class Board implements BoardView {
    public static final int BOARD_WIDTH = 10;
    public static final int BOARD_HEIGHT = 10;

    private final GameObjectPositioner gameObjectPositioner;
    private final TileFactory tileFactory;

    private final Player player;
    private Position playerPosition;

    private final Tile finish;
    private final Position finishPosition;

    private final Field[][] deathLayer = new Field[BOARD_WIDTH][BOARD_HEIGHT];
    private final Field[][] floorLayer = new Field[BOARD_WIDTH][BOARD_HEIGHT];

    public Board(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory, PlayerType player, Position playerPosition, TileType finish, Position finishPosition) {
        checkBounds(playerPosition);
        checkBounds(finishPosition);

        this.gameObjectPositioner = gameObjectPositioner;
        this.tileFactory = tileFactory;
        this.player = playerFactory.create(player);
        this.playerPosition = playerPosition;
        this.finish = tileFactory.create(finish);
        this.finishPosition = finishPosition;

        refreshPlayer();
        refreshFinish();
    }


    public void setDeathTile(Position position, TileType tileType) {
        setTile(deathLayer, position, tileType);
    }

    public void setFloorTile(Position position, TileType tileType) {
        setTile(floorLayer, position, tileType);
    }

    private void setTile(Field[][] layer, Position position, TileType tileType) {
        checkBounds(position);
        layer[position.column()][position.row()] = new Field(tileFactory.create(tileType));
        refreshField(layer, position);
    }

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
    public boolean isDeadly(Position position) {
        return !floorLayer[position.column()][position.row()].exists();
    }


    private void checkBounds(Position position) {
        if (position.column() < 0 || position.column() >= BOARD_WIDTH)
            throw new IllegalArgumentException("Column out of bound: " + position.column() + " not in [" + 0 + ", " + (BOARD_WIDTH - 1) + "]");
        if (position.row() < 0 || position.row() >= BOARD_HEIGHT)
            throw new IllegalArgumentException("Row out of bound: " + position.row() + " not in [" + 0 + ", " + (BOARD_HEIGHT - 1) + "]");
    }


    private record Field(Tile tile, boolean exists) {
        public Field(Tile tile) {
            this(tile, true);
        }
    }
}
