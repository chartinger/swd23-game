package at.campus02.swd.game.observer;

import java.util.ArrayList;

public interface GameObservable{

    void registerObserver(GameObserver gameObserver);

    void contactObserver(String action);

}
