package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
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
            case ISLAND:

                IslandBuilder islandBuilder = new IslandBuilder(5, 5, Type.LEFT_TOP, Type.TOP, Type.RIGHT_TOP,
                    Type.LEFT_BOTTOM, Type.BOTTOM, Type.RIGHT_BOTTOM, Type.RIGHT, Type.LEFT, Type.MIDDLE);

                tile = islandBuilder.createIslandTile(x, y, 5, 200, 200);
                break;

            default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }

        return tile;
    }

}
