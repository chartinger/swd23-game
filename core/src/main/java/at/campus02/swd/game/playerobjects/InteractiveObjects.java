package at.campus02.swd.game.playerobjects;

import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.gameobjects.interactiveObjects.Coral;
import at.campus02.swd.game.gameobjects.interactiveObjects.InteractiveObject;
import at.campus02.swd.game.gameobjects.interactiveObjects.Rock;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class InteractiveObjects{

    public static ArrayList<InteractiveObject> _listOfObjects = new ArrayList<>();

    public Array<GameObject> Create(Array<GameObject> gameObjects){

        Rock rock = new Rock(40, -20);
        gameObjects.add(rock);

        Coral coral = new Coral(-30,-190);
        gameObjects.add(coral);
        return gameObjects;
    }
}
