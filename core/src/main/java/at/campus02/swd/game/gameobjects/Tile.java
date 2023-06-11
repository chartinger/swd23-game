package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameObject {
    private final Texture image;
    private final Sprite sprite;

    public Tile(Texture texture, boolean isRotated) {
        image = texture;
        sprite = new Sprite(image);
        if (isRotated)
            sprite.rotate(180);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void setVisible(boolean visible) {
        sprite.setAlpha(visible ? 1f : 0f);
    }
}
