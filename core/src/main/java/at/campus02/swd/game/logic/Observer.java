package at.campus02.swd.game.logic;


import java.util.ArrayList;

public abstract class Observer
{
    private final ArrayList<String> ActionsList;

    public Observer(){
        ActionsList = new ArrayList<>();
    }

    public void PushAction(String action){
        this.ActionsList.add(action);
    }

    public void PushAction(String type, float x, float y){
        this.ActionsList.add(type + " Position: [x: " + x + "] [y: " + y + "]." );;
    }

    public ArrayList<String> GetActionList(){
        return ActionsList;
    }

    public void Print(){
        for (String e:ActionsList) {
            System.out.println(e);
        }
    }
}
