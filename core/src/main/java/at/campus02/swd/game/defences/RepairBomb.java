package at.campus02.swd.game.defences;

import at.campus02.swd.game.game.AidPack;
import at.campus02.swd.game.game.BoardView;
import at.campus02.swd.game.game.DefenceStrategy;
import at.campus02.swd.game.util.Position;

import java.util.*;

public class RepairBomb implements DefenceStrategy {
    private final BoardView board;

    public static DefenceStrategy.Builder builder() {
        return RepairBomb::new;
    }

    private RepairBomb(BoardView board) {
        this.board = Objects.requireNonNull(board);
    }

    @Override
    public AidPack restoreChaos() {
        AidPack aidPack = new AidPack(Collections.emptyList(), 0);
        Position playerPosition = board.getPlayerPosition();
        for (int radius = 0; aidPack.repairs().isEmpty() && radius < Math.max(board.getWidth(), board.getHeight()); radius++)
            aidPack = restoreRadius(playerPosition, radius);
        return aidPack;
    }

    private AidPack restoreRadius(Position playerPosition, int radius) {
        List<Position> repairs = new ArrayList<>();
        for (int column = playerPosition.column() - radius; column <= playerPosition.column() + radius; column++)
            for (int row = playerPosition.row() - radius; row <= playerPosition.row() + radius; row++)
                computeRepair(new Position(column, row))
                    .ifPresent(repairs::add);
        return new AidPack(repairs, radius);
    }

    private Optional<Position> computeRepair(Position position) {
        return board.isDeadly(position)
            ? Optional.of(position)
            : Optional.empty();
    }
}
