package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Player implements GameObject {
    private List<PositionObserver> observers;
    private Texture image; // Die Textur des Spielers.
    private Sprite sprite; // Der Sprite des Spielers.
    private String imagePath;
    private float x; // X-Koordinate des Spielers
    private float y; // Y-Koordinate des Spielers
    private float speed; // Geschwindigkeit des Spielers

    private boolean movingUp;
    private boolean movingDown;
    private boolean movingLeft;
    private boolean movingRight;

    public Player(String imagePath) {
        this.imagePath = imagePath;
        this.image = AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
        this.x = 0;
        this.y = 0;
        this.speed = 60;
        this.observers = new ArrayList<>();
    }

    public void addObserver(PositionObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(PositionObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(int x, int y) {
        for (PositionObserver observer : observers) {
            observer.updatePosition(x, y);
        }
    }


    @Override
    public void act(float delta) {
        if (movingUp || movingDown || movingLeft || movingRight) {
            // Bewegungslogik hier einfügen
            float newX = x;
            float newY = y;

            if (movingUp) {
                newY += speed * delta;
            }
            if (movingDown) {
                newY -= speed * delta;
            }
            if (movingLeft) {
                newX -= speed * delta;
            }
            if (movingRight) {
                newX += speed * delta;
            }
            if(newX >= 375){
                movingRight = false;
            }
            if(newX <= 85){
                movingLeft = false;
            }
            if(newY >= 385){
                movingUp = false;
            }
            if(newY <= 105){
                movingDown = false;
            }

            setPosition(newX, newY);
            notifyObservers((int) newX, (int) newY);}
    }


    public void moveUp() {
        movingUp = true;
    }

    public void moveDown() {
        movingDown = true;
    }

    public void moveLeft() {
        movingLeft = true;
    }

    public void moveRight() {
        movingRight = true;
    }


    public void stopMovingUp() {
        movingUp = false;
    }

    public void stopMovingDown() {
        movingDown = false;
    }

    public void stopMovingLeft() {
        movingLeft = false;
    }

    public void stopMovingRight() {
        movingRight = false;
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
