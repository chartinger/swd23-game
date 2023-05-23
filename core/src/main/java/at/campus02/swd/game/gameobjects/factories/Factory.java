package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public interface Factory {


    public GameObject create(String type);

    public void initialize();

    public Array<GameObject> getObjects();
}
