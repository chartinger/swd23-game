package at.campus02.swd.game.input;

import at.campus02.swd.game.factory.PlayerFactory;
import at.campus02.swd.game.gameobjects.IslandTile;
import at.campus02.swd.game.gameobjects.Player;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameInput extends InputAdapter {

    private Player player;
    private MoveCommand moveUpCommand;
    private MoveCommand moveDownCommand;
    private MoveCommand moveLeftCommand;
    private MoveCommand moveRightCommand;

    public GameInput(Player player) {
        this.player = player;
        this.moveUpCommand = new MoveUpCommand(player);
        this.moveDownCommand = new MoveDownCommand(player);
        this.moveLeftCommand = new MoveLeftCommand(player);
        this.moveRightCommand = new MoveRightCommand(player);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
            case Input.Keys.UP:
                moveUpCommand.execute();
                break;
            case Input.Keys.A:
            case Input.Keys.LEFT:
                moveLeftCommand.execute();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                moveDownCommand.execute();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                moveRightCommand.execute();
                break;
        }
        return true;
    }


    //@Override
    //public boolean keyDown(int keycode) {
    //    System.out.println(keycode);
//
//
    //    switch (keycode)
    //    {
    //        case (51):
    //            // W-Key
    //            System.out.println("W");
    //            moveUp();
    //            break;
    //        case (29):
    //            System.out.println("A");
    //            moveLeft();
    //            break;
    //        case (47):
    //            System.out.println("S");
    //            moveRight();
    //            break;
    //        case (32):
    //            System.out.println("D");
    //            moveDown();
    //            break;
    //        case (19):
    //            System.out.println("UP");
    //            moveUp();
    //            break;
    //        case (21):
    //            System.out.println("Left");
    //            moveLeft();
    //            break;
    //        case (22):
    //            System.out.println("Right");
    //            moveRight();
    //            break;
    //        case (20):
    //            System.out.println("Down");
    //            moveRight();
    //            break;
    //    default:
    //    break;
    //    }
    //    return true;
//
//
    //    }
    //private static void moveUp(Player player,float posx,float posy) {
    //    player.setPosition(posx ,posy - 10.0f);
    //    player.setRotation(0.0f);
    //}
    //private static void moveDown(Player player,float posx,float posy) {
    //    player.setPosition(posx ,posy + 10.0f);
    //    player.setRotation(180.0f);
    //}
    //private static void moveLeft(Player player,float posx,float posy) {
    //    player.setPosition(posx - 10.0f,posy);
    //    player.setRotation(270.0f);
    //}
    //private static void moveRight(Player player,float posx,float posy) {
    //    player.setPosition(posx + 10.0f,posy);
    //    player.setRotation(90.0f);
    //}
}


