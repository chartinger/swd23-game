package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Player;

public class ChargeStrategy implements Strategy{
    Enemy enemy;

    public ChargeStrategy(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void execute(Player player) {
        float enemyPosX = enemy.getSprite().getX();
        float enemyPosY = enemy.getSprite().getY();
        float playerPosX = player.getSprite().getY();
        float playerPosY = player.getSprite().getY();

    }

}
