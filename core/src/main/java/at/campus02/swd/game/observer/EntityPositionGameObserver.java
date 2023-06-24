package at.campus02.swd.game.observer;

import at.campus02.swd.game.gameobjects.entities.Entity;

public class EntityPositionGameObserver implements GameObserver{

    private Entity entity;

    public EntityPositionGameObserver(Entity entity){
        this.entity = entity;
    }

    public float getY(){

        return entity.getY();
    }

    public float getX(){
        return entity.getX();
    }

}
