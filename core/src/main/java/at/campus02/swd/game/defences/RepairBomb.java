package at.campus02.swd.game.defences;

import at.campus02.swd.game.board.BoardView;
import at.campus02.swd.game.board.DefenceStrategy;
import at.campus02.swd.game.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RepairBomb implements DefenceStrategy {
    private final BoardView board;

    public static DefenceStrategy.Builder builder() {
        return RepairBomb::new;
    }

    private RepairBomb(BoardView board) {
        this.board = board;
    }

    @Override
    public List<Position> restoreChaos() {
        List<Position> repairs = new ArrayList<>();
        Position playerPosition = board.getPlayerPosition();
        for (int distance = 0; repairs.isEmpty() && distance < Math.max(board.getWidth(), board.getHeight()); distance++)
            for (int column = playerPosition.column() - distance; column <= playerPosition.column() + distance; column++)
                for (int row = playerPosition.row() - distance; row <= playerPosition.row() + distance; row++)
                    computeRepair(new Position(column, row)).ifPresent(repairs::add);
        return repairs;
    }

    private Optional<Position> computeRepair(Position position) {
        return board.isDeadly(position)
            ? Optional.of(position)
            : Optional.empty();
    }
}
