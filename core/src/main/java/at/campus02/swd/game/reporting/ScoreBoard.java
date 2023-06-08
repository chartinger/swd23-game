package at.campus02.swd.game.reporting;

import at.campus02.swd.game.board.MovementObserver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScoreBoard implements MovementObserver {
    private int playerColumn = -1;
    private int playerRow = -1;
    private final float x;
    private final float y;
    private final BitmapFont font;

    public ScoreBoard(float x, float y) {
        this.x = x;
        this.y = y;
        font = new BitmapFont();
        font.setColor(Color.GREEN);
    }

    public void draw(SpriteBatch batch) {
        font.draw(batch, "Player Position: [" + playerColumn + ", " + playerRow + "]", x, y);
    }

    @Override
    public void updatePosition(int column, int row) {
        playerColumn = column;
        playerRow = row;
    }
}
