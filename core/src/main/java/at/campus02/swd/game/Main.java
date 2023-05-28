package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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

    private final GameObjectPositioner gameObjectPositioner = new GameObjectPositioner(640, 640, 64);
    private TileFactory tileFactory;
    private PlayerFactory playerFactory;

	@Override
	public void create() {
        tileFactory = new TileFactory(AssetRepository.INSTANCE);
        playerFactory = new PlayerFactory(AssetRepository.INSTANCE);

        drawDeathLayer();
        drawPlayingField();
        drawPlayer(3, 2);
        drawFinish(7, 8);

        batch = new SpriteBatch();
		font = new BitmapFont();
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this.gameInput);
	}

    private void drawPlayingField() {
        fillLayerWithTile(TileType.CENTER);
    }

    private void drawDeathLayer() {
        fillLayerWithTile(TileType.WATER);
    }

    private void fillLayerWithTile(TileType center) {
        for (int column = 0; column < 10; column++)
            for (int row = 0; row < 10; row++)
                createAndPlaceTile(center, column, row);
    }

    private void drawPlayer(int column, int row) {
        createAndPlacePlayer(PlayerType.READY_PLAYER_ONE, column, row);
    }

    private void drawFinish(int column, int row) {
        createAndPlaceTile(TileType.FINISH, column, row);
    }

    private void createAndPlaceTile(TileType tileType, int column, int row) {
        createAndPlaceGameObject(tileFactory, tileType, column, row);
    }

    private void createAndPlacePlayer(PlayerType playerType, int column, int row) {
        createAndPlaceGameObject(playerFactory, playerType, column, row);
    }

    private <E extends Enum<E>> void createAndPlaceGameObject(GameObjectFactory<E> factory, E tileType, int column, int row) {
        GameObject object = factory.create(tileType);
        gameObjectPositioner.setPosition(object, column, row);
        gameObjects.add(object);
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
