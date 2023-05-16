package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.*;

public class IslandBuilder {
    private final int islandWidth;
    private final int islandHeight;
    private final Type leftTopTileType;
    private final Type topTileType;
    private final Type rightTopTileType;
    private final Type leftBotTileType;
    private final Type botTileType;
    private final Type rightBotTileType;

    private final Type rightTileType;
    private final Type leftTileType;
    private final Type middleTileType;

    private final TileFactory tileFactory;

    public IslandBuilder(
        int islandWidth,
        int islandHeight,
        Type leftTopTileType,
        Type topTileType,
        Type rightTopTileType,
        Type leftBotTileType,
        Type botTileType,
        Type rightBotTileType,
        Type rightType,
        Type leftType,
        Type middleType
    ) {
        this.islandWidth = islandWidth;
        this.islandHeight = islandHeight;
        this.leftTopTileType = leftTopTileType;
        this.rightTopTileType = rightTopTileType;
        this.topTileType = topTileType;
        this.leftBotTileType = leftBotTileType;
        this.rightBotTileType = rightBotTileType;
        this.botTileType = botTileType;
        this.rightTileType = rightType;
        this.leftTileType = leftType;
        this.middleTileType = middleType;



        this.tileFactory = new TileFactory();
    }

    public IslandTile createIslandTile(int x, int y, int islandHeight, float islandPosX, float islandPosY) {

        Type tileType = getTileType(x, y);
        String tilePath = getTilePath(tileType);
        IslandTile islandTile = new IslandTile(tilePath);

        // Definieren der Größe des Tiles
        float tileWidth = 48;
        float tileHeight = 48;

        // Die Position des Tiles wird auf der Basis der x- und y-Werte berechnet
        float tileX = x * tileWidth + islandPosX;

        // Da die y-Koordinate von unten nach oben zählt, invertieren wir sie
        float tileY = (islandHeight - y - 1) * tileHeight + islandPosY;

        // Setzen der Position des Tiles
        islandTile.setPosition(tileX, tileY);

        return islandTile;
    }



    private Type getTileType(int col, int row) {
        if (row == 0 && col == 0) {
            return Type.LEFT_TOP;
        } else if (row == 0 && col == islandWidth - 1) {
            return Type.RIGHT_TOP;
        } else if (row == islandHeight - 1 && col == 0) {
            return Type.LEFT_BOTTOM;
        } else if (row == islandHeight - 1 && col == islandWidth - 1) {
            return Type.RIGHT_BOTTOM;
        } else if (row == 0 && (col != 0 && col != islandWidth - 1)) {
            return Type.TOP;
        } else if (row == islandHeight - 1 && (col != 0 && col != islandWidth - 1)) {
            return Type.BOTTOM;
        } else if (col == 0 && (row != 0 && row != islandHeight - 1)) {
            return Type.LEFT;
        } else if (col == islandWidth - 1 && (row != 0 && row != islandHeight - 1)) {
            return Type.RIGHT;
        } else {
            return Type.MIDDLE; // Default Tile-Typ für das Innere der Insel
        }
    }

    private String getTilePath(Type tileType) {
        switch (tileType) {
            case LEFT_TOP:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterlefttop1.png";
            case TOP:
                return "tiles/tropical/Terrain/Desertwater/Desertwatertop1.png";
            case RIGHT_TOP:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterrighttop1.png";
            case LEFT_BOTTOM:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterleftbot1.png";
            case BOTTOM:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterbot1.png";
            case RIGHT_BOTTOM:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterrightbot1.png";
            case RIGHT:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterright1.png";
            case LEFT:
                return "tiles/tropical/Terrain/Desertwater/Desertwaterleft1.png";
            case MIDDLE:
                return "tiles/tropical/Terrain/Desert/Desert1.png";
            default:
                return "tiles/tropical/Terrain/Desert/Desert1.png"; // Pfad für den Standard-Tile-Typ (z. B. Wasser)
        }


    }


}
