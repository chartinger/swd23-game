package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.Player;

public class MoveDownCommand implements MoveCommand {
    private Player player;

    public MoveDownCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        float posx = player.getSprite().getX();
        float posy = player.getSprite().getY();
        player.setPosition(posx, posy - 10.0f);
        player.setRotation(0.0f);
    }
}
