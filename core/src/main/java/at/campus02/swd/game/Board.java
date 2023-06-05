package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class Board {
    final GameObjectPositioner gameObjectPositioner;
    final PlayerFactory playerFactory;
    final TileFactory tileFactory;

    final Player player;
    int playerColumn;
    int playerRow;

    final Tile finish;
    int finishColumn;
    int finishRow;

    final Tile[][] deathLayer = new Tile[10][10];
    final Tile[][] floorLayer = new Tile[10][10];

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
        for (int column = 0; column < floorLayer.length; column++)
            for (int row = 0; row < floorLayer[column].length; row++)
                gameObjectPositioner.setPosition(floorLayer[column][row], column, row);
    }

    private Player createPlayer() {
        return playerFactory.create(PlayerType.READY_PLAYER_ONE);
    }

    private Tile createFinish() {
        return tileFactory.create(TileType.FINISH);
    }

    public Array<GameObject> getGameObjects() {
        Array<GameObject> gameObjects = new Array<>();
        for(Tile[] column : floorLayer)
            for (Tile tile : column)
                gameObjects.add(tile);
        gameObjects.add(finish);
        gameObjects.add(player);
        return gameObjects;
    }

    private void movePlayer(int offsetColumn, int offsetRow) {
        int newColumn = playerColumn + offsetColumn;
        int newRow = playerRow + offsetRow;

        if (!isOnBoard(newColumn, newRow))
            return;

        playerColumn = newColumn;
        playerRow = newRow;

        if (hasPlayerWon())
            System.out.println("You have won the game!!!!");

        refresh();
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

    private void fillLayerWithTile(Tile[][] layer, TileType tileType) {
        for (int column = 0; column < layer.length; column++)
            for (int row = 0; row < layer[column].length; row++)
                layer[column][row] = tileFactory.create(tileType);
    }
}
