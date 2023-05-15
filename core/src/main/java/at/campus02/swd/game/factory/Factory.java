package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;

public abstract class Factory {
    public GameObject create(TileType type){
        return createGameObject(type);
    }
    protected abstract GameObject createGameObject(TileType type);
}
