package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.utils.Array;

public class Background {

    private static final int X_LENGTH = 240; // beginn bei 240
    private static final int X_START = -240; // beginn bei -240
    private static final int Y_LENGTH = -240; // beginn bei -240
    private static final int Y_START = 240; // beginn bei 240
    private static final int PICTURE_SIZE = 64; // ist 64


    /**
     * Fills the ArrayList<Tile> tiles
     *
     */


    public void fillBackground(Array<GameObject> gameObjects){

        TileFactory factory = new TileFactory();

        for (int xAxis = X_START; xAxis < X_LENGTH; xAxis += PICTURE_SIZE) {
            for (int yAxis = Y_START; yAxis > Y_LENGTH; yAxis -= PICTURE_SIZE) {

                if (yAxis > -150){

                    Tile tile = factory.createWasser();
                    tile.setPosition(xAxis,yAxis);
                    gameObjects.add(tile);

                } else {

                    Tile tile = factory.createGras();
                    tile.setPosition(xAxis,yAxis);
                    gameObjects.add(tile);

                }}
        }

    }

}
