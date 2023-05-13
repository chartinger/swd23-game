package at.campus02.swd.game.gameobjects;

import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;

public class Background {

    private ArrayList<Tile> tiles = new ArrayList<>();

    private static int ONE_SIDE_LENGTH = 240;
    private static int IMAGE_SIZE = 64;

    /**
     * Fills the ArrayList<Tile> tiles
     *
     */

    // WIE LANG MUSS ARRAY LIST SEIN???
    // BACKGROUND MUSS WISSEN WIE VIEL TILES ER BRAUCHT

    private void createLayout(){
        /**
         * layoutForTiles => Integer --> Index of Tile
         * layoutForTiles => Integer[][] --> x and y positions on grid
         */

        HashMap<Integer, Integer[][] > layoutForTiles = new HashMap<>();

    }

    public int getLength(){
        return ONE_SIDE_LENGTH;
    }

    public int getPictureSide(){
        return IMAGE_SIZE;
    }


    private Integer[][] createGrid(){

        int elements = ONE_SIDE_LENGTH/IMAGE_SIZE;

        Integer[][] grid = new Integer[elements][elements];

        return grid;
    }

    public ArrayList<Tile> fillBackground() {

        TileFactory factory = new TileFactory();

        for (int i = 0; i < 30; i++) {

            Tile tile = factory.create();
            tiles.add(tile);
        }

        return tiles;
    }

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

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Background() {
        createGrid();
    }
}
