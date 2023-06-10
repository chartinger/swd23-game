package at.campus02.swd.game.threats;

import at.campus02.swd.game.board.BoardView;
import at.campus02.swd.game.board.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class RandomFloorDestroyer implements ThreatStrategy {
    private final Random randomNumberGenerator = new Random();
    private final int tilesPerRound;

    public RandomFloorDestroyer(int tilesPerRound) {
        this.tilesPerRound = tilesPerRound;
    }

    @Override
    public List<Position> wreakHavoc(BoardView gameBoard) {
        Set<Position> damage = new HashSet<>();
        for (int i = 0; i < tilesPerRound; i++)
            damage.add(getRandomFloorToBeDestroyed(gameBoard, damage));
        damage.remove(null);
        return List.copyOf(damage);
    }

    private Position getRandomFloorToBeDestroyed(BoardView board, Set<Position> damageDealt) {
        if (!hasDestructibleFields(board, damageDealt))
            return null;

        Position position = getRandomPosition(board);
        while (!isDestructible(board, damageDealt, position)) {
            position = getRandomPosition(board);
        }

        return position;
    }

    private Position getRandomPosition(BoardView board) {
        int column = randomNumberGenerator.nextInt(board.getWidth());
        int row = randomNumberGenerator.nextInt(board.getHeight());
        return new Position(column, row);
    }

    private boolean hasDestructibleFields(BoardView board, Set<Position> damageDealt) {
        for (int column = 0; column < board.getWidth(); column++)
            for (int row = 0; row < board.getHeight(); row++)
                if (isDestructible(board, damageDealt, new Position(column, row)))
                    return true;
        return false;
    }

    private boolean isDestructible(BoardView board, Set<Position> damageDealt, Position position) {
        return !board.isPlayer(position)
            && !board.isFinish(position)
            && !board.isDeadly(position)
            && !damageDealt.contains(position);
    }
}
