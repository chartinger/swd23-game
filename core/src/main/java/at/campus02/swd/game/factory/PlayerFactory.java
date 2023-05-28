package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Player;
import com.badlogic.gdx.graphics.Texture;


public class PlayerFactory extends Factory{
    @Override
    protected GameObject createGameObject(Type type, int x, int y) {
        GameObject player;
        switch (type){
            case HUMAN:
                player = new Player(Type.HUMAN);
                break;
            case ENEMY:
                player = new Player(Type.ENEMY);
                break;
            default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }
        player.setPosition(x,y);
        return player;
    }
}
