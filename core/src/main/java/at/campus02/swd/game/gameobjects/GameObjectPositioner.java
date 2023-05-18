package at.campus02.swd.game.gameobjects;

/**
 * Translates lines and columns of the board to the cartesian coordinates used when drawing the tile.
 */
public class GameObjectPositioner {
    private final int viewportWidth;
    private final int viewportHeight;
    private final int gameObjectSize;

    public GameObjectPositioner(int viewportWidth, int viewportHeight, int gameObjectSize) {
        this.viewportWidth = viewportWidth;
        this.viewportHeight = viewportHeight;
        this.gameObjectSize = gameObjectSize;
    }

    public void setPosition(GameObject gameObject, int column, int line) {
        gameObject.setPosition(translateColumnToX(column), translateLineToY(line));
    }

    private float translateColumnToX(int column) {
        float offsetX = viewportWidth / 2f;
        return column * gameObjectSize - offsetX;
    }

    private float translateLineToY(int line) {
        float offsetY = viewportHeight / 2f;
        // (line + 1) because we need to refer to the location of the *bottom* left corner of the image
        // return the negativ value because on the cartesian coordinate system our lines grow in the negative direction
        return -((line + 1) * gameObjectSize - offsetY);
    }
}
