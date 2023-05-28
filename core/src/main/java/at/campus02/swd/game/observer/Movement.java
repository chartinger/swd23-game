package at.campus02.swd.game.observer;

import java.util.ArrayList;

public class Movement {
    private ArrayList<PositionObserver> observer = new ArrayList<>();

    public void registerObserver(PositionObserver positionObserver) {
        this.observer.add(positionObserver);
    }


    public void removeObserver(PositionObserver positionObserver) {
        this.observer.remove(positionObserver);
    }


    public void setPosition(int x, int y, float rotation) {
        for (PositionObserver positionObserver : observer) {
            positionObserver.updatePosition(x,y, rotation);
        }
    }

}
