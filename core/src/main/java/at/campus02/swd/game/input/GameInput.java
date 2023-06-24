package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {
    private Player player;

    public GameInput(Player player) {
        this.player = player;
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                player.moveUp();
                break;
            case Input.Keys.DOWN:
                player.moveDown();
                break;
            case Input.Keys.LEFT:
                player.moveLeft();
                break;
            case Input.Keys.RIGHT:
                player.moveRight();
                break;
            default:
                break;
        }
        return true;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                player.stopMovingUp();
                break;
            case Input.Keys.DOWN:
                player.stopMovingDown();
                break;
            case Input.Keys.LEFT:
                player.stopMovingLeft();
                break;
            case Input.Keys.RIGHT:
                player.stopMovingRight();
                break;
            default:
                break;
        }
        return true;
    }
}
