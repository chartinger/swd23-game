package at.campus02.swd.game.movement;

import at.campus02.swd.game.gameobjects.Player;

public class MoveDownCommand implements Command {

    public void MoveDownCommand(Player player) {
    }

    private Player player;

    public void MoveUpCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        float move = 20.0f;
        player.setPosition(player.getPositionX(), player.getPositionY() - move);
    }

    public MoveDownCommand(Player player) {
        this.player = player;
    }

}
