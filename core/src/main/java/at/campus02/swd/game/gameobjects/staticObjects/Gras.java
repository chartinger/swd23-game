package at.campus02.swd.game.gameobjects.staticObjects;

import at.campus02.swd.game.logic.UsedTextures;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Gras extends StaticObject {

    private final UsedTextures textures = UsedTextures.instance();

    public Gras() {
        super(0,0, "Gras");
    }

    @Override
    public void act(float delta) {}

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x,y);
    }

    @Override
    public float getPositionX() {
        return 0;
    }

    @Override
    public float getPositionY() {
        return 0;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
