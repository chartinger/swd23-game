package at.campus02.swd.game.factory;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.IslandTile;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.gameobjects.WaterTile;

public class TileFactory extends Factory{
    @Override
    protected GameObject createGameObject(Type type, int x, int y) {
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
            case ISLAND_TOP:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwatertop1.png");
                break;
            case ISLAND_TOP_LEFT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterlefttop1.png");
                break;
            case ISLAND_TOP_RIGHT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterrighttop1.png");
                break;
            case ISLAND_CENTER:
                tile = new IslandTile("tiles/tropical/Terrain/Desert/Desert1.png");
                break;
            case ISLAND_LEFT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterleft1.png");
                break;
            case ISLAND_RIGHT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterright1.png");
                break;
            case ISLAND_BOTTOM:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterbot1.png");
                break;
            case ISLAND_BOTTOM_LEFT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterleftbot1.png");
                break;
            case ISLAND_BOTTOM_RIGHT:
                tile = new IslandTile("tiles/tropical/Terrain/Desertwater/Desertwaterrightbot1.png");
                break;
           default:
                throw  new IllegalArgumentException("Value is invalid: "+type);
        }

        return tile;
    }

}
