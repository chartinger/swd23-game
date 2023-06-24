package at.campus02.swd.game.input;


import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.PlayerBoy;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    private Command moveUpCommand;
    private Command moveDownCommand;
    private Command moveLeftCommand;
    private Command moveRightCommand;
    private GameObject player;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMovingLeft;
    private boolean isMovingRight;


    public void setPlayer(GameObject player) {
        this.player = player;
    }

    public void setMoveUpCommand(Command moveUpCommand) {
        this.moveUpCommand = moveUpCommand;
    }

    public void setMoveDownCommand(Command moveDownCommand) {
        this.moveDownCommand = moveDownCommand;
    }

    public void setMoveLeftCommand(Command moveLeftCommand) {
        this.moveLeftCommand = moveLeftCommand;
    }

    public void setMoveRightCommand(Command moveRightCommand) {
        this.moveRightCommand = moveRightCommand;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                isMovingUp = true;
                moveUpCommand.execute(player);
                return true;
            case Input.Keys.DOWN:
                isMovingDown = true;
                moveDownCommand.execute(player);
                return true;
            case Input.Keys.LEFT:
                isMovingLeft = true;
                moveLeftCommand.execute(player);
                return true;
            case Input.Keys.RIGHT:
                isMovingRight = true;
                moveRightCommand.execute(player);
                return true;
            default:
                return false;
        }
    }
    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                isMovingUp = false;
                return true;
            case Input.Keys.DOWN:
                isMovingDown = false;
                return true;
            case Input.Keys.LEFT:
                isMovingLeft = false;
                return true;
            case Input.Keys.RIGHT:
                isMovingRight = false;
                return true;
            default:
                return false;
        }
    }
    public void update(float delta) {
        if (isMovingUp) {
            moveUpCommand.execute(player);
        }
        if (isMovingDown) {
            moveDownCommand.execute(player);
        }
        if (isMovingLeft) {
            moveLeftCommand.execute(player);
        }
        if (isMovingRight) {
            moveRightCommand.execute(player);
        }
    }


}






