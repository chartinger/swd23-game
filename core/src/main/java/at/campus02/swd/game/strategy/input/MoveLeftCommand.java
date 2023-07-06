package at.campus02.swd.game.strategy.input;

import at.campus02.swd.game.gameobjects.Direction;
import at.campus02.swd.game.gameobjects.Player;

public class MoveLeftCommand implements MoveCommand {
    private Player player;

    public MoveLeftCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.setMovement(Direction.LEFT);
    }

    @Override
    public void cancelMovement() {
        player.cancelMovement(Direction.LEFT);
    }
}
