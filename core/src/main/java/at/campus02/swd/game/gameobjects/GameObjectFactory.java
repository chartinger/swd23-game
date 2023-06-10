package at.campus02.swd.game.gameobjects;

public interface GameObjectFactory<E extends Enum<E>> {
    GameObject create(E type);
}
