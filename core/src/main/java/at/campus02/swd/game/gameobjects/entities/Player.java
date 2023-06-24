package at.campus02.swd.game.gameobjects.entities;

import at.campus02.swd.game.gameobjects.entities.controller.Controller;

public class Player extends Entity{

    private Controller controller;

    private boolean boolEnemyDetected;
    private Entity enemyDetected;

    public Player(String spritePath){
        super(spritePath);
        entityType = "Player";
        controller = new Controller(this);
        speed = 4;
        boolEnemyDetected = false;
    }

    @Override
    public void update(){
        controller.readKeyPress();
        controller.kill(enemyDetected);
    }

    public void setBoolEnemyDetected(boolean enemyDetected){
        this.boolEnemyDetected = enemyDetected;
    }

    public void setEnemyDetected(Entity enemyDetected){
        this.enemyDetected = enemyDetected;
    }
}
