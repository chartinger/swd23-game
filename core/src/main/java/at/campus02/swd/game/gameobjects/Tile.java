package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;

public class Tile implements GameObject {
    private Texture image; // Die Textur des Tiles.
    private Sprite sprite; // Der Sprite des Tiles.
    public static final float SIZE = 64.0f;


    private String imagePath;

    public Tile(String imagePath) {
        this.imagePath=imagePath;
        this.image =AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
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
}
