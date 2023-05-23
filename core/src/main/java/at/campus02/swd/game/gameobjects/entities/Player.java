package at.campus02.swd.game.gameobjects.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity{

    public Player(String spritePath){
        super(spritePath);
        this.speed = 4;
    }

    @Override
    public void update(){
        checkUserInput();
        setPosition(this.x,this.y);
    }

    private void checkUserInput(){

        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            this.x++;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            this.x--;
        }
    }

}
