package at.campus02.swd.game.movement;

import at.campus02.swd.game.gameobjects.Player;

public class MoveLeftCommand implements Command {

    private Player player;

    public void MoveLeftCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        float move = 20.0f;
        player.setPosition(player.getPositionX() - move, player.getPositionY());
    }


    public MoveLeftCommand(Player player) {
        this.player = player;
    }

}
