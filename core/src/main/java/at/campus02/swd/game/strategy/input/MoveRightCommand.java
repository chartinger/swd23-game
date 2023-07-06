package at.campus02.swd.game.strategy.input;

import at.campus02.swd.game.gameobjects.Direction;
import at.campus02.swd.game.gameobjects.Player;

public class MoveRightCommand implements MoveCommand {
    private Player player;

    public MoveRightCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.setMovement(Direction.RIGHT);
    }

    @Override
    public void cancelMovement() {
        player.cancelMovement(Direction.RIGHT);
    }
}
