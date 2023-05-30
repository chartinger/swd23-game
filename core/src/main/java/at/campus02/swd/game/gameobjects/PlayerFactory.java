package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;

public class PlayerFactory {
    private Texture playerTexture;

    public PlayerFactory() {
        playerTexture = new Texture("sprites/mapTile_035.png");
    }

    public Player createPlayer() {
        return new Player(playerTexture);
    }
}
