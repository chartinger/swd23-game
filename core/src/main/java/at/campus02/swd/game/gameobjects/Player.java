package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.strategy.MovementStrategy;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Player implements GameObject, Observable {

    private Texture image;
    private Sprite sprite;

    /**
     * Movement TEST
     **/

    private MovementStrategy movementStrategy;

    private List<PositionObserver> observers = new ArrayList<PositionObserver>();


    public Player(Texture image) {
        this.image = image;
        this.sprite = new Sprite(image);
    }


    public void addObserver(PositionObserver obs) {
        this.observers.add(obs);
    }

    public void removeObserver(PositionObserver obs) {
        this.observers.remove(obs);
    }


    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);

        for (PositionObserver observer : observers) {
            observer.update(getPositionX(), getPositionY());
        }
    }

    public float getPositionX() {
        return sprite.getX();
    }

    public float getPositionY() {
        return sprite.getY();
    }


    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


}
