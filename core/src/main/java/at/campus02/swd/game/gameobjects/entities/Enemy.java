package at.campus02.swd.game.gameobjects.entities;

public class Enemy extends Entity{

    private int stateOfStrategy;

    public Enemy(String spritePath){
        super(spritePath);
        stateOfStrategy = 1;
        super.speed = 2;

    }

    @Override
    public void update(){

        if(stateOfStrategy == 1) {

            if (super.getY() == 250) {
                speed = -2;

            } else if (super.getY() == -300) {
                speed = 2;
            }
            super.setPosition(super.getX(), super.getY() + super.getSpeed());
        } else if (stateOfStrategy == 2) {
            if (super.getX() == 400) {
                speed = -4;

            } else if (super.getX() == 20) {
                speed = 4;
            }
            super.setPosition(super.getX() + super.getSpeed(), super.getY());

        }

    }

    public int getStateOfStrategy() {
        return stateOfStrategy;
    }

    public void setStateOfStrategy(int stateOfStrategy) {
        this.stateOfStrategy = stateOfStrategy;
    }
}
