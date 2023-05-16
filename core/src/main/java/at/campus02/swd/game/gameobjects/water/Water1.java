package at.campus02.swd.game.gameobjects.water;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Water1 implements GameObject{

    private Texture image;
    private Sprite sprite;

    public Water1() {
        image = new Texture("tiles/mapTile_171.png");
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

    public Texture getImage() {
        return image;
    }
}
