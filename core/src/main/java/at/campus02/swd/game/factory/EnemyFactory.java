package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Player;

public class EnemyFactory extends Factory{
    @Override
    protected GameObject createGameObject(Type type, int x, int y) {
        GameObject enemy;
        switch (type){
            case ENEMY1:
                enemy = new Enemy("sprites/ship parts/crew (3).png");
                break;
            case ENEMY2:
                enemy = new Enemy("sprites/ship parts/crew (1).png");
                break;
            default:
                throw  new IllegalArgumentException("Kein EnemyObject gefunden");
        }
        enemy.setPosition(x,y);
        return enemy;
    }
}
