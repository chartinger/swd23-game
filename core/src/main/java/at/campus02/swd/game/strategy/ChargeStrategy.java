package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Entity;
import at.campus02.swd.game.gameobjects.Player;

import java.util.Objects;

public class ChargeStrategy implements Strategy {
    Entity entity;

    public ChargeStrategy(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void execute(Entity target) {
        float enemyPosX = entity.getSprite().getX();
        float enemyPosY = entity.getSprite().getY();
        float targetPosX = target.getSprite().getX();
        float targetPosY = target.getSprite().getY();

        if (enemyPosX == targetPosX) {
            if (enemyPosY == targetPosY)
                return;
            float dy = targetPosY > enemyPosY ? 1f : -1f;
            entity.move(0, dy);
            return;
        }

        float dx = targetPosX > enemyPosX ? 1f : -1f;
        entity.move(dx, 0);
    }

}
