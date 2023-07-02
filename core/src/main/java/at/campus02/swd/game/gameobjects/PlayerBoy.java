package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Observable;
import java.util.Observer;

public class PlayerBoy extends Observable implements GameObject {
    private Texture image;
    private Sprite sprite;
    private String lastAction;

    private float positionX;
    private float positionY;

    public PlayerBoy() {
        image = new Texture("dinghyLarge1.png"); // TODO: Pfad anpassen!
        sprite = new Sprite(image);
    }
    public float getPositionX() {
        return positionX;
    }

    public float getPositionY() {
        return positionY;
    }



    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x , y );
        positionX = sprite.getX();
        positionY = sprite.getY();
        setChanged();
        notifyObservers("changed position");
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void moveUp() {
        sprite.setY(sprite.getY() + 10);
        lastAction = "Up";
        positionX = sprite.getX();
        positionY = sprite.getY();
        setChanged();
        notifyObservers("MoveUp");
    }

    public void moveDown() {
        sprite.setY(sprite.getY() - 10);
        lastAction = "Down";
        positionX = sprite.getX();
        positionY = sprite.getY();
        setChanged();
        notifyObservers("MoveDown");

    }

    public void moveLeft() {
        sprite.setX(sprite.getX() - 10);
        lastAction = "Left";
        positionX = sprite.getX();
        positionY = sprite.getY();
        setChanged();
        notifyObservers("MoveLeft");
    }

    public void moveRight() {
        sprite.setX(sprite.getX() + 10);
        lastAction = "Right";
        positionX = sprite.getX();
        positionY = sprite.getY();;
        setChanged();
        notifyObservers("MoveRight");
    }

    public String getLastAction() {
        return lastAction;
    }

    public void addObserver(Observer observer) {
        super.addObserver(observer);
    }


}
