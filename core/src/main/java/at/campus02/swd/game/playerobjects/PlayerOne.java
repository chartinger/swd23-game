package at.campus02.swd.game.playerobjects;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PlayerOne implements GameObject {
    private Texture image;
    private Sprite sprite;

    private float x = 0;
    private float y = 0;

    public PlayerOne() {
        image = new Texture("sprites/Ships/ship (6).png");
        sprite = new Sprite(image);
    }


    @Override
    public void act(float delta) {

    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;

        sprite.setPosition(x,y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public float X() {
        return this.x;
    }

    public float Y() {
        return this.y;
    }
}
