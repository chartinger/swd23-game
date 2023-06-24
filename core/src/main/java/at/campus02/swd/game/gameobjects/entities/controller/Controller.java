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

    private ConsoleGameObserver consoleGameObserver;

    public Controller(Entity entity){
        this.entity = entity;
        gameObservers = new ArrayList<>();
        registerObserver(new ConsoleGameObserver(this.entity));
        consoleGameObserver = new ConsoleGameObserver(this.entity);
    }


    public void readKeyPress(){

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            entity.setPosition(entity.getX()+ entity.getSpeed(), entity.getY());
            //contactObserver("onPlayerMovedRight");
            //consoleGameObserver.onPlayerMovedRight();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            entity.setPosition(entity.getX()- entity.getSpeed(), entity.getY());
            //contactObserver("onPlayerMovedLeft");
            //consoleGameObserver.onPlayerMovedLeft();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            entity.setPosition(entity.getX(), entity.getY()+ entity.getSpeed());
            //contactObserver("onPlayerMovedUp");
            //consoleGameObserver.onPlayerMovedUp();
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            entity.setPosition(entity.getX(), entity.getY()- entity.getSpeed());
            //contactObserver("onPlayerMovedDown");
            //consoleGameObserver.onPlayerMovedDown();
        }


    }


    @Override
    public void registerObserver(GameObserver gameObserver){
        gameObservers.add(gameObserver);
    }

    @Override
    public void contactObserver(String action){

    }
}
