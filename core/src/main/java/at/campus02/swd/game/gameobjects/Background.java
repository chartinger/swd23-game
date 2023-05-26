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

                if (yAxis > 50){

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



    /*public ArrayList<Tile> fillBackground() {

        TileFactory factory = new TileFactory();

        for (int i = 0; i < 30; i++) {

            Tile tile = factory.create();
            tiles.add(tile);
        }

        return tiles;
    }*/



   /* public void setPosition(Background background){

        getTiles();

        for (Tile tile : tiles) {
            tile.setPosition(0,0);}

        for (int rows = -240; rows < 240; rows += 64) {
            for (int columns = 240; columns < -240; columns -= 64 ) {
            }
         }

        Tile tile = tiles.get(0);

    }*/


}
