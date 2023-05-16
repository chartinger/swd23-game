package at.campus02.swd.game.gameobjects.island;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SandIsland implements GameObject {

    private ArrayList<GameObject> gameObjectArray = new ArrayList<>();

    private Texture image;
    private Sprite sprite;

    public SandIsland() {

        gameObjectArray.add(new SandLeftLower());
        gameObjectArray.add(new SandMidLower());
        gameObjectArray.add(new SandRightLower());
        gameObjectArray.add(new SandLeftUpper());
        gameObjectArray.add(new SandMidUpper());
        gameObjectArray.add(new SandRightUpper());
        gameObjectArray.add(new SandLeftMiddle());
        gameObjectArray.add(new SandMidMiddle());
        gameObjectArray.add(new SandRightMiddle());




    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
