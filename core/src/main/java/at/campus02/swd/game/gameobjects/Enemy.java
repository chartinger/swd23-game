package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy implements GameObject{

    private List<PositionObserver> observers;
    private Texture image; // Die Textur des Spielers.
    private Sprite sprite; // Der Sprite des Spielers.
    private String imagePath;
    private float x; // X-Koordinate des Spielers
    private float y; // Y-Koordinate des Spielers
    private float speed; // Geschwindigkeit des Spielers

    public Enemy(String imagePath) {
        this.imagePath = imagePath;
        this.image = AssetRepository.getInstance().getTexture(imagePath);
        this.sprite = new Sprite(image);
        this.x = 0;
        this.y = 0;
        this.speed = 40;
    }


    @Override
    public void act(float delta) {
        int max = 370;
        int min = 100;
        int range = max - min + 1;
        int randX = 0;
        int randY = 0;

        randX = (int) (Math.random() * range) + min;
        randY = (int) (Math.random() * range) + min;
        setPosition(randX,randY);
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
