package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

public interface MovementObserver {
    void updatePosition(Position position);
}
