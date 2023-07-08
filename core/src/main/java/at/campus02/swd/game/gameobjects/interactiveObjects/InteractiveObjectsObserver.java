package at.campus02.swd.game.gameobjects.interactiveObjects;


import at.campus02.swd.game.logic.Observer;

import java.util.ArrayList;

public class InteractiveObjectsObserver extends Observer {

    private final ArrayList<String> ActionsList;

    public InteractiveObjectsObserver(){
        ActionsList = new ArrayList<>();
        ActionsList.add("Log of Interactive Objects Actions");
    }
}
