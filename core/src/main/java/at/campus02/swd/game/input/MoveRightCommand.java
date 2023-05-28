package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Player;

public class MoveRightCommand implements MoveCommand {
    private Player player;

    public MoveRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        float posx = player.getSprite().getX();
        float posy = player.getSprite().getY();
        player.setPosition(posx + 10.0f, posy);
        player.setRotation(90.0f);
    }
}
