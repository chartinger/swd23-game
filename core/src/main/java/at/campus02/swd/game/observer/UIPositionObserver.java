package at.campus02.swd.game.observer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import at.campus02.swd.game.observer.PositionObserver;

public class UIPositionObserver implements PositionObserver {

    String pos;


    @Override
    public void updatePosition(float x, float y, float rotation) {
        pos = "x = " + x + ", y = " + y + ", rot = " + rotation;
    }

    public String getPos() {
        return pos;
    }
}
