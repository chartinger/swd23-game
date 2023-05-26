package at.campus02.swd.game.playerobjects;
import com.badlogic.gdx.utils.Array;
import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.Game;

import java.util.ArrayList;

public class Background {

    public Array<GameObject> Create(Array<GameObject> gameObjects){

        Cliff cliff;
        Gras gras;

        for (int i = -240; i < 240; i+=64){
            for(int j = 240; j >-240;j-=64){
                Tile tile = new Tile();
                tile.setPosition(i,j-64);
                gameObjects.add(tile);
            }
        }

        for(int i = - 240; i < 240; i +=64){
            gras = new Gras();
            gras.setPosition(178, i);
            gameObjects.add(gras);
        }

        Rock rock = new Rock();
        rock.setPosition(40,-128);
        gameObjects.add(rock);

        Corals corals = new Corals();
        corals.setPosition(-30,-192);
        gameObjects.add(corals);

        for(int i = - 240; i < 240; i +=64){
            cliff = new Cliff();
            cliff.setPosition(-240,i);
            gameObjects.add(cliff);
        }
        return gameObjects;
    }
}
