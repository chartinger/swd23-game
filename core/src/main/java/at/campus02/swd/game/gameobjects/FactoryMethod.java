package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class FactoryMethod{
    public static GameObject createObject (String type, Texture texture) {
        if (type.equalsIgnoreCase("player")) {
            return new PlayerBoy() {

            };
        } else if (type.equalsIgnoreCase("tile")) {
            return new Tile(texture);
        }
        return null;
    }
}
