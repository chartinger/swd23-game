package at.campus02.swd.game.gameobjects;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.scenes.scene2d.ui.Label;


public class UIPositionObserver implements PositionObserver {
    private Label positionLabel;

    public UIPositionObserver(Label positionLabel) {
        this.positionLabel = positionLabel;
    }

    @Override
    public void updatePosition(int x, int y) {
        String positionText = "UI-Position: x=" + x + ", y=" + y;
        positionLabel.setText(positionText);
    }
}
