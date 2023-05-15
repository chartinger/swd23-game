package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.IslandTile;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.gameobjects.WaterTile;

public class TileFactory extends Factory{
    @Override
    protected GameObject createGameObject(Type type, int x, int y) {
        GameObject tile;
        switch (type){
            case WATER:
                tile = new WaterTile();
                break;
            case SIGN:
                tile = new Sign();
                break;
            case ISLAND_CENTER:
                tile = new IslandTile();
                break;
            default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }
        tile.setPosition(x,y);
        return tile;
    }
}
