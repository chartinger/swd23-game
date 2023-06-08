package at.campus02.swd.game.board;

public interface FloorObserver {
    void updateFloor(Action action, int column, int row);

    enum Action {
        CREATE, DESTROY
    }
}
