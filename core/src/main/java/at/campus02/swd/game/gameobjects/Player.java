package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class Player implements GameObject, Observable{

    private Texture image;
    private Sprite sprite;

    private List<PositionObserver> oberservers = new ArrayList<PositionObserver>();
    private PlayerPositionObserver playerPositionObserver = new PlayerPositionObserver();

    private PlayerPositionLogObserver playerPositionLogObserver = new PlayerPositionLogObserver();




    public Player(Texture image) {
        this.image = image;
        this.sprite = new Sprite(image);

        addObserver(playerPositionObserver);
        //addObserver(playerPositionLogObserver); //--> funktioniert noch nicht --> findet File nicht

    }


    public void addObserver(PositionObserver obs) {
        this.oberservers.add(obs);
    }

    public void removeObserver(PositionObserver obs) {
        this.oberservers.remove(obs);
    }


    @Override
    public void act(float delta) {
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);

        // update() Oberservers -> ?
        for (PositionObserver oberserver : oberservers) {

            System.out.print("Spieler ");
            oberserver.update(x,y);

        }
    }

    public float getPositionX(){
       return sprite.getX();
    }

    public float getPositionY(){
        return sprite.getY();
    }


    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }


}
