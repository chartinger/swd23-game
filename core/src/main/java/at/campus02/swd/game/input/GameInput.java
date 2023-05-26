package at.campus02.swd.game.input;
import at.campus02.swd.game.playerobjects.PlayerOne;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    public PlayerOne player;

    private final float stepSize = 10.0F;
    @Override
    public boolean keyDown(int keycode) {

        if(keycode == 20) {
            player.setPosition(player.X(), player.Y() - stepSize );
        }

        if(keycode == 19) {
            player.setPosition(player.X(), player.Y() + stepSize );
        }

        if(keycode == 21) {
            player.setPosition(player.X()-stepSize, player.Y() );
        }

        if(keycode == 22) {
            player.setPosition(player.X() + stepSize, player.Y() );
        }

        if(keycode == 111) {
            System.exit(0);
        }
        System.out.println(keycode);
        return true;
    }
}
