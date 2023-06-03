package at.campus02.swd.game.gameobjects.factories;

import at.campus02.swd.game.AssetRepository;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.tile.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;


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
                return new AssetRepository();
            case "sand":
                return new SandTile(findTileTexture(type));
            case "grass":
                return new GrassTile(findTileTexture(type));
            case "castle":
                return new CastleTile(findTileTexture(type));
            default:
                return null;
        }
    }

    public Texture findTileTexture(String type){

        String[] types = type.split("-");

        switch(types[0].toLowerCase()){
            case "water":
                switch(types[1]){
                    case "right":
                        return new AssetRepository().getWaterRight();
                    case "left":
                        return new AssetRepository().getWaterLeft();
                    case "still":
                        return new AssetRepository().getWaterStill();
                    case "bright":
                        return new AssetRepository().getWaterBright();
                }
            case "sand":
                switch(types[1]){
                    //corners
                    case "topLeftCorner":
                        return new AssetRepository().getSandTopLeftCorner();
                    case "topRightCorner":
                        return new AssetRepository().getSandTopRightCorner();
                    case "bottomleftCorner":
                        return new AssetRepository().getSandBottomLeftCorner();
                    case "bottomRightCorner":
                        return new AssetRepository().getSandBottomRightCorner();
                    //lanes (water touching land)
                    case "topLane":
                        return new AssetRepository().getSandTopLane();
                    case "bottomLane":
                        return new AssetRepository().getSandBottomLane();
                    case "leftLane":
                        return new AssetRepository().getSandLeftLane();
                    case "rightLane":
                        return new AssetRepository().getSandRightLane();
                    //middle piece
                    case "middlePiece":
                        return new AssetRepository().getSandMiddlePiece();
                    //accessoires
                    case "boat":
                        return new AssetRepository().getSandBoat();
                    case "dump":
                        return new AssetRepository().getSandDump();
                }
            case "grass":
                switch(types[1]){
                    //corners
                    case "topLeftCorner":
                        return new AssetRepository().getGrasTopLeftCorner();
                    case "topRightCorner":
                        return new AssetRepository().getGrasTopRightCorner();
                    case "bottomleftCorner":
                        return new AssetRepository().getGrasBottomLeftCorner();
                    case "bottomRightCorner":
                        return new AssetRepository().getGrasBottomRightCorner();
                    //lanes (water touching land)
                    case "topLane":
                        return new AssetRepository().getGrasTopLane();
                    case "bottomLane":
                        return new AssetRepository().getGrasBottomLane();
                    case "leftLane":
                        return new AssetRepository().getGrasLeftLane();
                    case "rightLane":
                        return new AssetRepository().getGrasRightLane();
                    //middle piece
                    case "middlePiece":
                        return new AssetRepository().getGrasMiddlePiece();
                    case "middleStony":
                        return new AssetRepository().getGrasMiddleStony();
                    //accessoires
                    case "bigBush":
                        return new AssetRepository().getGrasBigBush();
                    case "triangleBush":
                        return new AssetRepository().getGrasTriangleBush();
                    case "littleBush":
                        return new AssetRepository().getGrasLittleBush();
                    case "littleStone":
                        return new AssetRepository().getLittleStone();
                }
            case "castle":
                switch(types[1]) {
                    //corners
                    case "topLeftCorner":
                        return new AssetRepository().getCastleTopLeftCorner();
                    case "topRightCorner":
                        return new AssetRepository().getCastleTopRightCorner();
                    case "bottomleftCorner":
                        return new AssetRepository().getCastleBottomLeftCorner();
                    case "bottomRightCorner":
                        return new AssetRepository().getCastleBottomRightCorner();
                    //lanes
                    case "bottomTopLane":
                        return new AssetRepository().getCastleBottomTopLane();
                    case "leftRightLane":
                        return new AssetRepository().getCastleLeftRightLane();
                    //canons
                    case "topCanon":
                        return new AssetRepository().getCastleTopCanon();
                    case "bottomCanon":
                        return new AssetRepository().getCastleBottomCanon();
                    case "leftCanon":
                        return new AssetRepository().getCastleLeftCanon();
                    case "rightCanon":
                        return new AssetRepository().getCastleRightCanon();
                    //door
                    case "bottomTopDoor":
                        return new AssetRepository().getCastleBottomTopDoor();
                    case "leftRightDoor":
                        return new AssetRepository().getCastleLeftRightDoor();
                }

            default:
                return null;
        }

    }
}
