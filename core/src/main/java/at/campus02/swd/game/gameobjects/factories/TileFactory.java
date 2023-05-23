package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.Main;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.tile.*;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class TileFactory implements Factory{

    @Override
    public Tile create(String type) {
        return returnTileBasedOnType(type);
    }

    @Override
    public void initialize() {

    }

    @Override
    public Array<GameObject> getObjects() {
        return new Array<>();
    }

    public Tile returnTileBasedOnType(String type){
        String myType = type.split("-")[0];

        switch(myType.toLowerCase()){
            case "water":
                return new WaterTile(findTileGraphicPath(type));
            case "sand":
                return new SandTile(findTileGraphicPath(type));
            case "grass":
                return new GrassTile(findTileGraphicPath(type));
            default:
                return null;
        }
    }

    public String findTileGraphicPath(String type){

        String[] types = type.split("-");

        switch(types[0].toLowerCase()){
            case "water":
                switch(types[1]){
                    case "right":
                        return "tiles/mapTile_171.png";
                    case "left":
                        return "tiles/mapTile_187.png";
                    case "still":
                        return "tiles/mapTile_188.png";
                }
            case "sand":
                switch(types[1]){
                    //corners
                    case "topLeftCorner":
                        return "tiles/tile_01.png";
                    case "topRightCorner":
                        return "tiles/tile_03.png";
                    case "bottomleftCorner":
                        return "tiles/tile_33.png";
                    case "bottomRightCorner":
                        return "tiles/tile_35.png";
                    //lanes (water touching land)
                    case "topLane":
                        return "tiles/tile_02.png";
                    case "bottomLane":
                        return "tiles/tile_34.png";
                    case "leftLane":
                        return "tiles/tile_17.png";
                    case "rightLane":
                        return  "tiles/tile_19.png";
                    //middle piece
                    case "middlePiece":
                        return "tiles/tile_18.png";
                }
            case "grass":
                switch(types[1]){
                    //to do
                }
            default:
                return null;
        }

    }
}
