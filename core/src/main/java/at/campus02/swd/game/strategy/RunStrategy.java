package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.UIPositionObserver;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class RunStrategy implements MovementStrategy {

    private Enemy enemy;
    private UIPositionObserver uiPositionObserver;
    private Timer attackTimer;

    public RunStrategy() {
        attackTimer = new Timer();
    }

    @Override
    public void execute(Enemy enemy, UIPositionObserver uiPositionObserver) {
        this.enemy = enemy;
        this.uiPositionObserver = uiPositionObserver;

        attackTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                performMovement();
            }
        }, 0, 500);
    }

    private void performMovement() {
        Random random = new Random();
        float moveX = random.nextInt(30) + 1;
        float moveY = random.nextInt(30) + 1;


        if (uiPositionObserver.getCurrentX() > enemy.getPositionX() && uiPositionObserver.getCurrentY() > enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() - moveX, enemy.getPositionY() - moveY);
        }

        else if (uiPositionObserver.getCurrentX() < enemy.getPositionX() && uiPositionObserver.getCurrentY() < enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() + moveX, enemy.getPositionY() + moveY);
        }

        else if (uiPositionObserver.getCurrentX() > enemy.getPositionX() && uiPositionObserver.getCurrentY() < enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() - moveX, enemy.getPositionY() + moveY);
        }

        else if (uiPositionObserver.getCurrentX() < enemy.getPositionX() && uiPositionObserver.getCurrentY() > enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() + moveX, enemy.getPositionY() - moveY);
        }

        else if (uiPositionObserver.getCurrentX() == enemy.getPositionX() && uiPositionObserver.getCurrentY() < enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX(), enemy.getPositionY() + moveY);
        }

        else if (uiPositionObserver.getCurrentX() == enemy.getPositionX() && uiPositionObserver.getCurrentY() > enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX(), enemy.getPositionY() - moveY);
        }

        else if (uiPositionObserver.getCurrentX() < enemy.getPositionX() && uiPositionObserver.getCurrentY() == enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() + moveX, enemy.getPositionY());
        }

        else if (uiPositionObserver.getCurrentX() > enemy.getPositionX() && uiPositionObserver.getCurrentY() == enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX() - moveX, enemy.getPositionY());
        }

        else{
            enemy.setPosition(enemy.getPositionX(), enemy.getPositionY());
        }
    }



    public void stopExecution() {
        attackTimer.cancel();
    }
}
