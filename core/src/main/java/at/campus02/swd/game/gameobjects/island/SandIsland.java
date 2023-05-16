package at.campus02.swd.game.gameobjects.island;

import at.campus02.swd.game.gameobjects.GameObject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class SandIsland implements GameObject {

    private ArrayList<GameObject> gameObjectArray = new ArrayList<>();

    public float x;
    public float y;

    SandLeftLower sandLeftLower = new SandLeftLower();
    SandMidLower sandMidLower = new SandMidLower();
    SandRightLower sandRightLower = new SandRightLower();
    SandLeftUpper sandLeftUpper = new SandLeftUpper();
    SandMidUpper sandMidUpper = new SandMidUpper();
    SandRightUpper sandRightUpper = new SandRightUpper();
    SandLeftMiddle sandLeftMiddle = new SandLeftMiddle();
    SandRightMiddle sandRightMiddle = new SandRightMiddle();
    SandMidMiddle sandMidMiddle = new SandMidMiddle();

    public SandIsland(int x, int y) {

        this.x = x;
        this.y = y;

        updateRealtivePosition();


        gameObjectArray.add(sandLeftLower);
        gameObjectArray.add(sandMidLower);
        gameObjectArray.add(sandRightLower);
        gameObjectArray.add(sandLeftUpper);
        gameObjectArray.add(sandMidUpper);
        gameObjectArray.add(sandRightUpper);
        gameObjectArray.add(sandLeftMiddle);
        gameObjectArray.add(sandRightMiddle);
        gameObjectArray.add(sandMidMiddle);

    }

    public ArrayList<GameObject> getGameObjectArray(){
        return gameObjectArray;
    }

    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
        updateRealtivePosition();
    }

    public void updateRealtivePosition(){

        System.out.println("x: "+x);
        System.out.println("y: "+y);

        sandLeftLower.setPosition(x+0, y-128);
        sandMidLower.setPosition(x+64, y-128);
        sandRightLower.setPosition(x+128, y-128);
        sandLeftUpper.setPosition(x+0, y+0);
        sandMidUpper.setPosition(x+64,y+0);
        sandRightUpper.setPosition(x+128, y+0);
        sandLeftMiddle.setPosition(x+0, y-64);
        sandRightMiddle.setPosition(x+64, y-64);
        sandMidMiddle.setPosition(x+128, y-64);
    }

    @Override
    public void draw(SpriteBatch batch) {

    }
}

