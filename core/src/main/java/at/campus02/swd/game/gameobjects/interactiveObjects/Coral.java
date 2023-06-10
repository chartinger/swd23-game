package at.campus02.swd.game.gameobjects.interactiveObjects;

import at.campus02.swd.game.gameobjects.interactiveObjects.InteractiveObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.w3c.dom.Text;

public class Coral extends InteractiveObject {

    private float x;
    private float y;

    public Coral(float x, float y) {
        super(x, y, "Coral");
        this.x = x;
        this.y = y;
    }


    @Override
    public void act(float delta) {

    }
    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x,y);
    }

    @Override
    public float getPositionX() {
        return x;
    }

    @Override
    public float getPositionY() {
        return y;
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }
}
