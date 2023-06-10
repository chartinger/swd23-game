package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

import java.util.List;

public interface DefenceStrategy {
    List<Position> restoreChaos();

    interface Builder {
        DefenceStrategy forBoard(BoardView gameBoard);
    }
}
