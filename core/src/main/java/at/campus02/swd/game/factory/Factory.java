package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;

public abstract class Factory {
    public GameObject create(Type type, int x, int y){
        return createGameObject(type,x,y);
    }
    protected abstract GameObject createGameObject(Type type, int x, int y);


}
