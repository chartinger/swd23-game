package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class DrawObserver {

    public void draw(SpriteBatch batch, ExtendViewport viewport, Array<GameObject> gameObjects) {
        BitmapFont font;
        font = new BitmapFont();
        font.setColor(Color.WHITE);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        font.draw(batch, "Krasses Gras", -220, -220);
        batch.end();
    }

}
