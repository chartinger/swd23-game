package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class Tile implements GameObject {
    private Texture texture;
    private Sprite sprite;

    public Tile() {
        //texture = new Texture("tiles/mapTile_003.png");
        texture = getRandomTexture();
        sprite = new Sprite(texture);
    }

    @Override
    public void act(float delta) {

    }
    public Texture getRandomTexture() {
        String folderPath = "tiles/";
        Array<String> fileNames = new Array<>();
        for (FileHandle fileHandle : Gdx.files.internal(folderPath).list()) {
            fileNames.add(fileHandle.name());
        }
        String randomFileName = fileNames.random();
        return new Texture(Gdx.files.internal(folderPath + "/" + randomFileName));
    }

    @Override
    public void setPosition(float x, float y) {
        sprite.setPosition(x, y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
