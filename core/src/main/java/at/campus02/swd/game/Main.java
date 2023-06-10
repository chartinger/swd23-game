package at.campus02.swd.game;

import at.campus02.swd.game.board.Game;
import at.campus02.swd.game.board.FloorObserver.Action;
import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.reporting.ScoreBoard;
import at.campus02.swd.game.threats.AmplifiedEdgeDamage;
import at.campus02.swd.game.threats.NoDamage;
import at.campus02.swd.game.threats.RandomFloorDestroyer;
import at.campus02.swd.game.util.GameObjectPositioner;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.input.GameInput;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private final GameObjectPositioner gameObjectPositioner = new GameObjectPositioner(640, 640, 64);
    private SpriteBatch batch;
    private ScoreBoard scoreBoard;

	private final ExtendViewport viewport = new ExtendViewport(640.0f, 640.0f, 640.0f, 640.0f);

	private final Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;


    @Override
	public void create() {
        batch = new SpriteBatch();
        scoreBoard = new ScoreBoard(-300, -290);
        startNewGame();
    }

    private void startNewGame() {
        final Game game = createGame();
        setupReporting(game);
        setupKeybindings(game);
        setupThreats(game);
        System.out.println("Game has been started - try to safe yourself!");
    }

    private Game createGame() {
        final TileFactory tileFactory = new TileFactory(AssetRepository.INSTANCE);
        final PlayerFactory playerFactory = new PlayerFactory(AssetRepository.INSTANCE);
        final Game game = new Game(gameObjectPositioner, playerFactory, tileFactory);
        gameObjects.addAll(game.getGameObjects());
        return game;
    }

    private void setupKeybindings(Game game) {
        final GameInput gameInput = new GameInput();
        gameInput.addAction(Keys.UP, game::moveNorth);
        gameInput.addAction(Keys.DOWN, game::moveSouth);
        gameInput.addAction(Keys.LEFT, game::moveWest);
        gameInput.addAction(Keys.RIGHT, game::moveEast);
        gameInput.addAction(Keys.ESCAPE, this::startNewGame);
        gameInput.addAction(Keys.Q, Gdx.app::exit);
        gameInput.addAction(Keys.NUM_0, () -> game.setActiveStrategy(0));
        gameInput.addAction(Keys.NUM_1, () -> game.setActiveStrategy(1));
        gameInput.addAction(Keys.NUM_2, () -> game.setActiveStrategy(2));
        gameInput.addAction(Keys.X, game::useAllStrategies);
        Gdx.input.setInputProcessor(gameInput);
    }

    private void setupReporting(Game game) {
        game.subscribe(scoreBoard);
        game.subscribe(position -> System.out.println("You are at " + position));
        game.subscribe((action, position) -> System.out.println("Floor at " + position + " just " + (Action.DESTROY.equals(action) ? "vanished" : "appeared")));
    }

    private static void setupThreats(Game game) {
        game.addThreat(NoDamage.builder());
        game.addThreat(RandomFloorDestroyer.withTilesPerRound(2));
        game.addThreat(AmplifiedEdgeDamage.withTilesPerRound(3));
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
		scoreBoard.draw(batch);
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
        AssetRepository.INSTANCE.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}
