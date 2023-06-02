package at.campus02.swd.game.gameobjects.entities;

import at.campus02.swd.game.gameobjects.entities.controller.Controller;

public class Player extends Entity{

    private Controller controller;

    public Player(String spritePath){
        super(spritePath);
        controller = new Controller(this);
        speed = 4;
    }

    @Override
    public void update(){
        controller.readKeyPress();
    }

}
