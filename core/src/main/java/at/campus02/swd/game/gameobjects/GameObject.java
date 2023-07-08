package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface GameObject {

    void act(float delta);
    void setPosition(float x, float y);
    float getPositionX();
    float getPositionY();
    void draw(SpriteBatch batch);
}
