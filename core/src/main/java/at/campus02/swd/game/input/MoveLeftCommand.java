package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Player;

public class MoveLeftCommand implements MoveCommand {
    private Player player;

    public MoveLeftCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        float posx = player.getSprite().getX();
        float posy = player.getSprite().getY();
        if (player.isBorderCollision(posx - 10.0f, posy)) {
            player.setPosition(posx, posy);
        } else {
            player.setPosition(posx - 10.0f, posy);
            player.setRotation(270.0f);
        }
    }
}
