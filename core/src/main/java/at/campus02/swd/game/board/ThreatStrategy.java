package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

import java.util.List;

@FunctionalInterface
public interface ThreatStrategy {
    /**
     * Determines floor tiles to be destroyed
     * @return Positions of floor tiles to be destroyed
     */
    List<Position> wreakHavoc();

    interface Builder {
        ThreatStrategy forBoard(BoardView gameBoard);
    }
}
