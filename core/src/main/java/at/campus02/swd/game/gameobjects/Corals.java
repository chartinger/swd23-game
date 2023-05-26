package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Corals implements GameObject{
    private Texture image;
    private Sprite sprite;

    public Corals() {
        image = new Texture("sprites/mapTile_044.png");
        sprite = new Sprite(image);
    }


    @Override
    public void act(float delta) {

    }
    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x,y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
