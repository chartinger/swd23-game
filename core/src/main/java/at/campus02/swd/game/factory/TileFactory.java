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
                tile.setPosition(x,y);
                break;
            case SIGN:
                tile = new Sign();
                tile.setPosition(x,y);
                break;
            case ISLAND_TOP:
                tile = new IslandTile(Type.ISLAND_TOP);
                break;
            case ISLAND_TOP_LEFT:
                tile = new IslandTile(Type.ISLAND_TOP_LEFT);
                break;
            case ISLAND_TOP_RIGHT:
                tile = new IslandTile(Type.ISLAND_TOP_RIGHT);
                break;
            case ISLAND_CENTER:
                tile = new IslandTile(Type.ISLAND_CENTER);
                break;
            case ISLAND_LEFT:
                tile = new IslandTile(Type.ISLAND_LEFT);
                break;
            case ISLAND_RIGHT:
                tile = new IslandTile(Type.ISLAND_RIGHT);
                break;
            case ISLAND_BOTTOM:
                tile = new IslandTile(Type.ISLAND_BOTTOM);
                break;
            case ISLAND_BOTTOM_LEFT:
                tile = new IslandTile(Type.ISLAND_BOTTOM_LEFT);
                break;
            case ISLAND_BOTTOM_RIGHT:
                tile = new IslandTile(Type.ISLAND_BOTTOM_RIGHT);
                break;
           default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }

        return tile;
    }

}
