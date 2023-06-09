package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.GameObject;

public class MoveRightCommand implements Command{
    public void execute(GameObject gameObject) {
        gameObject.moveRight();
    }
}
