package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.WaterTile;

public class TileFactory extends Factory{
    @Override
    protected GameObject createGameObject(String type) {
        if (type.startsWith("Water")){
            return new WaterTile();
        } else return new WaterTile(); //more ifs
    }
}
