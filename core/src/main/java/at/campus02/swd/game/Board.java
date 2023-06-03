package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.utils.Array;

public class Board {
    GameObjectPositioner gameObjectPositioner;
    PlayerFactory playerFactory;
    TileFactory tileFactory;

//    Tile[][] floor = new Tile[10][10];
    Player player;
    int playerX;
    int playerY;

    Tile finish;
    int finishX;
    int finishY;

    public Board(GameObjectPositioner gameObjectPositioner, PlayerFactory playerFactory, TileFactory tileFactory) {
        this.gameObjectPositioner = gameObjectPositioner;
        this.playerFactory = playerFactory;
        this.tileFactory = tileFactory;

        this.player = createPlayer();
        this.playerX = 0;
        this.playerY = 0;

        this.finish = createFinish();
        this.finishX = 8;
        this.finishY = 8;

        refresh();
    }

    private void refresh() {
        gameObjectPositioner.setPosition(player, playerX, playerY);
        gameObjectPositioner.setPosition(finish, finishX, finishY);
    }

    private Player createPlayer() {
        return playerFactory.create(PlayerType.READY_PLAYER_ONE);
    }

    private Tile createFinish() {
        return tileFactory.create(TileType.FINISH);
    }

    public Array<GameObject> getGameObjects() {
        Array<GameObject> gameObjects = new Array<>();
//        for(Tile[] column : floor)
//            for(Tile tile : column)
//                gameObjects.add(tile);
        gameObjects.add(finish);
        gameObjects.add(player);
        return gameObjects;
    }

    private void movePlayer(int offsetColumn, int offsetRow) {
        int newCol = playerX + offsetColumn;
        int newRow = playerY + offsetRow;

        if (newCol < 0 || newCol > 9 || newRow < 0 || newRow > 9)
            return;

        playerX = newCol;
        playerY = newRow;

        refresh();
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
}
