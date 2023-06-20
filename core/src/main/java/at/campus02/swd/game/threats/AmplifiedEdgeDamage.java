package at.campus02.swd.game.threats;

import at.campus02.swd.game.game.BoardView;
import at.campus02.swd.game.game.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class AmplifiedEdgeDamage implements ThreatStrategy {
    private final Random randomNumberGenerator = new Random();
    private final BoardView board;
    private final int tilesPerRound;

    public static ThreatStrategy.Builder withTilesPerRound(int tilesPerRound) {
        return board -> new AmplifiedEdgeDamage(board, tilesPerRound);
    }

    private AmplifiedEdgeDamage(BoardView board, int tilesPerRound) {
        this.board = Objects.requireNonNull(board);
        this.tilesPerRound = tilesPerRound;
    }

    @Override
    public List<Position> wreakHavoc() {
        List<Position> candidateTiles = getCandidateTiles();
        List<Position> damage = new ArrayList<>();
        for (int i = 0; i < tilesPerRound; i++)
            computeDamage(candidateTiles).ifPresent(damage::add);
        return damage;
    }


    private Optional<Position> computeDamage(List<Position> candidateTiles) {
        if (candidateTiles.isEmpty())
            return Optional.empty();
        return Optional.of(drawCandidate(candidateTiles));
    }

    private Position drawCandidate(List<Position> candidateTiles) {
        int diceRoll = randomNumberGenerator.nextInt(candidateTiles.size());
        Position candidate = candidateTiles.remove(diceRoll);
        while (candidateTiles.remove(candidate)) ;
        return candidate;
    }


    private List<Position> getCandidateTiles() {
        List<Position> candidateTiles = new ArrayList<>();
        for (int column = 0; column < board.getWidth(); column++)
            for (int row = 0; row < board.getHeight(); row++) {
                Position candidate = new Position(column, row);
                if (isDestructible(candidate))
                    candidateTiles.addAll(getCandidateSet(candidate));
            }
        return candidateTiles;
    }

    private boolean isDestructible(Position candidate) {
        return !board.isPlayer(candidate)
            && !board.isFinish(candidate)
            && board.isDamageable(candidate);
    }

    private List<Position> getCandidateSet(Position candidate) {
        int edgeCount = 0;
        edgeCount += board.isDeadly(northOf(candidate)) ? 2 : 0;
        edgeCount += board.isDeadly(southOf(candidate)) ? 2 : 0;
        edgeCount += board.isDeadly(eastOf(candidate)) ? 2 : 0;
        edgeCount += board.isDeadly(westOf(candidate)) ? 2 : 0;
        edgeCount += board.isDeadly(northEastOf(candidate)) ? 1 : 0;
        edgeCount += board.isDeadly(northWestOf(candidate)) ? 1 : 0;
        edgeCount += board.isDeadly(southEastOf(candidate)) ? 1 : 0;
        edgeCount += board.isDeadly(southWestOf(candidate)) ? 1 : 0;
        List<Position> candidateSet = new ArrayList<>();
        if (randomNumberGenerator.nextDouble() < .1)
            candidateSet.add(candidate);
        for (int i = 0; i < edgeCount; i++)
            candidateSet.add(candidate);
        return candidateSet;
    }

    private static Position northOf(Position position) {
        return getOffset(position, 0, -1);
    }
    private static Position southOf(Position position) {
        return getOffset(position, 0, 1);
    }
    private static Position eastOf(Position position) {
        return getOffset(position, 1, 0);
    }
    private static Position westOf(Position position) {
        return getOffset(position, -1, 0);
    }
    private static Position northEastOf(Position position) {
        return getOffset(position, -1, -1);
    }
    private static Position northWestOf(Position position) {
        return getOffset(position, 1, -1);
    }
    private static Position southEastOf(Position position) {
        return getOffset(position, -1, 1);
    }
    private static Position southWestOf(Position position) {
        return getOffset(position, 1, 1);
    }
    private static Position getOffset(Position position, int offsetColumn, int offsetRow) {
        return new Position(position.column() + offsetColumn, position.row() + offsetRow);
    }
}
