package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.Movement;
import at.campus02.swd.game.observer.PositionObserver;
import at.campus02.swd.game.strategy.Strategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements GameObject {
    private final Movement movement;
    private Texture image;
    private Sprite sprite;
    private int health;

    public Player(Type type, PositionObserver observer, int health) {
        this.image = AssetRepository.INSTANCE.getTexture(type);
        sprite = new Sprite(image);
        movement = new Movement();
        movement.registerObserver(observer);
        this.health = health;
    }

    public Sprite getSprite() {
        return sprite;
    }

    @Override
    public void act(float delta) {

    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isBorderCollision(float x, float y) {
        if (y <= -10 || y >= 540) {
            return true;
        } else if (x <= 10 || x >= 560) {
            return true;
        } else return false;
    }

    @Override
    public void setPosition(float x, float y) {

        sprite.setPosition(x, y);
        movement.setPosition(x, y, sprite.getRotation());
    }

    public void setRotation(float angle) {
        sprite.setRotation(angle);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
