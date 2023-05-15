package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.gameobjects.WaterTile;

public class TileFactory extends Factory{
    @Override
    protected GameObject createGameObject(TileType type,int x, int y) {
        GameObject tile;
        switch (type){
            case WATER:
                tile = new WaterTile();
                tile.setPosition(x,y);
                break;
            case SIGN:
                tile = new Sign();
                tile.setPosition(x,y);
                break;
            case ISLAND:
                TileType leftTopTileType = TileType.LEFT_TOP;
                TileType topTileType = TileType.TOP;
                TileType rightTopTileType = TileType.RIGHT_TOP;
                TileType leftBotTileType = TileType.LEFT_BOTTOM;
                TileType botTileType = TileType.BOTTOM;
                TileType rightBotTileType = TileType.RIGHT_BOTTOM;

                IslandBuilder islandBuilder = new IslandBuilder(3, 2, leftTopTileType, topTileType, rightTopTileType,
                    leftBotTileType, botTileType, rightBotTileType);

                tile = islandBuilder.createIslandTile(x, y);
                break;

            default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }

        return tile;
    }

}
