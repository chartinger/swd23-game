package at.campus02.swd.game.gameobjects.entities;

public class Enemy extends Entity{

    public String enemyName;
    public Enemy(String spritePath){
        super(spritePath);
        entityType = "Enemy";
    }

    @Override
    public void update(){

    }




}
