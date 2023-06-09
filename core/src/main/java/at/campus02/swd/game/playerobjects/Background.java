package at.campus02.swd.game.playerobjects;
import at.campus02.swd.game.gameobjects.staticObjects.Cliff;
import at.campus02.swd.game.gameobjects.staticObjects.Gras;
import at.campus02.swd.game.gameobjects.staticObjects.Tile;
import com.badlogic.gdx.utils.Array;
import at.campus02.swd.game.gameobjects.*;

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

        for(int i = - 240; i < 240; i +=64){
            cliff = new Cliff();
            cliff.setPosition(-240,i);
            gameObjects.add(cliff);
        }
        return gameObjects;
    }
}
