package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import at.campus02.swd.game.observer.Movement;
import at.campus02.swd.game.observer.PositionObserver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements GameObject {
    private final Movement movement;
    private Texture image;
    private Sprite sprite;
    private int health;

    public Entity(Type type, PositionObserver observer, int health) {
        this.image = AssetRepository.INSTANCE.getTexture(type);
        sprite = new Sprite(image);
        movement = new Movement();
        movement.registerObserver(observer);
        this.health = health;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public abstract void act(float delta);

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public static boolean isBorderCollision(float x, float y) {
        if (y < 0 || y >= 550) {
            return true;
        } else return x < 10 || x >= 560;
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        movement.setPosition(x, y, sprite.getRotation());
    }

    public void move(float dx, float dy) {
        float x = sprite.getX();
        float y = sprite.getY();
        if (isBorderCollision(x + dx, y + dy))
            return;
        setPosition(x + dx, y + dy);

        if (dy > 0)
            setRotation(180f);
        else if (dy < 0)
            setRotation(0f);
        else if (dx > 0)
            setRotation(90f);
        else if (dx < 0)
            setRotation(270f);
    }

    public void setRotation(float angle) {
        sprite.setRotation(angle);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
