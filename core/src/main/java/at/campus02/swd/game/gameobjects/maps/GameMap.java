package at.campus02.swd.game.gameobjects.maps;

import at.campus02.swd.game.EnemyProximtyDetector;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.entities.Entity;
import at.campus02.swd.game.gameobjects.entities.Player;
import at.campus02.swd.game.gameobjects.factories.EntityFactory;
import at.campus02.swd.game.gameobjects.factories.TileFactory;
import at.campus02.swd.game.gameobjects.tile.Tile;

import java.util.ArrayList;

public class GameMap{

    private ArrayList<GameObject> terrainObjects;
    private ArrayList<Entity> entityObjects;
    private ArrayList<GameObject> structureObjects;

    private TileFactory tileFactory;
    private EntityFactory entityFactory;

    private Entity player1;
    private Entity enemy1;
    private Entity enemy2;

    private int width;
    private int height;

    private EnemyProximtyDetector enemyProximtyDetector;

    public GameMap(){
        terrainObjects = new ArrayList<>();
        entityObjects = new ArrayList<>();
        structureObjects = new ArrayList<>();

        tileFactory = new TileFactory();
        entityFactory = new EntityFactory();

        width = 1280;
        height = 720;

        water();
        littleIsland(-350, 200);
        littleIsland(-350, -400);
        fortifiedGrasIsland(450, -256);

        spawnPlayer();
        spawnEnemies();

        enemyProximtyDetector = new EnemyProximtyDetector(entityObjects,(Player) player1);
    }

    private void water(){

        for (float i = -width; i < width; i= i+64) {
            for (float j = -height; j < height; j = j+64) {
                Tile wt = tileFactory.create("water-left");
                wt.setPosition(i,j);
                terrainObjects.add(wt);
            }
        }
    }

    private void littleIsland(int x, int y){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile wt;
                if(i == 0){
                    wt = tileFactory.create("sand-leftLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomleftCorner");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topLeftCorner");
                    }
                }
                else if(i == 3){
                    wt = tileFactory.create("sand-rightLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomRightCorner");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topRightCorner");
                    }
                }
                else if(i > 0 && i < 3){
                    if(j == 0){
                        wt = tileFactory.create("sand-bottomLane");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topLane");
                    }
                    else{
                        wt = tileFactory.create("sand-middlePiece");
                    }
                }
                else{
                    wt = tileFactory.create("sand-middlePiece");
                }
                if (i == 1 && j == 0) {
                    wt = tileFactory.create("sand-dump");
                } else if (i == 2 && j == 0) {
                    wt = tileFactory.create("sand-boat");
                }
                wt.setPosition(x+i*64,y+j*64);
                terrainObjects.add(wt);
            }
        }

        //must create twice otherwise accessoirs will overrite the tiles
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Tile wt;
                if(i == 0){
                    wt = tileFactory.create("sand-leftLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomleftCorner");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topLeftCorner");
                    }
                }
                else if(i == 3){
                    wt = tileFactory.create("sand-rightLane");
                    if(j== 0){
                        wt = tileFactory.create("sand-bottomRightCorner");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topRightCorner");
                    }
                }
                else if(i > 0 && i < 3){
                    if(j == 0){
                        wt = tileFactory.create("sand-bottomLane");
                    }
                    else if(j == 3){
                        wt = tileFactory.create("sand-topLane");
                    }
                    else{
                        wt = tileFactory.create("sand-middlePiece");
                    }
                }
                else{
                    wt = tileFactory.create("sand-middlePiece");
                }
                if (i == 1 && j == 0) {
                    wt = tileFactory.create("sand-dump");
                } else if (i == 2 && j == 0) {
                    wt = tileFactory.create("sand-boat");
                }else if (i == 1 && j == 3) {
                    wt = tileFactory.create("grass-littleStone");
                }
                wt.setPosition(x+i*64,y+j*64);
                terrainObjects.add(wt);
            }
        }
    }

    private void fortifiedGrasIsland(int x, int y){

        //grassIsland
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                Tile wt;
                if(i == 0){
                    wt = tileFactory.create("grass-leftLane");
                    if(j== 0){
                        wt = tileFactory.create("grass-bottomleftCorner");
                    }
                    else if(j == 8){
                        wt = tileFactory.create("grass-topLeftCorner");
                    }
                }
                else if(i == 8){
                    wt = tileFactory.create("grass-rightLane");
                    if(j== 0){
                        wt = tileFactory.create("grass-bottomRightCorner");
                    }
                    else if(j == 8){
                        wt = tileFactory.create("grass-topRightCorner");
                    }
                }
                else if(i > 0 && i < 8){
                    if(j == 0){
                        wt = tileFactory.create("grass-bottomLane");
                    }
                    else if(j == 8){
                        wt = tileFactory.create("grass-topLane");
                    }
                    else{
                        wt = tileFactory.create("grass-middlePiece");
                    }
                }
                else{
                    wt = tileFactory.create("grass-middlePiece");
                }

                if (i == 5 && j == 5){
                    wt = tileFactory.create("grass-middleStony");
                }
                /*if (i == 0 && (j == 2 || j == 4 || j == 6)) {
                    wt = tileFactory.create("grass-bigBush");
                }*/
                wt.setPosition(x+i*64,y+j*64);
                terrainObjects.add(wt);
            }
        }

        int x1 = x-4;
        int y1 = y-6;

        //castle
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                Tile t;
                if(i == 1){ //left side
                    t = tileFactory.create("castle-leftRightLane");
                    if(j== 1){
                        t = tileFactory.create("castle-bottomleftCorner");
                    }
                    if(j == 2 || j == 6) {
                        t = tileFactory.create("castle-leftCanon");
                    }
                    if(j == 4) {
                        t = tileFactory.create("castle-leftRightDoor");
                    }
                    else if(j == 7){
                        t = tileFactory.create("castle-topLeftCorner");
                    }
                }
                else if(i == 7){ //right side
                    t = tileFactory.create("castle-leftRightLane");
                    if(j == 1){
                        t = tileFactory.create("castle-bottomRightCorner");
                    }
                    else if(j == 7){
                        t = tileFactory.create("castle-topRightCorner");
                    }
                }
                else if(i > 1 && i < 7){
                    if(j == 1){
                        t = tileFactory.create("castle-bottomTopLane");
                    }
                    else if(j == 7){
                        t = tileFactory.create("castle-bottomTopLane");
                    }
                    else{
                        t = tileFactory.create("grass-middlePiece");
                    }
                } else{
                    t = tileFactory.create("grass-middlePiece");
                }
                if ((i == 2 || i == 6) && j == 7) { //top canons
                    t = tileFactory.create("castle-topCanon");
                }
                else if ((i == 2 || i == 6) && j == 1) { //bottom cannons
                    t = tileFactory.create("castle-bottomCanon");
                } else if ((i == 2 || i ==6) && (j == 3 || j == 6)) { //accessoires
                    t = tileFactory.create("grass-middleStony");
                }

                t.setPosition(x1+i*64,y1+j*64);
                terrainObjects.add(t);
            }
        }
    }

    private void spawnPlayer(){
        /*
        Entity player1 = entityFactory.create("player-green");
        Entity player2 = entityFactory.create("player-blue");
        Entity player3 = entityFactory.create("player-red");
        player1.setPosition(-700,-200);
        player2.setPosition(-700, 0);
        player3.setPosition(-700, 200);
        entityObjects.add(player1);
        entityObjects.add(player2);
        entityObjects.add(player3);
        */
        player1 = entityFactory.create("player-green");
        player1.setPosition(-700,-200);
        entityObjects.add(player1);
    }

    private void spawnEnemies(){

        /*Entity enemy1 = entityFactory.create("enemy-black");
        Entity enemy2 = entityFactory.create("player-black");
        Entity enemy3 = entityFactory.create("player-black");*/
        enemy1 = entityFactory.create("enemy-black");
        enemy2 = entityFactory.create("enemy-black");
        enemy1.setPosition(300,-300);
        enemy2.setPosition(100, 250);
        entityObjects.add(enemy1);
        entityObjects.add(enemy2);
    }

    public ArrayList<GameObject> getTerrainObjects(){
        return terrainObjects;
    }

    public ArrayList<Entity> getEntityObjects(){
        return entityObjects;
    }

    public ArrayList<GameObject> getStructureObjects(){
        return structureObjects;
    }
    public Entity getPlayer1(){
        return player1;
    }


    public void entityDetector(){
        enemyProximtyDetector.detect();
    }
    
    public Entity getEnemy1() {
        return enemy1;
    }

    public Entity getEnemy2() {
        return enemy2;
    }

}
