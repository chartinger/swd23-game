package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;

public abstract class Factory {
    public GameObject create(String type){
        return createGameObject(type);
    }
    protected abstract GameObject createGameObject(String type);
}
