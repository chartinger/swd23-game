package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Objects;

public class Tile implements ITile {
    private final Sprite sprite;
    private int columnOffset = 0;
    private int rowOffset = 0;

    public Tile(Texture texture, boolean isRotated) {
        this.sprite = new Sprite(Objects.requireNonNull(texture));
        if (isRotated)
            sprite.rotate(180);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        final float offsetX = getWidth() * (1 - getScale()) / 2;
        final float offsetY = getHeight() * (1 - getScale()) / 2;
        final float tileOffsetX = getWidth() * getScale() * columnOffset;
        final float tileOffsetY = getHeight() * getScale() * rowOffset;
        sprite.setPosition(x - offsetX + tileOffsetX, y - offsetY + tileOffsetY);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void setVisible(boolean visible) {
        sprite.setAlpha(visible ? 1f : 0f);
    }

    @Override
    public void setScale(float scale) {
        sprite.setScale(scale);
    }

    @Override
    public void setRelativePosition(int columnOffset, int rowOffset) {
        this.columnOffset = columnOffset;
        this.rowOffset = rowOffset;
    }

    @Override
    public float getWidth() {
        return sprite.getWidth();
    }

    @Override
    public float getHeight() {
        return sprite.getHeight();
    }

    @Override
    public float getScale() {
        return sprite.getScaleX();
    }

    @Override
    public void rotate() {
        sprite.rotate(180);
    }
}
