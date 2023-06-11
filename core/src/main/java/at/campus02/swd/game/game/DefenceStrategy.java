package at.campus02.swd.game.game;

@FunctionalInterface
public interface DefenceStrategy {
    AidPack restoreChaos();

    interface Builder {
        DefenceStrategy forBoard(BoardView gameBoard);
    }
}
