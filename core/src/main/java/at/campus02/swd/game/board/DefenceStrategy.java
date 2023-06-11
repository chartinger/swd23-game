package at.campus02.swd.game.board;

public interface DefenceStrategy {
    AidPack restoreChaos();

    interface Builder {
        DefenceStrategy forBoard(BoardView gameBoard);
    }
}
