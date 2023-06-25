package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class UIPositionObserver implements PositionObserver {
    private float currentX;
    private float currentY;
    private final SpriteBatch batch;
    private final ExtendViewport viewport;
    private String positions;
    private final BitmapFont fontPlayerPosition;

    @Override
    public void update(Observable observable) {
        currentX = observable.getPositionX();
        currentY = observable.getPositionY();

        positions = currentX + " / " + currentY;

        draw();
    }

    @Override
    public void update(float x, float y) {
        currentX = x;
        currentY = y;

        positions = currentX + " / " + currentY;

        draw();
    }

    public void draw() {
        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                batch.setProjectionMatrix(viewport.getCamera().combined);
                batch.begin();
                fontPlayerPosition.draw(batch, "Spielerposition: " + positions, -220, -220);
                batch.end();
            }
        });
    }

    public UIPositionObserver(SpriteBatch batch, ExtendViewport viewport) {
        this.batch = batch;
        this.viewport = viewport;
        this.positions = "";
        this.fontPlayerPosition = new BitmapFont();
        this.fontPlayerPosition.setColor(Color.WHITE);
    }


    // Diese Methoden eigentlich nicht notwendig :)

    @Override
    public float updateX(Observable observable) {
        currentX = observable.getPositionX();
        return currentX;
    }

    @Override
    public float updateX(float x) {
        currentX = x;
        return currentX;
    }

    @Override
    public float updateY(Observable observable) {
        currentY = observable.getPositionY();
        return currentY;
    }

    @Override
    public float updateY(float y) {
        currentY = y;
        return currentY;
    }

    public String getPositions() {
        return positions;
    }

    public float getCurrentX() {
        return currentX;
    }

    public float getCurrentY() {
        return currentY;
    }
}

