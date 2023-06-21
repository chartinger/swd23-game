package at.campus02.swd.game;

import at.campus02.swd.game.board.MultiDamageBoard;
import at.campus02.swd.game.game.Board;
import at.campus02.swd.game.game.DefenceType;
import at.campus02.swd.game.game.Game;
import at.campus02.swd.game.game.FloorObserver.Action;
import at.campus02.swd.game.defences.Direction;
import at.campus02.swd.game.defences.RepairBomb;
import at.campus02.swd.game.defences.RepairGun;
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
    public static final int BUDGET = 3;

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
        startNewGame();
    }

    private void startNewGame() {
        final Game game = createGame();
        setupReporting(game);
        setupKeybindings(game);
        setupThreats(game);
        setupDefences(game);
        System.out.println("Game has been started - try to safe yourself!");
    }

    private Game createGame() {
        final TileFactory tileFactory = new TileFactory(AssetRepository.INSTANCE);
        final PlayerFactory playerFactory = new PlayerFactory(AssetRepository.INSTANCE);
        final Board board = new MultiDamageBoard(gameObjectPositioner, playerFactory, tileFactory, PlayerType.READY_PLAYER_ONE, TileType.FINISH);
        final Game game = new Game(board, BUDGET);
        gameObjects.addAll(board.getGameObjects());
        scoreBoard = new ScoreBoard(-300, -290);
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
        gameInput.addAction(Keys.NUM_0, () -> game.setActiveThreat(0));
        gameInput.addAction(Keys.NUM_1, () -> game.setActiveThreat(1));
        gameInput.addAction(Keys.NUM_2, () -> game.setActiveThreat(2));
        gameInput.addAction(Keys.X, game::activateAllThreats);
        gameInput.addAction(Keys.SPACE, () -> game.defend(DefenceType.DETONATE));
        gameInput.addAction(Keys.W, () -> game.defend(DefenceType.AIM_NORTH));
        gameInput.addAction(Keys.A, () -> game.defend(DefenceType.AIM_WEST));
        gameInput.addAction(Keys.S, () -> game.defend(DefenceType.AIM_SOUTH));
        gameInput.addAction(Keys.D, () -> game.defend(DefenceType.AIM_EAST));
        Gdx.input.setInputProcessor(gameInput);
    }

    private void setupReporting(Game game) {
        game.subscribeForBudget(scoreBoard);
        game.subscribeForMovement(scoreBoard);
        game.subscribeForFloorActions(scoreBoard);
        game.subscribeForMovement(position -> System.out.println("You are at " + position));
        game.subscribeForFloorActions((action, position) -> System.out.println("Floor at " + position + " just " + (Action.DESTROY.equals(action) ? "vanished" : "appeared")));
    }

    private static void setupThreats(Game game) {
        game.addThreat(NoDamage.builder());
        game.addThreat(RandomFloorDestroyer.withTilesPerRound(3));
        game.addThreat(AmplifiedEdgeDamage.withTilesPerRound(11));
    }

    private static void setupDefences(Game game) {
        game.setDefence(DefenceType.DETONATE, RepairBomb.builder());
        game.setDefence(DefenceType.AIM_NORTH, RepairGun.pointing(Direction.NORTH));
        game.setDefence(DefenceType.AIM_SOUTH, RepairGun.pointing(Direction.SOUTH));
        game.setDefence(DefenceType.AIM_EAST, RepairGun.pointing(Direction.EAST));
        game.setDefence(DefenceType.AIM_WEST, RepairGun.pointing(Direction.WEST));
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
