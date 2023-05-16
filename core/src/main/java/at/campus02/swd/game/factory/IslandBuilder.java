package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.*;

import java.util.ArrayList;

public class IslandBuilder {
    private ArrayList<GameObject> islandTiles = new ArrayList<>();


    public IslandBuilder() {
    }

    public ArrayList<GameObject> placeIsland(float x, float y, int islandHeight, int islandWidth) {
        for (int row = 0; row < islandWidth; row++) {
            for (int col = 0; col < islandHeight; col++) {
                int tileX = col;
                int tileY = row;

                islandTiles.add(createIslandTile(tileX, tileY, x, y, islandHeight, islandWidth));
            }
        }
        return islandTiles;
    }

    private GameObject createIslandTile(int x, int y, float islandPosX, float islandPosY, int islandHeight, int islandWidth) {

        Factory tileFactory = new TileFactory();

        // Definieren der Größe der Tiles
        float tileWidth = 48;
        float tileHeight = 48;

        // Die Position des Tiles wird auf der Basis der x- und y-Werte berechnet
        float tileX = x * tileWidth + islandPosX;

        // Da die y-Koordinate von unten nach oben zählt, invertieren wir sie
        float tileY = (islandHeight - y - 1) * tileHeight + islandPosY;
        GameObject islandTile = tileFactory.create(getTileType(x, y, islandHeight, islandWidth), (int) tileX, (int) tileY);
        // Setzen der Position des Tiles
        islandTile.setPosition(tileX, tileY);

        return islandTile;
    }


    private Type getTileType(int col, int row, int islandHeight, int islandWidth) {
        if (row == 0 && col == 0) {
            return Type.ISLAND_TOP_LEFT;
        } else if (row == 0 && col == islandWidth - 1) {
            return Type.ISLAND_TOP_RIGHT;
        } else if (row == islandHeight - 1 && col == 0) {
            return Type.ISLAND_BOTTOM_LEFT;
        } else if (row == islandHeight - 1 && col == islandWidth - 1) {
            return Type.ISLAND_BOTTOM_RIGHT;
        } else if (row == 0 && (col != 0 && col != islandWidth - 1)) {
            return Type.ISLAND_TOP;
        } else if (row == islandHeight - 1 && (col != 0 && col != islandWidth - 1)) {
            return Type.ISLAND_BOTTOM;
        } else if (col == 0 && (row != 0 && row != islandHeight - 1)) {
            return Type.ISLAND_LEFT;
        } else if (col == islandWidth - 1 && (row != 0 && row != islandHeight - 1)) {
            return Type.ISLAND_RIGHT;
        } else {
            return Type.ISLAND_CENTER; // Default Tile-Typ für das Innere der Insel
        }
    }


}
