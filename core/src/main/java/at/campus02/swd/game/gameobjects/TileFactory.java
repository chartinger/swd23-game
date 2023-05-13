package at.campus02.swd.game.gameobjects;

public class TileFactory {
    private final TilePositioner tilePositioner;

    public TileFactory(TilePositioner tilePositioner) {
        this.tilePositioner = tilePositioner;
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
        tile.setPosition(tilePositioner.translateToX(column), tilePositioner.translateToY(line));
        return tile;
    }
}
