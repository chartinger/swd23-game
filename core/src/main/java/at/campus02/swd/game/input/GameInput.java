package at.campus02.swd.game.input;

import com.badlogic.gdx.InputAdapter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GameInput extends InputAdapter {
    private final Map<Integer, GameCommand> actions = new HashMap<>();

    @Override
    public boolean keyDown(int keycode) {
        // Comment for educational purposes:
        // - noOp is short for 'no operation', i.e.: do nothing (we could have called it 'doNothing', but 'noOp' is
        //   the idiomatic name in various branches of informatics, so let's stick with that)
        // - this is an explanatory variable, which means, it's only there to give the () -> {} construct a name
        // - it is used as a Null Object in the getOrDefault() call, so we don't need to check whether the keycode
        //   exists in our actions map or the result of a call to get()
        // - view this as an example on why explanatory variables make sense and how null objects can make your code
        //   more simple and easier to understand by reducing cyclomatic complexity
        //   (cyclomatic complexity is a measure on how many ways you have to execute your code (the less, the better))
        GameCommand noOp = () -> {};
        actions.getOrDefault(keycode, noOp).run();
        return true;
    }

    public void addAction(int keycode, GameCommand action){
        if (actions.containsKey(keycode))
            throw new IllegalStateException("Keycode " + keycode + " already in use");
        actions.put(keycode, Objects.requireNonNull(action));
    }

}
