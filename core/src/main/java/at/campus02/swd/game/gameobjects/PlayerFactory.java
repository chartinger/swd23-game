package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.Objects;

public class PlayerFactory implements GameObjectFactory<PlayerType> {

    private final AssetRepository assetRepository;

    public PlayerFactory(AssetRepository assetRepository) {
        this.assetRepository = Objects.requireNonNull(assetRepository);
        for (PlayerType playerType : PlayerType.values()) {
            this.assetRepository.loadTexture(getTextureFilename(getTextureId(playerType)));
        }
    }

    @Override
    public Player create(PlayerType type) {
        int textureId = getTextureId(type);
        String textureFile = getTextureFilename(textureId);
        Texture texture = assetRepository.getTexture(textureFile);
        return new Player(texture);
    }

    private static int getTextureId(PlayerType type) {
        return switch (Objects.requireNonNull(type)) {
            case READY_PLAYER_ONE -> 94;
        };
    }

    private static String getTextureFilename(int textureId) {
        return String.format("sprites/mapTile_%03d.png", textureId);
    }
}
