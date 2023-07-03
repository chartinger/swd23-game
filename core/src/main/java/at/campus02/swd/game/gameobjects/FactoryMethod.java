package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class FactoryMethod{
    public static GameObject createObject (String type, Texture texture) {
        if (type.equalsIgnoreCase("player")) {
            return new PlayerBoy() {

            };
        } else if (type.equalsIgnoreCase("tile")) {
            return new Tile(texture);
        } else if (type.equalsIgnoreCase("enemy")) {
            return new EnemyBoy(-20,-30,100,50) {

            };
        } else if (type.equalsIgnoreCase("enemy2")) {
            return new EnemyBoy2(-20,-30,200, 50) {

            };
        }
        return null;
    }
}
