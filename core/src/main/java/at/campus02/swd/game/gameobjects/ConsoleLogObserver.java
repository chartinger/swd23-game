package at.campus02.swd.game.gameobjects;

public class ConsoleLogObserver implements PositionObserver {

    private float currentX;
    private float currentY;


    public void update(Observable observable) {

        currentX = observable.getPositionX();
        currentY = observable.getPositionY();

        System.out.println("Objekt hat sich nach (" + currentX + "/" + currentY + ") bewegt.");

    }

    @Override
    public void update(float x, float y) {
        currentX = x;
        currentY = y;

        System.out.println("Objekt hat sich nach (" + currentX + "/" + currentY + ") bewegt.");
    }

    @Override
    public float updateX(Observable observable) {
        currentX = observable.getPositionX();
        return currentX;
    }

    @Override
    public float updateX(float x) {
        currentX = x;
        return currentX;
    }

    @Override
    public float updateY(Observable observable) {
        currentY = observable.getPositionY();
        return currentY;
    }

    @Override
    public float updateY(float y) {
        currentY = y;
        return currentY;
    }

    public ConsoleLogObserver() {
    }
}
