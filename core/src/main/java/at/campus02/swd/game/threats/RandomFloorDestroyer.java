package at.campus02.swd.game.threats;

import at.campus02.swd.game.game.BoardView;
import at.campus02.swd.game.game.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class RandomFloorDestroyer implements ThreatStrategy {
    private final Random randomNumberGenerator = new Random();
    private final BoardView board;
    private final int tilesPerRound;

    public static ThreatStrategy.Builder withTilesPerRound(int tilesPerRound) {
        return board -> new RandomFloorDestroyer(board, tilesPerRound);
    }

    private RandomFloorDestroyer(BoardView board, int tilesPerRound) {
        this.board = Objects.requireNonNull(board);
        this.tilesPerRound = tilesPerRound;
    }

    @Override
    public List<Position> wreakHavoc() {
        Set<Position> damage = new HashSet<>();
        for (int i = 0; i < tilesPerRound; i++)
            computeDamage(damage).ifPresent(damage::add);
        return List.copyOf(damage);
    }

    private Optional<Position> computeDamage(Set<Position> damageDealt) {
        if (!hasDestructibleFields(damageDealt))
            return Optional.empty();

        Position position = getRandomPosition();
        while (!isDestructible(damageDealt, position)) {
            position = getRandomPosition();
        }

        return Optional.of(position);
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
}
