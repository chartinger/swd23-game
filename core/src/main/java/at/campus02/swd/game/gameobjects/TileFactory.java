package at.campus02.swd.game.gameobjects;

import java.util.Objects;

public class TileFactory implements GameObjectFactory<TileType> {
    @Override
    public Tile create(TileType type) {
        int textureId = switch (Objects.requireNonNull(type)) {
            case TOP_LEFT -> 6;
            case TOP -> 7;
            case TOP_RIGHT -> 8;
            case LEFT -> 21;
            case CENTER -> 22;
            case RIGHT -> 23;
            case BOTTOM_LEFT -> 51;
            case BOTTOM -> 52;
            case BOTTOM_RIGHT -> 53;
            case WATER -> 187;
        };

        String textureFile = String.format("tiles/mapTile_%03d.png", textureId);
        return new Tile(textureFile);
    }
}
