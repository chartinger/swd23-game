package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.PlayerBoy;
import at.campus02.swd.game.gameobjects.Tile;

import java.util.Observable;
import java.util.Observer;

public class ConsoleObserver implements Observer {


    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof PlayerBoy) {
            PlayerBoy playerBoy = (PlayerBoy) o;
            String action = (String) arg;

            // Ausgabe der Informationen auf der Konsole
            System.out.println("Aktion: " + action);
            System.out.println("Position: (" + playerBoy.getPositionX() + ", " + playerBoy.getPositionY() + ")");


        }
    }
}
