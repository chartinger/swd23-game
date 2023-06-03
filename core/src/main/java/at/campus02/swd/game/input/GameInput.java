package at.campus02.swd.game.input;

import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameInput extends InputAdapter {
    private final Map<Integer, GameCommand> actions = new HashMap<>();

    @Override
    public boolean keyDown(int keycode) {
        GameCommand printKeycode = () -> System.out.println(keycode);
        actions.getOrDefault(keycode, printKeycode).run();
        return true;
    }

    public void addAction(int keycode, GameCommand action){
        if (actions.containsKey(keycode))
            throw new IllegalStateException("Keycode " + keycode + " already in use");
        actions.put(keycode, Objects.requireNonNull(action));
    }

}
