package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.Player;

public class MoveRightCommand implements Command{
    private Player player;

    public void MoveRightCommand(Player player){
        this.player = player;
    }

    @Override
    public void execute() {
        float move = 20.0f;
        player.setPosition(player.getPositionX()+ move, player.getPositionY());
    }


    public MoveRightCommand(Player player) {
        this.player = player;
    }

}
