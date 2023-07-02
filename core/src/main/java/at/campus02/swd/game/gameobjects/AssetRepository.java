package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.util.HashMap;
import java.util.Map;

public class AssetRepository {
    private static AssetRepository instance;
    private Map<String, Texture> textures;

    private AssetRepository() {
        textures = new HashMap<>();
    }

    public static AssetRepository getInstance() {
        if (instance == null) {
            synchronized (AssetRepository.class) {
                if (instance == null) {
                    instance = new AssetRepository();
                }
            }
        }
        return instance;
    }

    public void preloadAssets() {

        Texture texture1 = loadTexture("tiles/mapTile_001.png");
        textures.put("Sand_Links_Oben", texture1);

        Texture texture2 = loadTexture("tiles/mapTile_002.png");
        textures.put("Sand_Mitte_Oben", texture2);

        Texture texture3 = loadTexture("tiles/mapTile_003.png");
        textures.put("Sand_Rechts_Oben", texture3);

        Texture texture4 = loadTexture("tiles/mapTile_016.png");
        textures.put("Sand_Links_Mitte", texture4);

        Texture texture5 = loadTexture("tiles/mapTile_017.png");
        textures.put("Sand_Mitte", texture5);

        Texture texture6 = loadTexture("tiles/mapTile_018.png");
        textures.put("Sand_Rechts_Mitte", texture6);

        Texture texture7 = loadTexture("tiles/mapTile_031.png");
        textures.put("Sand_Links_Unten", texture7);

        Texture texture8 = loadTexture("tiles/mapTile_032.png");
        textures.put("Sand_Mitte_Unten", texture8);

        Texture texture9 = loadTexture("tiles/mapTile_033.png");
        textures.put("Sand_Rechts_Unten", texture9);

    }

    public Texture getTexture(String textureName) {
        // Get a texture from the repository
        return textures.get(textureName);
    }

    public void dispose() {
        // Dispose of all the loaded assets
        for (Texture texture : textures.values()) {
            texture.dispose();
        }
        textures.clear();
    }

    private Texture loadTexture(String fileName) {
        try {
            Pixmap pixmap = new Pixmap(Gdx.files.internal(fileName));
            Texture texture = new Texture(pixmap);
            pixmap.dispose();
            return texture;
        } catch (GdxRuntimeException e) {
            // Handle any potential loading errors
            e.printStackTrace();
            return null; // or throw an exception, depending on your needs
        }
    }
}
