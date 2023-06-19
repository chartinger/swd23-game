package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface ITile extends GameObject {
    @Override
    void act(float delta);

    @Override
    void setPosition(float x, float y);

    @Override
    void draw(SpriteBatch batch);

    void setVisible(boolean visible);

    void setScale(float scale);

    /**
     * Position relative to the lower left tile
     * Introduced to aid the implementation of structures composed of multiple tiles (e.g. ComposedTile)
     * @param columnOffset number of columns to the right of the left most column
     * @param rowOffset number of rows above the bottom row
     */
    void setRelativePosition(int columnOffset, int rowOffset);

    float getWidth();
    float getHeight();
    float getScale();

    void rotate();
}
