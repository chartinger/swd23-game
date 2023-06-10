package at.campus02.swd.game.gameobjects;
import com.badlogic.gdx.graphics.Texture;
import java.util.HashMap;
import java.util.Map;

public class AssetRepository {
    private static AssetRepository instance;
    private Map<String, Texture> textureCache;

    private AssetRepository() {
        textureCache = new HashMap<>();
    }
//    Gibt die einzige Instanz des AssetRepository zurück.
//    Wenn keine Instanz vorhanden ist, wird eine neue Instanz erstellt und zurückgegeben.fg

    public static AssetRepository getInstance() {
        if (instance == null) {
            instance = new AssetRepository();
        }
        return instance;
    }
//    Gibt die Textur mit dem angegebenen Dateipfad zurück.
//    Falls die Textur noch nicht im Cache vorhanden ist, wird sie erstellt, im Cache abgelegt und zurückgegeben.

    public Texture getTexture(String path) {
        Texture texture = textureCache.get(path);
        if (texture == null) {
            texture = new Texture(path);
            textureCache.put(path, texture);
        }
        return texture;
    }
//    Gibt alle im Cache gespeicherten Texturen frei.
//    Ruft die "dispose()" Methode für jede Textur im Cache auf und leert anschließend den Cache.

    public void dispose() {
        for (Texture texture : textureCache.values()) {
            texture.dispose();
        }
        textureCache.clear();
    }
}
