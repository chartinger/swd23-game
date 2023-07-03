package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;

public class EnemyBoy implements GameObject {
    private Texture image;
    private Sprite sprite;

    private float centerX;
    private float centerY;
    private float radius;
    private float angle;
    private float speed;

    private float positionX;
    private float positionY;
    public EnemyBoy(float centerX, float centerY, float radius, float initialSpeed) {
        image = new Texture("sprites/Ships/dinghyLarge2.png");
        sprite = new Sprite(image);

        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.angle = 0;
        this.speed = initialSpeed;
    }

    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }

    @Override
    public void act(float delta) {
        angle += speed * delta;

        // Calculate the new position based on the angle and radius
        float x = centerX + (float) Math.cos(Math.toRadians(angle)) * radius;
        float y = centerY + (float) Math.sin(Math.toRadians(angle)) * radius;

        setPosition(x, y);
    }

    public void increaseSpeed(float additionalSpeed) {
        speed += additionalSpeed;
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
        positionX = sprite.getX();
        positionY = sprite.getY();
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }
}
