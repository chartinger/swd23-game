package at.campus02.swd.game.gameobjects;

import java.util.Random;

public class EnemyFactory extends Factory {
    /**
     * Creates and Initializes a 'Enemy'
     *
     * @return: Returns created Enemy
     */

    @Override
    public Enemy create() {
        Random random = new Random();
        int randomNumberX = random.nextInt(481) - 240;
        int randomNumberY = random.nextInt(481) - 240;
        Enemy enemy = new Enemy(AssetRepository.getInstance().getTextureEnemy());
        enemy.setPosition(randomNumberX,randomNumberY);
        return enemy;
    }


}
