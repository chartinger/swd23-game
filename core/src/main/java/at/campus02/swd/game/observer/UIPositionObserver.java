package at.campus02.swd.game.observer;

public class UIPositionObserver implements PositionObserver {

    String pos;


    @Override
    public void updatePosition(float x, float y, float rotation) {
        pos = "x = " + x + ", y = " + y + ", rot = " + rotation;
    }

    public String getPos() {
        return pos;
    }
}
