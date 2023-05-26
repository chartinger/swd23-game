package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.entities.Enemy;
import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.gameobjects.entities.Player;
import com.badlogic.gdx.utils.Array;

public class EntityFactory implements Factory{

    @Override
    public Entity create(String type) {
        return returnEntityBasedOnType(type);
    }

    @Override
    public void initialize() {

    }

    @Override
    public Array<GameObject> getObjects() {
        return new Array<>();
    }

    public Entity returnEntityBasedOnType(String type){
        String myType = type.split("-")[0];

        switch(myType.toLowerCase()){
            case "player":
                return new Player(findEntityGraphicPath(type));
            case "enemy":
                return new Enemy(findEntityGraphicPath(type));
            default:
                return null;
        }
    }

    public String findEntityGraphicPath(String type){

        String[] types = type.split("-");

        switch(types[0].toLowerCase()){
            case "player":
                switch(types[1]){
                    case "yellow":
                        return "sprites/Ships/ship (6).png";
                    case "red":
                        return "sprites/Ships/ship (3).png";
                    case "green":
                        return "sprites/Ships/ship (4).png";
                    case "blue":
                        return "sprites/Ships/ship (5).png";
                }
            case "enemy":
                switch(types[1]){
                    case "white":
                        return "sprites/Ships/ship (1).png";
                    case "black":
                        return "sprites/Ships/ship (2).png";
                }
            default:
                return null;
        }

    }


}
