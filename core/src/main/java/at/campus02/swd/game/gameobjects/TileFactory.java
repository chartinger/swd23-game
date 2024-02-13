package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;
import java.util.Objects;

public class TileFactory implements GameObjectFactory<TileType> {
    private static final Map<TileType, Integer> TEXTURE_IDS = Map.of(
        TileType.TOP_LEFT, 61,
        TileType.TOP, 62,
        TileType.TOP_RIGHT, 63,
        TileType.LEFT, 76,
        TileType.FLOOR, 77,
        TileType.RIGHT, 78,
        TileType.CERTAIN_DEATH, 187,
        TileType.FINISH, 95
    );

    private final AssetRepository assetRepository;

    public TileFactory(AssetRepository assetRepository) {
        this.assetRepository = Objects.requireNonNull(assetRepository);
        for (Integer textureId : TEXTURE_IDS.values())
            this.assetRepository.loadTexture(getTextureFilename(textureId));
    }

    @Override
    public Tile create(TileType type) {
        return switch (type) {
            case TOP_LEFT, TOP, TOP_RIGHT, LEFT, FLOOR, RIGHT, CERTAIN_DEATH, FINISH -> createSimpleTile(getTextureId(type));
            case DAMAGED_FLOOR -> createCompositeTile(TileType.TOP_LEFT, TileType.TOP_RIGHT, TileType.BOTTOM_LEFT, TileType.BOTTOM_RIGHT);
            case BOTTOM_LEFT -> rotate(create(TileType.TOP_RIGHT));
            case BOTTOM -> rotate(create(TileType.TOP));
            case BOTTOM_RIGHT -> rotate(create(TileType.TOP_LEFT));
        };
    }

    private static int getTextureId(TileType tileType) {
        return Objects.requireNonNull(TEXTURE_IDS.get(tileType), "Missing texture id for tile type: " + tileType);
    }

    private Tile createCompositeTile(TileType northWestType, TileType northEastType, TileType southWestType, TileType southEastType) {
        return new ComposedTile(
            create(northWestType),
            create(northEastType),
            create(southWestType),
            create(southEastType)
        );
    }

    private Texture getTexture(int textureId) {
        String textureFile = getTextureFilename(textureId);
        return assetRepository.getTexture(textureFile);
    }

    private SimpleTile createSimpleTile(int textureId) {
        return new SimpleTile(getTexture(textureId), false);
    }

    private static String getTextureFilename(int textureId) {
        return String.format("tiles/mapTile_%03d.png", textureId);
    }

    private static Tile rotate(Tile tile) {
        tile.rotate();
        return tile;
    }
}
