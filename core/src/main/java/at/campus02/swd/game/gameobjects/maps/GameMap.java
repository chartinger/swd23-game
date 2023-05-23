package at.campus02.swd.game.gameobjects.maps;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.gameobjects.entities.Player;
import at.campus02.swd.game.gameobjects.factories.EntityFactory;
import at.campus02.swd.game.gameobjects.factories.TileFactory;
import at.campus02.swd.game.gameobjects.tile.Tile;

import java.util.ArrayList;

public class GameMap{

    private ArrayList<GameObject> terrainObjects;
    private ArrayList<GameObject> entityObjects;
    private ArrayList<GameObject> structureObjects;

    private TileFactory tileFactory;
    private EntityFactory entityFactory;

    private int width;
    private int height;

    public GameMap(){
        terrainObjects = new ArrayList<>();
        entityObjects = new ArrayList<>();
        structureObjects = new ArrayList<>();

        tileFactory = new TileFactory();
        entityFactory = new EntityFactory();

        width = 1280;
        height = 720;

        water();
        middleIsland();

        spawnPlayer();
        spawnEnemies();
    }

    private void water(){
        //water
        for (float i = -width; i < width; i= i+64) {
            for (float j = -height; j < height; j = j+64) {
                Tile wt = tileFactory.create("water-still");
                wt.setPosition(i,j);
                terrainObjects.add(wt);
            }
        }
    }

    private void middleIsland(){

        int x = -256;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Tile wt;
                if(i == 0){
                    wt = tileFactory.create("sand-leftLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomleftCorner");
                    }
                    else if(j == 7){
                        wt = tileFactory.create("sand-topLeftCorner");
                    }
                }
                else if(i == 7){
                    wt = tileFactory.create("sand-rightLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomRightCorner");
                    }
                    else if(j == 7){
                        wt = tileFactory.create("sand-topRightCorner");
                    }
                }
                else if(i > 0 && i < 7){
                    if(j == 0){
                        wt = tileFactory.create("sand-bottomLane");
                    }
                    else if(j == 7){
                        wt = tileFactory.create("sand-topLane");
                    }
                    else{
                        wt = tileFactory.create("sand-middlePiece");
                    }
                }
                else{
                    wt = tileFactory.create("sand-middlePiece");
                }
                wt.setPosition(x+i*64,x+j*64);
                terrainObjects.add(wt);
            }
        }
    }

    private void spawnPlayer(){
        Entity player1 = entityFactory.create("player-green");
        Entity player2 = entityFactory.create("player-blue");
        Entity player3 = entityFactory.create("player-red");
        player1.setPosition(-400,-300);
        player2.setPosition(-500, -200);
        player3.setPosition(-500, -50);
        entityObjects.add(player1);
        entityObjects.add(player2);
        entityObjects.add(player3);
    }

    private void spawnEnemies(){
        Entity enemy1 = entityFactory.create("enemy-black");
        Entity enemy2 = entityFactory.create("player-black");
        Entity enemy3 = entityFactory.create("player-black");
        enemy1.setPosition(400,300);
        enemy2.setPosition(500, 200);
        enemy3.setPosition(600, 300);
        entityObjects.add(enemy1);
        entityObjects.add(enemy2);
        entityObjects.add(enemy3);
    }

    public ArrayList<GameObject> getTerrainObjects(){
        return terrainObjects;
    }

    public ArrayList<GameObject> getEntityObjects(){
        return entityObjects;
    }

    public ArrayList<GameObject> getStructureObjects(){
        return structureObjects;
    }
}
