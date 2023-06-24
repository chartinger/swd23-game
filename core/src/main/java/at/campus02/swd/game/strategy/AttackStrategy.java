package at.campus02.swd.game.strategy;

import at.campus02.swd.game.gameobjects.*;

import java.util.Random;


public class AttackStrategy implements MovementStrategy {

    private Enemy enemy;
    private Player player;

    public AttackStrategy(Enemy enemy, Player player) {
        this.enemy = enemy;
        this.player = player;
    }

    @Override
    public void execute() {
    // TODO: direkte Position vom Player und Gegner umschreiben auf den Observer!
        Random random = new Random();
        float move = random.nextInt(20) + 1;

            if (player.getPositionX() >enemy.getPositionX()){
                enemy.setPosition(enemy.getPositionX()+move, enemy.getPositionY());
            } else if (player.getPositionX() < enemy.getPositionX()) {
                enemy.setPosition(enemy.getPositionX()-move, enemy.getPositionY());
            }
            else {
                return;
            }

        if (player.getPositionY() > enemy.getPositionY()){
            enemy.setPosition(enemy.getPositionX(), enemy.getPositionY() +move);
        } else if (player.getPositionY() < enemy.getPositionY()) {
            enemy.setPosition(enemy.getPositionX(), enemy.getPositionY()-move);
        }
        else {
            return;
        }


    }
    }

