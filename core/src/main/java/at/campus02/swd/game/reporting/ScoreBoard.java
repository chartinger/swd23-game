package at.campus02.swd.game.reporting;

import at.campus02.swd.game.game.BudgetObserver;
import at.campus02.swd.game.game.FloorObserver;
import at.campus02.swd.game.game.MovementObserver;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreBoard implements MovementObserver, BudgetObserver, FloorObserver {
    private final float x;
    private final float y;
    private final BitmapFont font;

    private Position playerPosition = null;
    private int budget = 0;
    private int repairCounter = 0;

    public ScoreBoard(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

    public void draw(SpriteBatch batch) {
        String hudTemplate = "Player Position: %s     Budget: %d     Repaired Tiles: %d";
        String hud = String.format(hudTemplate, playerPosition, budget, repairCounter);
        font.draw(batch, hud, x, y);
    }

    @Override
    public void updatePosition(Position position) {
        this.playerPosition = position;
    }

    @Override
    public void updateBudget(int budget) {
        this.budget = budget;
    }

    @Override
    public void updateFloor(Action action, Position position) {
        if (Action.CREATE.equals(action))
            repairCounter++;
    }
}
