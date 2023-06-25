package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Entity;
import at.campus02.swd.game.gameobjects.Player;

public class RunStrategy implements Strategy {

    private final Entity entity;

    public RunStrategy(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void execute(Entity target) {
        float enemyPosX = entity.getSprite().getX();
        float enemyPosY = entity.getSprite().getY();
        this.entity.move(5f, 0);
    }
}
