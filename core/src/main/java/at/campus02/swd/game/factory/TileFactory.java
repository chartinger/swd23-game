package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.MeadowTile;

public class TileFactory extends Factory{
    protected GameObject createGameObject(Type type, int x, int y) {
        GameObject tile;
        switch (type){
            case MEADOW_TOP_LEFT:
                tile = new MeadowTile("tiles/mapTile_006.png");
                break;
            case MEADOW_TOP:
                tile = new MeadowTile("tiles/mapTile_007.png");
                break;
            case MEADOW_TOP_RIGHT:
                tile = new MeadowTile("tiles/mapTile_008.png");
                break;
            case MEADOW_LEFT:
                tile = new MeadowTile("tiles/mapTile_021.png");
                break;
            case MEADOW_CENTER:
                tile = new MeadowTile("tiles/mapTile_022.png");
                break;
            case MEADOW_RIGHT:
                tile = new MeadowTile("tiles/mapTile_023.png");
                break;
            case MEADOW_BOTTOM_LEFT:
                tile = new MeadowTile("tiles/mapTile_036.png");
                break;
            case MEADOW_BOTTOM:
                tile = new MeadowTile("tiles/mapTile_037.png");
                break;
            case MEADOW_BOTTOM_RIGHT:
                tile = new MeadowTile("tiles/mapTile_038.png");
                break;
            default:
                throw  new IllegalArgumentException("Wert ungültig: "+type);
        }
        return tile;
    }

}
