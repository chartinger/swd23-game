package at.campus02.swd.game.commands;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.gameobjects.UIPositionObserver;

public class AttackCommand implements Command {
    private Player player;
    private Enemy enemy;

    public AttackCommand(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void execute() {

        if (player.getPositionX()- enemy.getPositionX() < 5 || player.getPositionY()-enemy.getPositionY() < 3){
            enemy.died();
        }

    }
}
