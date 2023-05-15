package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.gameobjects.WaterTile;

public class TileFactory extends Factory{
    @Override
    protected GameObject createGameObject(TileType type) {
        switch (type){
            case WATER:
                return new WaterTile();
            case SIGN:
                return new Sign();
            default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }
    }
}
