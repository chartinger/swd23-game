package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.input.InputObservable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Observable;
import java.util.Observer;

public class PlayerBoy implements GameObject, Observer {
    private Texture image;
    private Sprite sprite;
    private String lastAction;

    public PlayerBoy() {
        image = new Texture("dinghyLarge1.png"); // TODO: Pfad anpassen!
        sprite = new Sprite(image);
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x + 20, y + 15);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void moveUp() {
        sprite.setY(sprite.getY() + 10);
        lastAction = "Up";
    }

    public void moveDown() {
        sprite.setY(sprite.getY() - 10);
        lastAction = "Down";
    }

    public void moveLeft() {
        sprite.setX(sprite.getX() - 10);
        lastAction = "Left";
    }

    public void moveRight() {
        sprite.setX(sprite.getX() + 10);
        lastAction = "Right";
    }

    public String getLastAction() {
        return lastAction;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof InputObservable) {
            String action = (String) arg;
            if (action.equals("MoveUp")) {
                moveUp();
            } else if (action.equals("MoveDown")) {
                moveDown();
            } else if (action.equals("MoveLeft")) {
                moveLeft();
            } else if (action.equals("MoveRight")) {
                moveRight();
            }
        }
    }
}
