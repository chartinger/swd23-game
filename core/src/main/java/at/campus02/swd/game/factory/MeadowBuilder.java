package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;

import java.util.ArrayList;

public class MeadowBuilder {
    private ArrayList<GameObject> meadowTiles = new ArrayList<>();


    public MeadowBuilder() {
    }

    public ArrayList<GameObject> placeTile(float x, float y, int meadowHeight, int meadowWidth) {
        for (int row = 0; row < meadowHeight; row++) {
            for (int col = 0; col < meadowWidth; col++) {
                meadowTiles.add(createMeadowTile(col, row, x, y, meadowHeight, meadowWidth));
            }
        }
        return meadowTiles;
    }

    private GameObject createMeadowTile(int x, int y, float tilePosX, float tilePosY, int tileMeadowHeight, int tileMeadowWidth) {

        Factory tileFactory = new TileFactory();

        float tileWidth = 64;
        float tileHeight = 64;

        float tileX = x * tileWidth + tilePosX;


        float tileY = (tileMeadowHeight - y - 1) * tileHeight + tilePosY;
        GameObject meadowTile = tileFactory.create(getTileType(x, y, tileMeadowHeight, tileMeadowWidth), (int) tileX, (int) tileY);

        meadowTile.setPosition(tileX, tileY);

        return meadowTile;
    }


    private Type getTileType(int col, int row, int meadowHeight, int meadowWidth) {
        if (row == 0 && col == 0) {
            return Type.MEADOW_TOP_LEFT;
        } else if (row == 0 && col == meadowWidth - 1) {
            return Type.MEADOW_TOP_RIGHT;
        } else if (row == meadowHeight - 1 && col == 0) {
            return Type.MEADOW_BOTTOM_LEFT;
        } else if (row == meadowHeight - 1 && col == meadowWidth - 1) {
            return Type.MEADOW_BOTTOM_RIGHT;
        } else if (row == 0 && col != meadowWidth - 1) {
            return Type.MEADOW_TOP;
        } else if (row == meadowHeight - 1 && (col != 0 && col != meadowWidth - 1)) {
            return Type.MEADOW_BOTTOM;
        } else if (col == 0 && row != meadowHeight - 1) {
            return Type.MEADOW_LEFT;
        } else if (col == meadowWidth - 1 && (row != 0 && row != meadowHeight - 1)) {
            return Type.MEADOW_RIGHT;
        } else {
            return Type.MEADOW_CENTER;
        }
    }


}
