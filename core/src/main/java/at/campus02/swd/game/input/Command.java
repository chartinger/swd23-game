package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.PlayerBoy;

public interface Command {
    void execute(GameObject gameObject);
}
