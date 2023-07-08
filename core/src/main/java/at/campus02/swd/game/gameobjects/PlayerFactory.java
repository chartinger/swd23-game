package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class PlayerFactory extends Factory {
    /**
     * Creates and Initializes a Player
     *
     * @return: Returns created Player
     */

    private String type;

    @Override
    public Player create() {
        Random random = new Random();
        int randomNumberX = random.nextInt(481) - 240;
        int randomNumberY = random.nextInt(481) - 240;
        Player player = new Player(AssetRepository.getInstance().getTexturePlayer());
        player.setPosition(randomNumberX,randomNumberY);
        return player;
    }
}
