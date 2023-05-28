package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class AssetRepository {

    public static final AssetRepository INSTANCE = new AssetRepository();

    private final Map<String, Texture> textureMap = new HashMap<>();

    private AssetRepository() {
    }

    public Texture getTexture(String textureFile) {
        return textureMap.get(textureFile);
    }

    public void loadTexture(String textureFile) {
        textureMap.put(textureFile, new Texture(textureFile));
    }
}
