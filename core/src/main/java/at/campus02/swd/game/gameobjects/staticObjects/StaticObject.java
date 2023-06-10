package at.campus02.swd.game.gameobjects.staticObjects;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.logic.UsedTextures;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StaticObject implements GameObject{

    private final Sprite sprite;

    private float x = 0;
    private float y = 0;

    public StaticObject(float x, float y, String typeOfTexture) {
        UsedTextures textures = UsedTextures.instance();
        this.sprite = new Sprite(textures.Textures.get(typeOfTexture));
        this.x = x;
        this.y = y;
        this.setPosition(this.x,this.y);
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
    public float getPositionX() {
        return this.x;
    }

    @Override
    public float getPositionY() {
        return this.y;
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
