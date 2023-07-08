package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.UIPositionObserver;

public interface MovementStrategy {
    void execute(Enemy enemy, UIPositionObserver uiPositionObserver);

    void stopExecution();
}
