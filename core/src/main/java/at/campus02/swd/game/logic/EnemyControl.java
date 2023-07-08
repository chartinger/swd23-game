
package at.campus02.swd.game.logic;
import at.campus02.swd.game.gameobjects.EnemyObjects.Enemy;
import at.campus02.swd.game.gameobjects.EnemyObjects.EnemyObserver;

import java.util.Random;

import java.util.ArrayList;

public class EnemyControl {

    public static boolean killEnemySpawn = false;
    public static boolean spawnEnemy = false;
    private static EnemyControl enemyControl = null;

    public static int _numberEnemies = 2;

    private final Control control;

    public Observer eObserver;

    private final float stepSize = 5.0F;

    public ArrayList<Enemy> _enemies = new ArrayList<>();

    private EnemyControl(Control control)  {
        this.control = control;
        eObserver = new EnemyObserver();
    }

    public void run(){
        Runnable spawnTimer = new Runnable() {
            public void run() {
                enemySpawnTimer();
            }
        };

        new Thread(spawnTimer).start();
    }

    public void deleteEnemyFromList(Enemy enemy){
        if(_enemies.contains(enemy))
            _enemies.remove(enemy);
    }
    private void enemySpawnTimer(){
        while(!EnemyControl.killEnemySpawn) {
            try {
                Thread.sleep(2000);
                Random rand = new Random();
                int spawnChance = 0;
                spawnChance = rand.ints(0,99).limit(100).findFirst().orElse(0);
                //System.out.println("SpawnChance: " + spawnChance + "SpawnEnemy; " + spawnEnemy + "Number of Enemies" + _numberEnemies);
                if(spawnChance > 50)
                    EnemyControl.spawnEnemy = true;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void alterLeft(Enemy enemy){
        AlterLeft.execute(enemy, stepSize);
        WriteLog(eObserver, enemy.getPositionX(), enemy.getPositionY());
    }

    public void alterRight(Enemy enemy){
        AlterRight.execute(enemy, stepSize);
        WriteLog(eObserver, enemy.getPositionX(), enemy.getPositionY());
    }

    public void alterDown(Enemy enemy){
        AlterDown.execute(enemy, stepSize);
        WriteLog(eObserver, enemy.getPositionX(), enemy.getPositionY());
    }

    public void alterUp(Enemy enemy){
        AlterUp.execute(enemy, stepSize);
        WriteLog(eObserver, enemy.getPositionX(), enemy.getPositionY());
    }

    static public EnemyControl instance(Control control) {
        if (enemyControl == null) {
            enemyControl = new EnemyControl(control);
        }
        return enemyControl;
    }

    public Enemy AddEnemy(){
        Enemy newEnemy = new Enemy();
        _enemies.add(newEnemy);
        Random rand = new Random();
        float x =  control.player.getPositionX() + (float)rand.ints(-100,100).limit(10000).findFirst().getAsInt();
        float y =  (control.player.getPositionX() + (float)rand.ints(-100,100).limit(10000).findFirst().getAsInt());
        newEnemy.setPosition(x,y);
        return newEnemy;
    }

    public void MoveEnemies(){
        _enemies.forEach((temp) -> {
            MoveEnemy(temp);
            CheckForPlayerInteraction();
        });

    }
    private void MoveEnemy(Enemy enemy){
        if(control.player.getPositionX() < enemy.getPositionX())
            alterLeft(enemy);
        if(control.player.getPositionY() < enemy.getPositionY())
            alterDown(enemy);
        if(control.player.getPositionX() > enemy.getPositionX())
            alterRight(enemy);
        if(control.player.getPositionY() > enemy.getPositionY())
            alterUp(enemy);
        WriteLog(eObserver, enemy.getPositionX(), enemy.getPositionY());
    }

    private void CheckForPlayerInteraction(){
        _enemies.forEach(control::CheckPlayerAgainstEnemy);
    }

    private void WriteLog(Observer observer, float x, float y){
        observer.PushAction("Enemy: ", x,y);
    }
}

