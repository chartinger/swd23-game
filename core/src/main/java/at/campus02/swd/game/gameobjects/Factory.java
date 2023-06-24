package at.campus02.swd.game.gameobjects;

import java.lang.reflect.Type;

public abstract class Factory {

    protected String type;

    public abstract GameObject create();

}
