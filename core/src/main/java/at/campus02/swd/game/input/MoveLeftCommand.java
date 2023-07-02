package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.GameObject;

public class MoveLeftCommand implements Command{
    public void execute(GameObject gameObject) {
        gameObject.moveLeft();
    }

}
