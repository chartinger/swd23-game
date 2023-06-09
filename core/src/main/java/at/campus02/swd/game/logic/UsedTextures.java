package at.campus02.swd.game.logic;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

public class UsedTextures {

    public Map<String, Texture> Textures = new HashMap<String, Texture>();

    private static UsedTextures usedTextures = null;

    private UsedTextures(){}

    private void FillList()
    {
        Textures.put("Coral", new Texture("sprites/mapTile_044.png") );
        Textures.put("Rock",  new Texture("sprites/mapTile_049.png" ));
        Textures.put("Cliff", new Texture("tiles/mapTile_018.png" ));
        Textures.put("Gras", new Texture("tiles/mapTile_021.png" ));
        Textures.put("Sign", new Texture("sign.png" ));
        Textures.put("Tile", new Texture("tiles/mapTile_188.png" ));

        Textures.put("Player", new Texture("sprites/Ships/ship (6).png" ));
        Textures.put("Explosion", new Texture("sprites/Effects/explosion2.png" ));

//        for (String key : Textures.keySet()) {
//            System.out.println(key);
//            if(Textures.get(key) == null)
//                System.out.println("ERROR------------------------");
//        }
    }

    static public UsedTextures instance(){
        if(usedTextures == null){
            usedTextures = new UsedTextures() ;
            usedTextures.FillList();
        }
        return usedTextures;
    }

    public void RemoveTexture(String TextureName){
        Textures.remove(TextureName);
    }

    public void Unload(){
        usedTextures = null;
    }

}
