package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements GameObject {

    private List<PositionObserver> observers;
    private final Texture image; // Die Textur des Spielers.
    private final Sprite sprite; // Der Sprite des Spielers.
    private final String imagePath;
    private float x; // X-Koordinate des Spielers
    private float y; // Y-Koordinate des Spielers
    private final float speed; // Geschwindigkeit des Spielers

    public Enemy(String imagePath) {
        this.imagePath = imagePath;
        this.image = AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
        this.x = 0;
        this.y = 0;
        this.speed = 40;

    }

    int velX = 2;
    int velY = 2;

    @Override
    public void act(float delta) {
        y += velY;
        x += velX;

        if (y <= 105 || y >= 385) velY *= -1;
        if (x <= 85 || x >= 375) velX *= -1;

        setPosition(x, y);
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
