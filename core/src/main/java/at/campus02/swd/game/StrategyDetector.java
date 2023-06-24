package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.entities.Enemy;
import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.gameobjects.entities.Player;

import java.util.ArrayList;

public class StrategyDetector{


    ArrayList<Entity> enemies;
    Player player;

    public StrategyDetector(ArrayList<Entity> enemies, Player player){
        this.enemies = enemies;
        this.player = player;
    }

    public void detect(){
        for(Entity e : enemies){
            if(e.entityType.equals("Player")){
                continue;
            }
            if(player.getX() >= 0){
                Enemy coolerEnemy = (Enemy)e;
                coolerEnemy.setStateOfStrategy(2);
            }
            else{
                Enemy coolerEnemy = (Enemy)e;
                coolerEnemy.setStateOfStrategy(1);
            }
        }
    }


}
