package at.campus02.swd.game.threats;

import at.campus02.swd.game.board.Board;
import at.campus02.swd.game.board.BoardView;
import at.campus02.swd.game.board.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFloorDestroyer implements ThreatStrategy {
    private final Random randomNumberGenerator = new Random();
    private final int tilesPerRound;

    public RandomFloorDestroyer(int tilesPerRound) {
        this.tilesPerRound = tilesPerRound;
    }

    @Override
    public List<Position> wreakHavoc(BoardView gameBoard) {
        List<Position> damage = new ArrayList<>();
        for (int i = 0; i < tilesPerRound; i++)
            damage.add(getRandomFloorToBeDestroyed(gameBoard));
        return damage;
    }

    private Position getRandomFloorToBeDestroyed(BoardView board) {
        if (!hasDestructibleFields(board))
            return null;

        Position position = getRandomPosition();
        while (!isDestructible(board, position)) {
            position = getRandomPosition();
        }

        return position;
    }

    private Position getRandomPosition() {
        int column = randomNumberGenerator.nextInt(Board.BOARD_WIDTH);
        int row = randomNumberGenerator.nextInt(Board.BOARD_HEIGHT);
        return new Position(column, row);
    }

    private boolean hasDestructibleFields(BoardView board) {
        for (int column = 0; column < Board.BOARD_WIDTH; column++)
            for (int row = 0; row < Board.BOARD_HEIGHT; row++)
                if (isDestructible(board, new Position(column, row)))
                    return true;
        return false;
    }

    private boolean isDestructible(BoardView board, Position position) {
        return !board.isPlayer(position)
            && !board.isFinish(position)
            && !board.isDeadly(position);
    }
}
