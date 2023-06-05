package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.input.GameInput;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

	private final ExtendViewport viewport = new ExtendViewport(640.0f, 640.0f, 640.0f, 640.0f);
	private final GameInput gameInput = new GameInput();

	private final Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;


    @Override
	public void create() {
        final GameObjectPositioner gameObjectPositioner = new GameObjectPositioner(640, 640, 64);
        final TileFactory tileFactory = new TileFactory(AssetRepository.INSTANCE);
        final PlayerFactory playerFactory = new PlayerFactory(AssetRepository.INSTANCE);
        final Board board = new Board(gameObjectPositioner, playerFactory, tileFactory);

        gameObjects.addAll(board.getGameObjects());

        gameInput.addAction(Keys.UP, board::moveNorth);
        gameInput.addAction(Keys.DOWN, board::moveSouth);
        gameInput.addAction(Keys.LEFT, board::moveWest);
        gameInput.addAction(Keys.RIGHT, board::moveEast);

        batch = new SpriteBatch();
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
		font.draw(batch, "Hello Game", -300, -300);
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
