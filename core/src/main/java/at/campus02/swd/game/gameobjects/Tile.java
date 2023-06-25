package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.factory.Type;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile implements GameObject {
    private Texture image;
    private Sprite sprite;

    public Tile(Type type) {
        this.image = AssetRepository.INSTANCE.getTexture(type);
        sprite = new Sprite(image);
    }

    @Override
    public void act(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    public void setRotation(float angle) {
        sprite.setRotation(angle);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
