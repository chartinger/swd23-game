package at.campus02.swd.game.defences;

import at.campus02.swd.game.board.BoardView;
import at.campus02.swd.game.board.DefenceStrategy;
import at.campus02.swd.game.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RestoreGun implements DefenceStrategy {
    private final BoardView board;
    private final Direction direction;

    public static DefenceStrategy.Builder pointing(Direction direction) {
        return board -> new RestoreGun(board, direction);
    }

    private RestoreGun(BoardView board, Direction direction) {
        this.board = board;
        this.direction = direction;
    }

    @Override
    public List<Position> restoreChaos() {
        Position playerPosition = board.getPlayerPosition();
        return switch (direction) {
            case NORTH -> restoreColumn(playerPosition.column(), 0, playerPosition.row());
            case SOUTH -> restoreColumn(playerPosition.column(), playerPosition.row(), board.getHeight() - 1);
            case EAST -> restoreRow(playerPosition.row(), playerPosition.column(), board.getWidth() - 1);
            case WEST -> restoreRow(playerPosition.row(), 0, playerPosition.column());
        };
    }

    private List<Position> restoreColumn(int column, int northRow, int southRow) {
        List<Position> restores = new ArrayList<>();
        for (int row = northRow; row <= southRow; row++)
            computeRestore(new Position(column, row)).ifPresent(restores::add);
        return restores;
    }

    private List<Position> restoreRow(int row, int westColumn, int eastColumn) {
        List<Position> restores = new ArrayList<>();
        for (int column = westColumn; column <= eastColumn; column++)
            computeRestore(new Position(column, row)).ifPresent(restores::add);
        return restores;
    }

    private Optional<Position> computeRestore(Position position) {
        return board.isDeadly(position)
            ? Optional.of(position)
            : Optional.empty();
    }
}
