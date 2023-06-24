package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.entities.Enemy;
import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.gameobjects.entities.Player;

import java.util.ArrayList;

public class EnemyProximtyDetector{

    ArrayList<Entity> entities;

    Player player;

    public EnemyProximtyDetector(ArrayList<Entity> entities, Player player){
        this.entities = entities;
        this.player = player;
    }

    public void detect(){
        for(Entity e : entities){
            if(e.entityType.equals("Player")){
                continue;
            }

            //System.out.println("detecting entity: "+e.entityType);
            System.out.println("playerX: "+player.getX() + "  -   enemyX: "+e.getX());


            if(Math.abs(player.getX() - e.getX()) <= 128){
                if(Math.abs(player.getY() -e.getY()) <= 128){
                    System.out.println("proximity detected!");
                    player.setBoolEnemyDetected(true);
                    player.setEnemyDetected(e);
                    e.setSpriteColor();
                }
            }
            else{
                player.setBoolEnemyDetected(false);
            }


        }
    }


}
