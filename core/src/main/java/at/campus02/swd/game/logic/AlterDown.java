package at.campus02.swd.game.logic;

import at.campus02.swd.game.gameobjects.GameObject;

public class AlterDown extends AlterInterface {

    static public void execute(GameObject object){
        object.setPosition(object.getPositionX(), object.getPositionY() - stepSize);
    }

    static public void execute(GameObject object, float stepSize){
        object.setPosition(object.getPositionX(), object.getPositionY() - stepSize);
    }
}
