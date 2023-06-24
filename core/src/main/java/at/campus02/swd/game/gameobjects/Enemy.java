package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.PositionObserver;
import at.campus02.swd.game.strategy.ChargeStrategy;
import at.campus02.swd.game.strategy.Strategy;

public class Enemy extends Player{
    private Strategy strategy;
    public Enemy(Type type, PositionObserver observer, int health) {
        super(type, observer, health);
        strategy = new ChargeStrategy(this);
    }
}
