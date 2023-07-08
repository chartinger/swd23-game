package at.campus02.swd.game.gameobjects.interactiveObjects;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Rock extends InteractiveObject {

    private float x;
    private float y;

    public Rock(float x, float y) {
        super(x,y, "Rock");
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
