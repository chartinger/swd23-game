package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.entities.Entity;

public class ConsoleGameObserver implements GameObserver{

    private Entity entity;

    public ConsoleGameObserver(Entity entity){
        this.entity = entity;
    }


    public void printPlayerPosition(){
        System.out.println("position: x:" + entity.getX() + " y: "+entity.getY());
    }


    public void onPlayerMovedUp(){
        System.out.println("player moves up");
        printPlayerPosition();

    }


    public void onPlayerMovedDown(){
        System.out.println("player moves down");
        printPlayerPosition();
    }


    public void onPlayerMovedLeft(){
        System.out.println("player moves left");
        printPlayerPosition();
    }

    
    public void onPlayerMovedRight(){
        System.out.println("player moves right");
        printPlayerPosition();
    }


}
