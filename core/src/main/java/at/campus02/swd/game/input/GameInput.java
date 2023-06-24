package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.movement.*;
import at.campus02.swd.game.strategy.AttackStrategy;
import at.campus02.swd.game.strategy.MovementStrategy;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    private Player player;

    private Enemy enemy;

    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;

    private MovementStrategy attackStrategy;

    public GameInput(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
        this.moveUpCommand = new MoveUpCommand(player);
        this.moveDownCommand = new MoveDownCommand(player);
        this.moveLeftCommand = new MoveLeftCommand(player);
        this.moveRightCommand = new MoveRightCommand(player);
        this.attackStrategy = new AttackStrategy(enemy,player);

    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 19:
                System.out.println(keycode);
                moveUpCommand.execute();
                attackStrategy.execute();
                break;
            case 20:
                System.out.println(keycode);
                moveDownCommand.execute();
                attackStrategy.execute();
                break;
            case 21:
                System.out.println(keycode);
                moveLeftCommand.execute();
                attackStrategy.execute();
                break;
            case 22:
                System.out.println(keycode);
                moveRightCommand.execute();
                attackStrategy.execute();
                break;

            default:
                break;

        }
        return true;
    }
}
