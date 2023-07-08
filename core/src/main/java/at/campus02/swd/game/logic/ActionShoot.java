package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.playerobjects.Player;

public class ActionShoot extends AlterInterface {

    static public void execute(GameObject object){
        Player.playerShoot = true;
    }
}
