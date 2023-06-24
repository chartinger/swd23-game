package at.campus02.swd.game.gameobjects.EnemyObjects;


import at.campus02.swd.game.logic.Observer;

import java.util.ArrayList;

public class EnemyObserver extends Observer {

    private final ArrayList<String> ActionsList;

    public EnemyObserver(){
        ActionsList = new ArrayList<>();
        ActionsList.add("Log of Interactive Objects Actions");
    }
}
