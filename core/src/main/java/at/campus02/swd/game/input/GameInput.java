package at.campus02.swd.game.input;
import at.campus02.swd.game.logic.Control;
import com.badlogic.gdx.InputAdapter;

public class GameInput extends InputAdapter {

    public Control control;

    @Override
    public boolean keyDown(int keycode) {

        if(control.LockPosition)
            return true;

        if(keycode == 20) {
            control.alterDown();
        }

        if(keycode == 19) {
            control.alterUp();
        }

        if(keycode == 21) {
            control.alterLeft();
        }

        if(keycode == 22) {
            control.alterRight();
        }

        if(keycode == 111) {
            System.exit(0);
        }

        control.CheckPlayerAgainstInteractiveObject();
//        System.out.println(keycode);
        return true;
    }
}
