package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.tile.ErrorTile;
import at.campus02.swd.game.gameobjects.tile.Tile;
import at.campus02.swd.game.gameobjects.tile.WaterTile;

import java.util.ArrayList;

public class TileFactory implements Factory{

    private Tile tile;


    @Override
    public Tile create(String tile) {

        switch (tile.toLowerCase()) {
            case "water":
                this.tile = new WaterTile("tiles/mapTile_171.png");
                return this.tile;
            default: return new ErrorTile("sign.png");
        }

    }

    @Override
    public void initialize() {

    }

    @Override
    public ArrayList<GameObject> getObjects() {

        ArrayList<GameObject> tiles = new ArrayList<>();
        tiles.add(this.tile);

        return tiles;

    }
}
