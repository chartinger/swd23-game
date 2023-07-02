
package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.PlayerBoy;
import at.campus02.swd.game.gameobjects.Tile;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

import java.util.Observable;
import java.util.Observer;

public class UIObserver implements Observer {
    private String ausgabeAction;
    private String ausgabePosition;


    public String getAusgabeAction() {
        return ausgabeAction;
    }




    public String getAusgabePosition() {
        return ausgabePosition;
    }



    public void update(Observable o, Object arg) {
        if (o instanceof PlayerBoy) {
            PlayerBoy playerBoy = (PlayerBoy) o;
            String action = (String) arg;


            // Ausgabe der Informationen in der UI
            ausgabeAction = "Aktion: " + action;
            ausgabePosition = "Position: (" + playerBoy.getPositionX() + ", " + playerBoy.getPositionY() + ")";

        }
    }

}

