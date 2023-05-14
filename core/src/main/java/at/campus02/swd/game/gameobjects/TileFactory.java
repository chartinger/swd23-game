package at.campus02.swd.game.gameobjects;

public class TileFactory {
    private final GameObjectPositioner gameObjectPositioner;

    public TileFactory(GameObjectPositioner gameObjectPositioner) {
        this.gameObjectPositioner = gameObjectPositioner;
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
            case SHALLOW_WATER:
                textureId = 188;
                break;
            case WAVY_WATER:
                textureId = 187;
                break;
            case WATER_LINES:
                textureId = 171;
                break;
            default:
                throw new IllegalArgumentException();
        }

        String textureFile = String.format("tiles/mapTile_%03d.png", textureId);
        Tile tile = new Tile(textureFile);
        tile.setPosition(gameObjectPositioner.translateToX(column), gameObjectPositioner.translateToY(line));
        return tile;
    }
}
