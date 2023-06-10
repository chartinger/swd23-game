package at.campus02.swd.game.playerobjects;


import at.campus02.swd.game.logic.Observer;

import java.util.ArrayList;

public class PlayerObserver extends Observer {

    private final ArrayList<String> ActionsList;

    public PlayerObserver(){
        ActionsList = new ArrayList<>();
        ActionsList.add("Log of PlayerInputs");
    }
}
