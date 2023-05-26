package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.sun.org.apache.bcel.internal.generic.PUSH;

public class TileFactory extends Factory{

    private String type;


    /**
     * Creates and Initializes a Tile
     *
     * @return: Returns created Tile
     */

    public Tile createGras() {
        Tile tile =  new Tile(new Texture("tiles/mapTile_022.png"));
        return tile;
    }

    public Tile createWasser() {
        Tile tile =  new Tile(new Texture("tiles/mapTile_188.png"));
        return tile;
    }


    @Override
    public Tile create() {
        Tile tile =  new Tile(new Texture("tiles/mapTile_188.png"));
        return tile;
    }
}
