package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public interface Factory {


    GameObject create(String type);

    void initialize();

    Array<GameObject> getObjects();
}
