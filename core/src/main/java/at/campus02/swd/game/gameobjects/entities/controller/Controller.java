package at.campus02.swd.game.gameobjects.entities.controller;

import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.observer.ConsoleGameObserver;
import at.campus02.swd.game.observer.GameObservable;
import at.campus02.swd.game.observer.GameObserver;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Controller implements GameObservable{

    private Entity entity;
    private ArrayList<GameObserver> gameObservers;

    public Controller(Entity entity){
        this.entity = entity;
        gameObservers = new ArrayList<>();
        registerObserver(new ConsoleGameObserver(this.entity));
    }


    public void readKeyPress(){

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            entity.setPosition(entity.getX()+ entity.getSpeed(), entity.getY());
            contactObserver("onPlayerMovedRight");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            entity.setPosition(entity.getX()- entity.getSpeed(), entity.getY());
            contactObserver("onPlayerMovedLeft");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            entity.setPosition(entity.getX(), entity.getY()+ entity.getSpeed());
            contactObserver("onPlayerMovedUp");
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            entity.setPosition(entity.getX(), entity.getY()- entity.getSpeed());
            contactObserver("onPlayerMovedDown");
        }


    }

    public void kill(Entity e) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            e.setPosition(e.getX() + 999999999, e.getY());
        }
    }


    @Override
    public void registerObserver(GameObserver gameObserver){
        gameObservers.add(gameObserver);
    }

    @Override
    public void contactObserver(String action){
        for(GameObserver go : gameObservers){
            switch(action){
                case "onPlayerMovedRight":
                    go.onPlayerMovedRight();
                    return;
                case "onPlayerMovedLeft":
                    go.onPlayerMovedLeft();
                    return;
                case "onPlayerMovedUp":
                    go.onPlayerMovedUp();
                    return;
                case "onPlayerMovedDown":
                    go.onPlayerMovedDown();
                    return;
            }
        }

    }
}
