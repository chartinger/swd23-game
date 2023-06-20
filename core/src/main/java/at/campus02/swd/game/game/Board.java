package at.campus02.swd.game.game;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.utils.Array;

public interface Board extends BoardView {
    void setPlayerPosition(Position playerPosition);
    void setFinishPosition(Position playerPosition);
    boolean destroyFloorTile(Position position);
    boolean restoreFloorTile(Position position);
    Array<GameObject> getGameObjects();
}
