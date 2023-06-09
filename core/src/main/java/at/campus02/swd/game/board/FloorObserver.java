package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

public interface FloorObserver {
    void updateFloor(Action action, Position position);

    enum Action {
        CREATE, DESTROY
    }
}
