package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.PositionObserver;
import at.campus02.swd.game.strategy.ChargeStrategy;
import at.campus02.swd.game.strategy.RunStrategy;
import at.campus02.swd.game.strategy.Strategy;

public class Enemy extends Entity {

    private Strategy strategy;
    private Entity target;

    public Enemy(Type type, PositionObserver observer, int health) {
        super(type, observer, health);
        strategy = new ChargeStrategy(this);
    }

    public void setTarget(Entity target) {
        this.target = target;
    }

    @Override
    public void act(float delta) {
        if (this.getHealth() < this.getHealth() / 2) {
            this.strategy = new RunStrategy(this);
        }
        strategy.execute(target);
    }

    @Override
    public void attack() {

    }

    public int getDamage() {
        return 10;
    }
}
