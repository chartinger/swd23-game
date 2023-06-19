package at.campus02.swd.game.gameobjects;

import javax.print.attribute.standard.PDLOverrideSupported;

public interface PositionObserver {

    void update(Observable observable);

    void update(float x, float y);

    // diese Methoden eigentlich nicht mehr notwendig
    float updateX(Observable observable);
    float updateX(float x);
    float updateY(Observable observable);
    float updateY(float y);
}
