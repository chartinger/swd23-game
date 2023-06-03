package at.campus02.swd.game;

import com.badlogic.gdx.graphics.Texture;

public class AssetRepository {

        //water textures

        private Texture waterRight = new Texture("tiles/mapTile_171.png");
        private Texture waterLeft = new Texture("tiles/mapTile_187.png");
        private Texture waterStill= new Texture("tiles/mapTile_188.png");
        private Texture waterBright = new Texture("tiles/tile_73.png");

        // sand textures
        private Texture sandTopLeftCorner = new Texture("tiles/tile_01.png");
        private Texture sandTopRightCorner = new Texture("tiles/tile_03.png");
        private Texture sandBottomLeftCorner = new Texture("tiles/tile_33.png");
        private Texture sandBottomRightCorner = new Texture("tiles/tile_35.png");
        private Texture sandTopLane = new Texture("tiles/tile_02.png");
        private Texture sandBottomLane = new Texture("tiles/tile_34.png");
        private Texture sandLeftLane = new Texture("tiles/tile_17.png");
        private Texture sandRightLane = new Texture("tiles/tile_19.png");
        private Texture sandMiddlePiece = new Texture("tiles/tile_18.png");
        private Texture sandBoat = new Texture("tiles/tile_81.png");
        private Texture sandDump = new Texture("tiles/tile_83.png");

        //grass textures
        private Texture grasTopLeftCorner = new Texture("tiles/tile_06.png");
        private Texture grasTopRightCorner = new Texture("tiles/tile_09.png");
        private Texture grasBottomLeftCorner = new Texture("tiles/tile_54.png");
        private Texture grasBottomRightCorner = new Texture("tiles/tile_57.png");
        private Texture grasMiddlePiece = new Texture("tiles/tile_23.png");
        private Texture grasMiddleStony = new Texture("tiles/tile_24.png");


        //lanes (water touching land)
        private Texture grasTopLane = new Texture("tiles/tile_08.png");
        private Texture grasBottomLane = new Texture("tiles/tile_55.png");
        private Texture grasLeftLane = new Texture("tiles/tile_22.png");
        private Texture grasRightLane = new Texture("tiles/tile_25.png");

        //bush textures
        private Texture grasBigBush = new Texture("tiles/tile_71.png");
        private Texture grasTriangleBush = new Texture("tiles/tile_70.png");
        private Texture grasLittleBush = new Texture("tiles/tile_72.png");

        //stone textures
        private Texture littleStone = new Texture("tiles/tile_49.png");

        //castle textures
        private Texture castleTopLeftCorner = new Texture("tiles/tile_77.png");
        private Texture castleTopRightCorner = new Texture("tiles/tile_78.png");
        private Texture castleBottomLeftCorner = new Texture("tiles/tile_93.png");
        private Texture castleBottomRightCorner = new Texture("tiles/tile_94.png");

        //lanes
        private Texture castleBottomTopLane = new Texture("tiles/tile_16.png");
        private Texture castleLeftRightLane = new Texture("tiles/tile_15.png");

        //canons
        private Texture castleTopCanon = new Texture("tiles/tile_47.png");
        private Texture castleBottomCanon = new Texture("tiles/tile_48.png");
        private Texture castleLeftCanon = new Texture("tiles/tile_32.png");
        private Texture castleRightCanon = new Texture("tiles/tile_31.png");

        //door
        private Texture castleBottomTopDoor = new Texture("tiles/tile_76.png");
        private Texture castleLeftRightDoor = new Texture("tiles/tile_60.png");
}
