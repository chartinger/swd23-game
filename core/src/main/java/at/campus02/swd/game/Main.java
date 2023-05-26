package at.campus02.swd.game;

import at.campus02.swd.game.factory.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.input.GameInput;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private GameInput gameInput = new GameInput();

    private Array<GameObject> gameObjects = new Array<>();

    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;

    private GameObject tile;

    private GameObject player;


    @Override
    public void create() {
        batch = new SpriteBatch();

        Factory tileFactory = new TileFactory();
        PlayerFactory player = new PlayerFactory();

        for (int i = 0; i < (viewport.getMinWorldHeight() / 64); i++) {
            for (int y = 0; y < (viewport.getMinWorldHeight() / 64); y++) {
                gameObjects.add(tileFactory.create(Type.MEADOW_CENTER,i*64,y*64));
            }
        }

        MeadowBuilder mb = new MeadowBuilder();
        for (GameObject o:mb.placeTile(-160,-160,5,5)
        ) {
            gameObjects.add(o);
        }

        /*
        for(int x = -240; x<240; x+=64) {
            for (int y = 240; y > -240; y -= 64) {
                tile = new Tile();
                tile.setPosition(x,y-64);
                gameObjects.add(tile);
            }
        }
*/

        gameObjects.add(player.create(Type.PLAYER,0,0));

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
    }

    private void act(float delta) {
        for(GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        for(GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        font.draw(batch, "Hello Game", -220, -220);
        batch.end();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.52f, 0.81f, 0.92f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
        while(deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width,height);
    }
}
