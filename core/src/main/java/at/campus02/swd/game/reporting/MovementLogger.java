package at.campus02.swd.game.reporting;

import at.campus02.swd.game.board.MovementObserver;

import java.io.PrintStream;

public class MovementLogger implements MovementObserver {
    PrintStream logger;

    public MovementLogger(PrintStream logger) {
        this.logger = logger;
    }

    @Override
    public void updatePosition(int column, int row) {
        logger.println("You are at [" + column + ", " + row + "]");
    }
}
