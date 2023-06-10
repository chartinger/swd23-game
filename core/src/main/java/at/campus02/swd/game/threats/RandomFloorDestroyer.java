package at.campus02.swd.game.threats;

import at.campus02.swd.game.board.BoardView;
import at.campus02.swd.game.board.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class RandomFloorDestroyer implements ThreatStrategy {
    private final Random randomNumberGenerator = new Random();
    private final BoardView board;
    private final int tilesPerRound;

    public static ThreatStrategy.Builder withTilesPerRound(int tilesPerRound) {
        return new Builder(tilesPerRound);
    }

    private RandomFloorDestroyer(BoardView board, int tilesPerRound) {
        this.board = board;
        this.tilesPerRound = tilesPerRound;
    }

    @Override
    public List<Position> wreakHavoc() {
        Set<Position> damage = new HashSet<>();
        for (int i = 0; i < tilesPerRound; i++)
            damage.add(getRandomFloorToBeDestroyed(damage));
        damage.remove(null);
        return List.copyOf(damage);
    }

    private Position getRandomFloorToBeDestroyed(Set<Position> damageDealt) {
        if (!hasDestructibleFields(damageDealt))
            return null;

        Position position = getRandomPosition();
        while (!isDestructible(damageDealt, position)) {
            position = getRandomPosition();
        }

        return position;
    }

    private Position getRandomPosition() {
        int column = randomNumberGenerator.nextInt(board.getWidth());
        int row = randomNumberGenerator.nextInt(board.getHeight());
        return new Position(column, row);
    }

    private boolean hasDestructibleFields(Set<Position> damageDealt) {
        for (int column = 0; column < board.getWidth(); column++)
            for (int row = 0; row < board.getHeight(); row++)
                if (isDestructible(damageDealt, new Position(column, row)))
                    return true;
        return false;
    }

    private boolean isDestructible(Set<Position> damageDealt, Position position) {
        return !board.isPlayer(position)
            && !board.isFinish(position)
            && !board.isDeadly(position)
            && !damageDealt.contains(position);
    }


    private static class Builder implements ThreatStrategy.Builder {
        private final int tilesPerRound;

        public Builder(int tilesPerRound) {
            this.tilesPerRound = tilesPerRound;
        }

        @Override
        public RandomFloorDestroyer forBoard(BoardView gameBoard) {
            return new RandomFloorDestroyer(gameBoard, tilesPerRound);
        }
    }
}
