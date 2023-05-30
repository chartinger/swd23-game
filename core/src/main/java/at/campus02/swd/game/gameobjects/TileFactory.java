package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class TileFactory {
    private Texture[] tileTextures;

    public TileFactory() {
        tileTextures = new Texture[10];
        loadTextures();
    }

    private void loadTextures() {
        for (int i = 0; i < tileTextures.length; i++) {
            tileTextures[i] = new Texture("tiles/mapTile_090.png");

        }
    }

    public Tile createTile(int index) {
        if (index >= 0 && index < tileTextures.length) {
            return new Tile(tileTextures[index]);
        }
        return null;
    }
}
