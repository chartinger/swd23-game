package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.sun.org.apache.xerces.internal.util.TeeXMLDocumentFilterImpl;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class AssetRepository  {

    private Texture player;
    private Texture enemy;
    private Texture grass;
    private Texture water;



    private static AssetRepository instance = null;

    private AssetRepository() {}

    public static AssetRepository getInstance(){

        if (instance == null){
            instance = new AssetRepository();
        }

        return instance;
    }


   /* public void preloadAssets(){

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

    } */ // alte public void preloadAssets() --> was soll hier passieren?

    public Texture getTexturePlayer() {
        return player;
    }

    public Texture getTextureEnemy() {
        return enemy;
    }

    public Texture getTextureGrass() {
        return grass;
    }

    public Texture getTextureWater() {
        return water;
    }


    /**
     * Hier werden alle Texturen für das AssetRepository initialisiert.
     * Nicht mehr beim Initialisieren des AssetRepositorys selbst.
     *
     * Diese Methode sollte aktuell nur in der Main .create() Methode verwendet werden.
     */
    public void preloadAssets(){

        player = new Texture("sprites/Ships/ship (3).png");
        enemy = new Texture("sprites/Ships/ship (2).png");
        grass = new Texture("tiles/mapTile_022.png");
        water = new Texture("tiles/mapTile_188.png");

    }

    /**
     * Hier werden alle Texturen aus dem AssetRepository aufgeräumt.
     *
     * Diese Methode soll aktuell nur in der Main --> .dispose() Methode verwendet werden.
     *
     * **/
    public void dispose(){

        player.dispose();
        enemy.dispose();
        grass.dispose();
        water.dispose();

    }


}
