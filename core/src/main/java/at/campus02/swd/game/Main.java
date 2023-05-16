package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.island.SandIsland;
import at.campus02.swd.game.gameobjects.island.SandLeftUpper;
import at.campus02.swd.game.gameobjects.water.Water1;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.input.GameInput;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private ExtendViewport viewport = new ExtendViewport(1920.0f, 1080.0f, 1920.0f, 1080.0f);
	private GameInput gameInput = new GameInput();

	private Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;

	@Override
	public void create() {
		batch = new SpriteBatch();

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this.gameInput);


        for (float i = -viewport.getMaxWorldWidth(); i < viewport.getMaxWorldWidth(); i= i+64) {
            for (float j = -viewport.getMaxWorldHeight(); j < viewport.getMaxWorldHeight(); j = j+64) {
                Water1 water1 = new Water1();
                gameObjects.add(water1);
                water1.setPosition(i,j);
            }
        }



        ArrayList<SandIsland> sandIslands = new ArrayList<>();
        sandIslands.add(new SandIsland(250, 250));
        sandIslands.add(new SandIsland(130,0));
        sandIslands.add(new SandIsland(-400,70));
        sandIslands.add(new SandIsland(699,-123));
        sandIslands.add(new SandIsland(-333,-300));

        for(SandIsland sandIsland : sandIslands){
            for(GameObject go : sandIsland.getGameObjectArray()){
                gameObjects.add(go);
            }
        }




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
		batch.end();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
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
