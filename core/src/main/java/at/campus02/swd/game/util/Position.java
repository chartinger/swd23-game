package at.campus02.swd.game.util;

public record Position(int column, int row) {
    @Override
    public String toString() {
        return "[" + column + ", " + row + "]";
    }
}
