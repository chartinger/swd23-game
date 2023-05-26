package at.campus02.swd.game.gameobjects.entities;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity implements GameObject{
    private Sprite sprite;

    public Entity(String spritePath) {
        this.sprite = new Sprite(new Texture(spritePath));
    }
    public Entity(String spritePath, int x, int y) {
        this.sprite = new Sprite(new Texture(spritePath));
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

    public abstract void update();

}
