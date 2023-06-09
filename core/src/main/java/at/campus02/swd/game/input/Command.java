package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.GameObject;

public interface Command {
    void execute(GameObject gameObject);
}
