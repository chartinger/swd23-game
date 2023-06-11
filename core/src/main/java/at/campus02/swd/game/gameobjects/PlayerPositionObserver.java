package at.campus02.swd.game.gameobjects;

public class PlayerPositionObserver implements PositionObserver {


    @Override
    public void update(float x, float y) {

        System.out.println("hat sich nach (" + x + "/" + y + ") bewegt.");

    }

    public PlayerPositionObserver() {
    }
}
