package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.PositionObserver;

public class Player extends Entity {

    public Player(Type type, PositionObserver observer, int health) {
        super(type, observer, health);
    }

    @Override
    public void act(float delta) {

    }
}
