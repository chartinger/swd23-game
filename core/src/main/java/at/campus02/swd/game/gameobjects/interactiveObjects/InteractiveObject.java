package at.campus02.swd.game.gameobjects.interactiveObjects;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.logic.UsedTextures;
import at.campus02.swd.game.playerobjects.InteractiveObjects;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class InteractiveObject implements GameObject{

    private final Sprite sprite;

    private float x;
    private float y;

    public InteractiveObject(float x, float y, String typeOfTexture) {
        UsedTextures textures = UsedTextures.instance();
        this.sprite = new Sprite(textures.Textures.get(typeOfTexture));
        this.x = x;
        this.y = y;
        this.setPosition(this.x,this.y);
        InteractiveObjects._listOfObjects.add(this);
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
