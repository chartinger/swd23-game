package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Enemy;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.commands.*;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    private Player player;

    private Enemy enemy;

    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;

    private Command attackCommand;


    public GameInput(Player player, Enemy enemy) {
        this.player = player;
        this.moveUpCommand = new MoveUpCommand(player);
        this.moveDownCommand = new MoveDownCommand(player);
        this.moveLeftCommand = new MoveLeftCommand(player);
        this.moveRightCommand = new MoveRightCommand(player);
        this.attackCommand = new AttackCommand(player, enemy);

    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case 19:
                System.out.println(keycode);
                moveUpCommand.execute();
                break;
            case 20:
                System.out.println(keycode);
                moveDownCommand.execute();
                break;
            case 21:
                System.out.println(keycode);
                moveLeftCommand.execute();
                break;
            case 22:
                System.out.println(keycode);
                moveRightCommand.execute();
                attackCommand.execute();
                break;
            case 23:
                System.out.println(keycode);
                attackCommand.execute();
                break;
            default:
                break;

        }
        return true;
    }
}
