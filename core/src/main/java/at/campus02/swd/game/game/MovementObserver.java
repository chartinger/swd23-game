package at.campus02.swd.game.game;

import at.campus02.swd.game.util.Position;

@FunctionalInterface
public interface MovementObserver {
    void updatePosition(Position position);
}
