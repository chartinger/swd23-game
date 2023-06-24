package at.campus02.swd.game.gameobjects;

public class EnemyFactory extends Factory {
    /**
     * Creates and Initializes a Player
     *
     * @return: Returns created Player
     */

    @Override
    public Enemy create() {

        Enemy enemy = new Enemy(AssetRepository.getInstance().getTextureEnemy());

        return enemy;
    }


}
