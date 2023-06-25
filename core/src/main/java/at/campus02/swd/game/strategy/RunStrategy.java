package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.UIPositionObserver;

public class RunStrategy implements MovementStrategy {
    @Override
    public void execute(Enemy enemy, UIPositionObserver uiPositionObserver) {
        System.out.println("Run away from Enemy");
    }
}
