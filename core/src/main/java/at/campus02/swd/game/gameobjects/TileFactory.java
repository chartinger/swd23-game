package at.campus02.swd.game.gameobjects;

public class TileFactory {
    public Tile create(TileType type) {
        int textureId;
        switch (type) {
            case TOP_LEFT:
                textureId = 6;
                break;
            case TOP:
                textureId = 7;
                break;
            case TOP_RIGHT:
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
            case BOTTOM_LEFT:
                textureId = 51;
                break;
            case BOTTOM_RIGHT:
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
        return new Tile(textureFile);
    }
}
