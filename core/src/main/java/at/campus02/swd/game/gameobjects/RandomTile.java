package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;


public class RandomTile implements GameObject {
    private Texture image;
    private Sprite sprite;

    public RandomTile() {
        //image = new Texture("sign.png");
        image = getRandomTexture();
        sprite = new Sprite(image);
    }
    @Override
    public void act(float delta) {

    }

    public Texture getRandomTexture() {
        String folderPath = "tiles";  //TODO PATH ANPASSEN!!!! so dass es aus Assets/Tiles geladen wird, nicht aus dem kopierten
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

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveRight() {

    }
}
