package at.campus02.swd.game.reporting;

import at.campus02.swd.game.board.BudgetObserver;
import at.campus02.swd.game.board.MovementObserver;
import at.campus02.swd.game.util.Position;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreBoard implements MovementObserver, BudgetObserver {
    private final float x;
    private final float y;
    private final BitmapFont font;

    private Position playerPosition = null;
    private int budget = 0;

    public ScoreBoard(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, "Player Position: " + playerPosition + "     Budget: " + budget, x, y);
    }

    @Override
    public void updatePosition(Position position) {
        this.playerPosition = position;
    }

    @Override
    public void updateBudget(int budget) {
        this.budget = budget;
    }
}
