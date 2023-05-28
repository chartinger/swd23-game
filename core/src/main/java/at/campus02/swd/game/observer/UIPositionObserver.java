package at.campus02.swd.game.observer;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import at.campus02.swd.game.observer.PositionObserver;

public class UIPositionObserver implements PositionObserver {
    private BitmapFont font;

    public UIPositionObserver(BitmapFont font) {
        this.font = font;
    }


    @Override
    public void updatePosition(float x, float y, float rotation) {
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        font.draw(spriteBatch, "Position: x=" + x + ", y=" + y + ", rotation=" + rotation, x, y);
        spriteBatch.end();
    }
}
