package at.campus02.swd.game.observer;

public class EnemyPositionObserver implements PositionObserver{


    @Override
    public void updatePosition(float x, float y, float rotation) {
        System.out.println("x, y, rotation = " + x +", "+ y +", "+ rotation);
    }
}
