package at.campus02.swd.game.gameobjects.EnemyObjects;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.logic.UsedTextures;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy implements GameObject {

    private Texture image;
    private Sprite sprite;
    public boolean killMe = false;

    private float x = 0;
    private float y = 0;

    private final UsedTextures textures = UsedTextures.instance();

    public Enemy() {
        this.image = textures.Textures.get("Enemy");
        this.sprite = new Sprite(image);
        this.setPosition(0,0);
    }

    public void ChangeTexture()
    {
        image = textures.Textures.get("Explosion");
        sprite = new Sprite(image);
        sprite.setPosition(x,y);
    }

    @Override
    public void act(float delta) {}

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

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void killEnemy(){
        killMe = true;
    }
}
