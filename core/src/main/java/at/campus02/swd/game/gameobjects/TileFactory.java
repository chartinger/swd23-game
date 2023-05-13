package at.campus02.swd.game.gameobjects;

public class TileFactory {
    private final int viewportWidth;
    private final int viewportHeight;
    private final int tileSize;

    /**
     * Creates a factory for tiles on the board
     * @param viewportWidth width in pixels
     * @param viewportHeight height in pixels
     * @param tileSize edge length of (square) tile in pixels
     */

    public TileFactory(int viewportWidth, int viewportHeight, int tileSize) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.tileSize = tileSize;
    }

    /**
     * Creates a new tile
     * @return a new tile to be drawn at the given position on the board
     */
    public Tile create(TileType type, int column, int line) {
        int textureId;
        switch (type) {
            case LEFT_TOP:
                textureId = 6;
                break;
            case TOP:
                textureId = 7;
                break;
            case RIGHT_TOP:
                textureId = 8;
                break;
            case LEFT:
                textureId = 21;
                break;
            case CENTER:
                textureId = 22;
                break;
            case RIGHT:
                textureId = 23;
                break;
            case LEFT_BOTTOM:
                textureId = 51;
                break;
            case RIGHT_BOTTOM:
                textureId = 53;
                break;
            case BOTTOM:
                textureId = 52;
                break;
            default:
                throw new IllegalArgumentException();
        }

        String textureFile = String.format("tiles/mapTile_%03d.png", textureId);
        Tile tile = new Tile(textureFile);
        tile.setPosition(translateToX(column), translateToY(line));
        return tile;
    }

    private int translateToX(int column) {
        int offsetX = viewportWidth / 2;
        return column * tileSize - offsetX;
    }

    private int translateToY(int line) {
        int offsetY = viewportHeight / 2;
        // (line + 1) because we need to refer to the location of the *bottom* left corner of the image
        // return the negativ value because on the cartesian coordinate system our lines grow in the negative direction
        return -((line + 1) * tileSize - offsetY);
    }
}
