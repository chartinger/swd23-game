package at.campus02.swd.game.gameobjects.entities;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.observer.EntityPositionGameObserver;
import at.campus02.swd.game.observer.GameObservable;
import at.campus02.swd.game.observer.GameObserver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public abstract class Entity implements GameObject, GameObservable{
    private Sprite sprite;

    protected int health;
    protected int speed;

    public String entityType;

    public EntityPositionGameObserver entityPositionGameObserver;

    private ArrayList<GameObserver> gameObservers;

    public Entity(String spritePath) {
        this.sprite = new Sprite(new Texture(spritePath));
        gameObservers = new ArrayList<>();
        entityPositionGameObserver = new EntityPositionGameObserver(this);
        registerObserver(entityPositionGameObserver);
    }
    public Entity(String spritePath, int x, int y) {
        this.sprite = new Sprite(new Texture(spritePath));
        gameObservers = new ArrayList<>();
        entityPositionGameObserver = new EntityPositionGameObserver(this);
        registerObserver(entityPositionGameObserver);
    }

    @Override
    public void act(float delta) {

    }
    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public abstract void update();

    public float getX(){
        return sprite.getX();
    }
    public float getY(){
        return sprite.getY();
    }
    public int getSpeed(){
        return speed;
    }

    @Override
    public void registerObserver(GameObserver gameObserver){
        gameObservers.add(gameObserver);
    }

    @Override
    public void contactObserver(String action){

    }

    public void setSpriteColor(){
        sprite.setColor(Color.RED);
    }

}
