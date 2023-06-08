package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AssetRepository  {

    private static AssetRepository instance = null;

    private AssetRepository() {}

    public static AssetRepository getInstance(){

        if (instance == null){
            instance = new AssetRepository();
        }

        return instance;
    }


    public void preloadAssets(){

        ArrayList<Texture> textures = new ArrayList<>();
        ArrayList<String> paths = new ArrayList<>();

        for ( Path path : Paths.get("/assets")) {

            String texturePath = path.toString();
            paths.add(texturePath);
        }

        for (String path : paths) {

            Texture texture = new Texture(path);
            textures.add(texture);

        }

        if (textures.size() == 0){
            // System.setErr("Keine Texturen gefunden");
        }


    }


    public void getTexture(Texture texture){





    }

    // dispose()


}
