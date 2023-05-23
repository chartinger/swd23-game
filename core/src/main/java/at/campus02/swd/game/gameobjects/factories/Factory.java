package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.gameobjects.GameObject;

import java.util.ArrayList;

public interface Factory {

    public GameObject create(String tile);

    public void initialize();

    public ArrayList<GameObject> getObjects();
}
