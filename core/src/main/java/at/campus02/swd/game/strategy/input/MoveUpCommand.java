package at.campus02.swd.game.strategy.input;

import at.campus02.swd.game.gameobjects.Direction;
import at.campus02.swd.game.gameobjects.Player;

public class MoveUpCommand implements MoveCommand {
    private Player player;

    public MoveUpCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.setMovement(Direction.UP);
    }

    @Override
    public void cancelMovement() {
        player.cancelMovement(Direction.UP);
    }
}
