package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.PositionObserver;

import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {

    private static final float MELEE_RANGE = 10f;

    private final List<Enemy> enemies;
    private Direction movement = Direction.NONE;

    public Player(Type type, PositionObserver observer, int health) {
        super(type, observer, health);
        this.enemies = new ArrayList<>();
    }

    @Override
    public void act(float delta) {
        switch (movement) {
            case NONE:
                break;
            case UP:
                move(0, 1f);
                break;
            case LEFT:
                move(-1f, 0);
                break;
            case DOWN:
                move(0, -1f);
                break;
            case RIGHT:
                move(1f, 0);
                break;
        }
    }

    @Override
    public void attack() {
        float playerX = getSprite().getX();
        float playerY = getSprite().getY();
        for (Enemy enemy : enemies) {
            float enemyX = enemy.getSprite().getX();
            float enemyY = enemy.getSprite().getY();
            if (Math.abs(enemyX - playerX) <= MELEE_RANGE && Math.abs(enemyY - playerY) <= MELEE_RANGE)
                enemy.getAttacked(getDamage());
        }
    }

    @Override
    public int getDamage() {
        return 26;
    }

    public void addEnemy(Enemy enemy) {
        this.enemies.add(enemy);
    }

    public void setMovement(Direction direction) {
        this.movement = direction;
    }

    public void cancelMovement(Direction direction) {
        if (this.movement == direction) {
            this.movement = Direction.NONE;
        }
    }
}
