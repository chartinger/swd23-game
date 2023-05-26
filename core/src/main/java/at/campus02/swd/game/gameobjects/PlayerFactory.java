package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class PlayerFactory extends Factory{
    /**
     * Creates and Initializes a Player
     *
     * @return: Returns created Player
     */

    @Override
    public Player create() {

        Player player = new Player(new Texture("sprites/Ship parts/hullLarge (1).png"));

        return player;
    }


}
