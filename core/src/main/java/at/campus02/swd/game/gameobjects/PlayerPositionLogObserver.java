package at.campus02.swd.game.gameobjects;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class PlayerPositionLogObserver implements PositionObserver{

    private Logger logger;

    private void setUp(){
        /**
        FileHandler fh;
        try {
            fh = new FileHandler("C:at/campus02/swd/game/gameobjects/playerPosition.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        logger.addHandler(fh);**/

    }


    @Override
    public void update(float x, float y) {

        logger.info("hat sich nach (" + x + "/" + y + ") bewegt.");

    }

    public PlayerPositionLogObserver() {
        this.logger = Logger.getLogger(Player.class.getSimpleName());

        setUp();
    }
}
