package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.playerobjects.PlayerOne;
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

	private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private GameInput gameInput = new GameInput();

	private Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;

    private Sign sign;

    private Tile tile;
    private PlayerOne playerOne;
    private Cliff cliff;
    private Gras gras;
    private Corals corals;
    private Rock rock;


	@Override
	public void create() {
		batch = new SpriteBatch();
		/*sign = new Sign();
        gameObjects.add(new Sign());
        sign.setPosition(30,30);
        gameObjects.add(sign);*/
        for (int i = -240; i < 240; i+=64){
            for(int j = 240; j >-240;j-=64){
                tile = new Tile();
                gras = new Gras();
                cliff = new Cliff();

                    tile.setPosition(i,j-64);
                    gameObjects.add(tile);

            }
        }
        playerOne = new PlayerOne();
        playerOne.setPosition(0,0);
        gameObjects.add(playerOne);

        for(int x = - 240; x < 240; x +=64){
        gras = new Gras();
        gras.setPosition(178,x);
        gameObjects.add(gras);
        }


        rock = new Rock();
        rock.setPosition(40,-128);
        gameObjects.add(rock);

        corals = new Corals();
        corals.setPosition(-30,-192);
        gameObjects.add(corals);

        for(int x = - 240; x < 240; x +=64){
        cliff = new Cliff();
        cliff.setPosition(-240,x);
        gameObjects.add(cliff);
        }

        for(int x = - 240; x < 240; x +=64){
            cliff = new Cliff();
            cliff.setPosition(-240,x);
            gameObjects.add(cliff);
        }



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
