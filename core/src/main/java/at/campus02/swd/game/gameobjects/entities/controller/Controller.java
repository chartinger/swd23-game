package at.campus02.swd.game.gameobjects.entities.controller;

import at.campus02.swd.game.gameobjects.entities.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Controller{

    private Entity entity;

    public Controller(Entity entity){
        this.entity = entity;
    }


    public void readKeyPress(){

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            entity.setPosition(entity.getX()+ entity.getSpeed(), entity.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            entity.setPosition(entity.getX()- entity.getSpeed(), entity.getY());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.W)){
            entity.setPosition(entity.getX(), entity.getY()+ entity.getSpeed());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            entity.setPosition(entity.getX(), entity.getY()- entity.getSpeed());
        }


    }

}
