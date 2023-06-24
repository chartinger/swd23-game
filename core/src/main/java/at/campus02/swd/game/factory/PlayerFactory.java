package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.observer.EnemyPositionObserver;
import at.campus02.swd.game.observer.HumanPositionObserver;
import com.badlogic.gdx.graphics.Texture;


public class PlayerFactory extends Factory {


    @Override
    protected GameObject createGameObject(Type type, int x, int y, int z) {
        GameObject player;
        switch (type) {
            case HUMAN:
                player = new Player(Type.HUMAN, new HumanPositionObserver());
                break;
            case ENEMY:
                player = new Player(Type.ENEMY, new EnemyPositionObserver());
                break;
            default:
                throw new IllegalArgumentException("Value is invalid: " + type);
        }
        player.setPosition(x, y);
        player.setRotation(z);


        return player;
    }
}
