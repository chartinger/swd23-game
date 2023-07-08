package at.campus02.swd.game.gameobjects.interactiveObjects;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Coral extends InteractiveObject {

    private final float x;
    private final float y;

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
