package at.campus02.swd.game.board;

import at.campus02.swd.game.game.Board;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.util.GameObjectPositioner;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

import java.util.Objects;

public class MultiDamageBoard implements Board {
    private static final int BOARD_WIDTH = 10;
    private static final int BOARD_HEIGHT = 10;

    private final GameObjectPositioner gameObjectPositioner;
    private final TileFactory tileFactory;

    private final Player player;
    private Position playerPosition;

    private final Tile finish;
    private Position finishPosition;

    private final Field[][] gameBoard = new Field[BOARD_WIDTH][BOARD_HEIGHT];

    public MultiDamageBoard(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory, PlayerType player, TileType finish) {
        this.gameObjectPositioner = Objects.requireNonNull(gameObjectPositioner);
        this.tileFactory = Objects.requireNonNull(tileFactory);
        this.player = playerFactory.create(player);
        this.finish = tileFactory.create(finish);

        createGameBoard();
    }

    private void createGameBoard() {
        for (int column = 0; column < getWidth(); column++)
            for (int row = 0; row < getHeight(); row++)
                setTiles(new Position(column, row), TileType.FLOOR, TileType.DAMAGED_FLOOR, TileType.CERTAIN_DEATH);
    }

    private void setTiles(Position position, TileType tileType, TileType damagedType, TileType backgroundType) {
        gameBoard[position.column()][position.row()] = new Field(
            tileFactory.create(tileType),
            tileFactory.create(damagedType),
            tileFactory.create(backgroundType)
        );
        refreshField(position);
    }


    @Override
    public boolean destroyFloorTile(Position position) {
        checkBounds(position);
        Field field = gameBoard[position.column()][position.row()];
        if (field.isBroken())
            return false;

        gameBoard[position.column()][position.row()] = field.damage();
        refreshField(position);
        return true;
    }

    @Override
    public boolean restoreFloorTile(Position position) {
        checkBounds(position);
        Field field = this.gameBoard[position.column()][position.row()];
        if (field.isIntact())
            return false;

        gameBoard[position.column()][position.row()] = field.repair();
        refreshField(position);
        return true;
    }

    private void refreshField(Position position) {
        Field field = gameBoard[position.column()][position.row()];
        field.intactTile().setVisible(field.isIntact());
        field.damagedTile().setVisible(!field.isIntact() && !field.isBroken());
        field.backgroundTile().setVisible(true);
        gameObjectPositioner.setPosition(field.intactTile(), position);
        gameObjectPositioner.setPosition(field.damagedTile(), position);
        gameObjectPositioner.setPosition(field.backgroundTile(), position);
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
        gameObjects.addAll(getGameObjectsForGameBoard());
        gameObjects.add(finish);
        gameObjects.add(player);
        return gameObjects;
    }

    private Array<GameObject> getGameObjectsForGameBoard() {
        Array<GameObject> gameObjects = new Array<>();
        for(Field[] column : gameBoard)
            for (Field field : column) {
                gameObjects.add(field.backgroundTile());
                gameObjects.add(field.damagedTile());
                gameObjects.add(field.intactTile());
            }
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
        return isOnBoard(position) && gameBoard[position.column()][position.row()].isBroken();
    }

    @Override
    public boolean isDamageable(Position position) {
        return isOnBoard(position) && !gameBoard[position.column()][position.row()].isBroken();
    }

    @Override
    public boolean isRepairable(Position position) {
        return isOnBoard(position) && !gameBoard[position.column()][position.row()].isIntact();
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


    private record Field(Tile intactTile, Tile damagedTile, Tile backgroundTile, State state) {
        public Field(Tile intact, Tile damaged, Tile background) {
            this(intact, damaged, background, State.INTACT);
        }

        public boolean isBroken() {
            return state == State.BROKEN;
        }
        public boolean isIntact() {
            return state == State.INTACT;
        }
        public Field damage() {
            return new Field(intactTile, damagedTile, backgroundTile, isIntact() ? State.DAMAGED : State.BROKEN);
        }
        public Field repair() {
            return new Field(intactTile, damagedTile, backgroundTile, isBroken() ? State.DAMAGED : State.INTACT);
        }

        public enum State { INTACT, DAMAGED, BROKEN }
    }
}
