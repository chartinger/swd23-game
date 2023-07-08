package at.campus02.swd.game.playerobjects;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.logic.UsedTextures;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player implements GameObject {

    public String name;

    private Texture image;
    private Sprite sprite;

    private float x = 0;
    private float y = 0;

    private final UsedTextures textures = UsedTextures.instance();

    public static boolean playerShoot = false;

    public Player(String name) {
        this.name = name;
        this.image = textures.Textures.get("Player");
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
}
