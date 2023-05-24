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
            case "castle":
                return new CastleTile(findTileGraphicPath(type));
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
                    case "bright":
                        return "tiles/tile_73.png";

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
                        //accessoire
                    case "boat":
                        return "tiles/tile_81.png";
                    case "dump":
                        return "tiles/tile_83.png";
                }
            case "grass":
                switch(types[1]){
                    //corners
                    case "topLeftCorner":
                        return "tiles/tile_06.png";
                    case "topRightCorner":
                        return "tiles/tile_09.png";
                    case "bottomleftCorner":
                        return "tiles/tile_54.png";
                    case "bottomRightCorner":
                        return "tiles/tile_57.png";
                    //lanes (water touching land)
                    case "topLane":
                        return "tiles/tile_08.png";
                    case "bottomLane":
                        return "tiles/tile_55.png";
                    case "leftLane":
                        return "tiles/tile_22.png";
                    case "rightLane":
                        return  "tiles/tile_25.png";
                    //middle piece
                    case "middlePiece":
                        return "tiles/tile_23.png";
                    case "middleStony":
                        return "tiles/tile_24.png";
                    //accessoires
                    case "bigBush":
                        return "tiles/tile_71.png";
                    case "triangleBush":
                        return "tiles/tile_70.png";
                    case "littleBush":
                        return "tiles/tile_72.png";
                    case "littleStone":
                        return "tiles/tile_49.png";
                }
            case "castle":
                switch(types[1]) {
                    //corners
                    case "topLeftCorner":
                        return "tiles/tile_77.png";
                    case "topRightCorner":
                        return "tiles/tile_78.png";
                    case "bottomleftCorner":
                        return "tiles/tile_93.png";
                    case "bottomRightCorner":
                        return "tiles/tile_94.png";
                    //lanes
                    case "bottomTopLane":
                        return "tiles/tile_16.png";
                    case "leftRightLane":
                        return "tiles/tile_15.png";
                    //canons
                    case "topCanon":
                        return "tiles/tile_47.png";
                    case "bottomCanon":
                        return "tiles/tile_48.png";
                    case "leftCanon":
                        return "tiles/tile_32.png";
                    case "rightCanon":
                        return  "tiles/tile_31.png";
                    //door
                    case "bottomTopDoor":
                        return "tiles/tile_76.png";
                    case "leftRightDoor":
                        return "tiles/tile_60.png";

                }

            default:
                return null;
        }

    }
}
