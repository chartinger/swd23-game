package at.campus02.swd.game.strategy.input;

import at.campus02.swd.game.gameobjects.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    private Player player;
    private MoveCommand moveUpCommand;
    private MoveCommand moveDownCommand;
    private MoveCommand moveLeftCommand;
    private MoveCommand moveRightCommand;
    private AttackCommand meleeAttackCommand;

    public GameInput(Player player) {
        this.player = player;
        this.moveUpCommand = new MoveUpCommand(player);
        this.moveDownCommand = new MoveDownCommand(player);
        this.moveLeftCommand = new MoveLeftCommand(player);
        this.moveRightCommand = new MoveRightCommand(player);
        this.meleeAttackCommand = new MeleeAttackCommand(player);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                moveUpCommand.execute();
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                moveLeftCommand.execute();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                moveDownCommand.execute();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveRightCommand.execute();
                break;
            case Input.Keys.SPACE:
                meleeAttackCommand.attack();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                moveUpCommand.cancelMovement();
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                moveLeftCommand.cancelMovement();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                moveDownCommand.cancelMovement();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveRightCommand.cancelMovement();
                break;
        }
        return true;
    }

}


