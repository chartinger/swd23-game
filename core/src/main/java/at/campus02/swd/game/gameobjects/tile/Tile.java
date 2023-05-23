package at.campus02.swd.game.gameobjects.tile;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Tile implements GameObject {

    private String image;
    private Texture texture;
    private Sprite sprite;

    public Tile(String image) {
        this.image = image;
        this.texture = new Texture(this.image);
        this.sprite = new Sprite(texture);
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

    public String getImage() {
        return image;
    }

    public Texture getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
