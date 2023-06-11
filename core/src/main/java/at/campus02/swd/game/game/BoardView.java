package at.campus02.swd.game.game;

import at.campus02.swd.game.util.Position;

public interface BoardView {
    Position getPlayerPosition();
    Position getFinishPosition();
    boolean isPlayer(Position position);
    boolean isFinish(Position position);
    boolean isDeadly(Position position);
    boolean isOnBoard(Position position);
    int getWidth();
    int getHeight();
}
