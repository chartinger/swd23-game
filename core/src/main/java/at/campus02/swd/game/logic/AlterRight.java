package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.GameObject;

public class AlterRight extends AlterInterface {

    static public void execute(GameObject object){
        object.setPosition(object.getPositionX() + stepSize , object.getPositionY() );
    }

    static public void execute(GameObject object, float stepSize){
        object.setPosition(object.getPositionX() + stepSize , object.getPositionY() );
    }
}
