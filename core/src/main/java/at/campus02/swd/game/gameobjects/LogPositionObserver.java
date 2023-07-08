package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.gameobjects.GameObject;

public class LogPositionObserver implements PositionObserver {
    private int lastX;
    private int lastY;

    @Override
    public void updatePosition(int x, int y, String identifier) {
        if (x != lastX || y != lastY) {
            System.out.println(identifier + " Log-Position: x=" + x + ", y=" + y);
            lastX = x;
            lastY = y;
        }
    }
    public int[] getPosition(){
        return new int[]{lastX,lastY};
    }
}
