package at.campus02.swd.game.gameobjects;

/**
 * Translates lines and columns of the board to the cartesian coordinates used when drawing the tile.
 */
public class GameObjectPositioner {
    private final int viewportWidth;
    private final int viewportHeight;
    private final int tileSize;

    public GameObjectPositioner(int viewportWidth, int viewportHeight, int tileSize) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.tileSize = tileSize;
    }

    public int translateToX(int column) {
        int offsetX = viewportWidth / 2;
        return column * tileSize - offsetX;
    }

    public int translateToY(int line) {
        int offsetY = viewportHeight / 2;
        // (line + 1) because we need to refer to the location of the *bottom* left corner of the image
        // return the negativ value because on the cartesian coordinate system our lines grow in the negative direction
        return -((line + 1) * tileSize - offsetY);
    }
}
