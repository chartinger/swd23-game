package at.campus02.swd.game.gameobjects.entities;

public class Enemy extends Entity{

    public Enemy(String spritePath){
        super(spritePath);
    }

    @Override
    public void update(){

        if(super.getY() == 250) {
            speed = -2;

        } else if (super.getY() == -300) {
            speed = 2;
        }
        super.setPosition(super.getX(), super.getY() + super.getSpeed());

    }
}
