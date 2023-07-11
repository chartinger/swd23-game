package at.campus02.swd.game.game;

import at.campus02.swd.game.util.Position;

@FunctionalInterface
public interface FloorObserver {
    void updateFloor(Action action, Position position);

    enum Action {
        CREATE, DESTROY
    }
}
