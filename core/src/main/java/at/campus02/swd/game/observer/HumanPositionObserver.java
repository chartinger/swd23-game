package at.campus02.swd.game.observer;

public class HumanPositionObserver implements PositionObserver{


    @Override
    public void updatePosition(float x, float y, float rotation) {
        System.out.println("x, y, rotation = " + x +", "+ y +", "+ rotation);
    }
}
