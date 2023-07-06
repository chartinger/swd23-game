package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;

public abstract class Factory {
    public GameObject create(Type type, int x, int y, int z) {
        return createGameObject(type, x, y, z);
    }

    protected abstract GameObject createGameObject(Type type, int x, int y, int z);


}
