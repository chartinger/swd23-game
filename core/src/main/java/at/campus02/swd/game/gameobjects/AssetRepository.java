package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public enum AssetRepository {
    INSTANCE;
    private HashMap<Type, Texture> textures = new HashMap<>();

    public void preload() {
        textures.put(Type.SIGN, new Texture("sign.png"));
        textures.put(Type.WATER, new Texture("tiles/tropical/Terrain/DeepWater/Deepwater/Watermiddle2.png"));
        textures.put(Type.ISLAND_TOP, new Texture("tiles/tropical/Terrain/Desertwater/Desertwatertop1.png"));
        textures.put(Type.ISLAND_TOP_LEFT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterlefttop1.png"));
        textures.put(Type.ISLAND_TOP_RIGHT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterrighttop1.png"));
        textures.put(Type.ISLAND_CENTER, new Texture("tiles/tropical/Terrain/Desert/Desert1.png"));
        textures.put(Type.ISLAND_LEFT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterleft1.png"));
        textures.put(Type.ISLAND_RIGHT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterright1.png"));
        textures.put(Type.ISLAND_BOTTOM, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterbot1.png"));
        textures.put(Type.ISLAND_BOTTOM_LEFT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterleftbot1.png"));
        textures.put(Type.ISLAND_BOTTOM_RIGHT, new Texture("tiles/tropical/Terrain/Desertwater/Desertwaterrightbot1.png"));
        textures.put(Type.HUMAN, new Texture("sprites/Ships/ship (1).png"));
        textures.put(Type.ENEMY, new Texture("sprites/Ships/ship (14).png"));
    }

    public Texture getTexture(Type type) {
        Texture texture = textures.get(type);
        return texture;
    }

    public void dispose() {
        textures.clear();
    }

}
