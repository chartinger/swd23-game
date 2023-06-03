package at.campus02.swd.game.observer;

public interface GameObserver{

    void onPlayerMovedUp();
    void onPlayerMovedDown();
    void onPlayerMovedLeft();
    void onPlayerMovedRight();
    void printPlayerPosition();

}
