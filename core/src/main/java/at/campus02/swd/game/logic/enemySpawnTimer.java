package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.EnemyObjects.Enemy;
import at.campus02.swd.game.gameobjects.EnemyObjects.EnemyObserver;

import java.util.ArrayList;
import java.util.Random;

public class enemySpawnTimer extends Thread{

   public enemySpawnTimer(){
       while(!EnemyControl.killEnemySpawn) {
           try {
               Thread.sleep(5000);
               Random rand = new Random();
               int spawnChance = 0;
               spawnChance = rand.ints(0,99).limit(100).findFirst().orElse(0);
               if(spawnChance > 50)
                   EnemyControl.spawnEnemy = true;

           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
   }
}

