package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.input.GameInput;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private GameInput gameInput;

	private Array<GameObject> gameObjects = new Array<>();

    private DrawObserver drawObserver = new DrawObserver();

    private CurrentPositionObserver positionObserver = new CurrentPositionObserver();



    private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;
    private Sign sign;

	@Override
	public void create() {

        batch = new SpriteBatch();

        /** Übung 2: Laden der Textures aus dem AssetRepository **/
        AssetRepository.getInstance().preloadAssets();

        /** Tiles werden hier erstellt und platziert  **/
        Background background = new Background();
        background.fillBackground(gameObjects);

        /**  Hier wird ein Spieler von einer Factory erzeugt und platziert **/
        PlayerFactory playerFactory = new PlayerFactory();
        Player player = playerFactory.create();
        gameObjects.add(player);
        player.setPosition(100,130);
        positionObserver.update(player);
        gameInput = new GameInput(player);




        sign = new Sign();
        gameObjects.add(sign);
        sign.setPosition(100,100);

		font = new BitmapFont();
		font.setColor(Color.WHITE);
		Gdx.input.setInputProcessor(this.gameInput);

	}




	private void act(float delta) {
		for(GameObject gameObject : gameObjects) {
			gameObject.act(delta);
		}
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
		//draw();
        drawObserver.draw(batch,viewport,gameObjects); // Methode in DrawObserver Klasse

        int lastPlayerX = (int) positionObserver.getLastX();
        int lastPlayerY = (int) positionObserver.getLastY();
	}

	@Override
	public void dispose() {

        batch.dispose();

        // Übung 2: aufräumen des AssetRepositorys
        AssetRepository.getInstance().dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}
}
