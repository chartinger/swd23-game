package at.campus02.swd.game.gameobjects.island;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SandLeftLower implements GameObject {

    private Texture image;
    private Sprite sprite;

    public SandLeftLower() {
        image = new Texture("tiles/tile_17.png");
        sprite= new Sprite(image);
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
