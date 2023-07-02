package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Observable;

public interface GameObject {
    void act(float delta);
    void setPosition(float x, float y);
    void draw(SpriteBatch batch);

    void moveUp();

    void moveDown();

    void moveLeft();

    void moveRight();

}
