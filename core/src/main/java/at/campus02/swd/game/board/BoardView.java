package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

public interface BoardView {
    Position getPlayerPosition();
    boolean isPlayer(Position position);
    void setPlayerPosition(Position playerPosition);
    Position getFinishPosition();
    boolean isFinish(Position position);
    boolean isDeadly(Position position);
}
