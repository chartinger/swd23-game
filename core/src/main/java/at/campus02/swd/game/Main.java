package at.campus02.swd.game;

import at.campus02.swd.game.gameobjects.*;
import at.campus02.swd.game.gameobjects.EnemyObjects.Enemy;
import at.campus02.swd.game.logic.Control;
import at.campus02.swd.game.logic.EnemyControl;
import at.campus02.swd.game.playerobjects.Background;
import at.campus02.swd.game.playerobjects.InteractiveObjects;
import at.campus02.swd.game.playerobjects.Player;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.input.GameInput;

import java.util.Arrays;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
	private SpriteBatch batch;

	private final ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
	private final GameInput gameInput = new GameInput();

	public Array<GameObject> gameObjects = new Array<>();

	private final float updatesPerSecond = 60;
	private final float logicFrameTime = 1 / updatesPerSecond;
	private float deltaAccumulator = 0;
	private BitmapFont font;

    public Player playerOne;

	@Override
	public void create() {
		batch = new SpriteBatch();

        Background background = new Background();
        InteractiveObjects interactiveObjects= new InteractiveObjects();
        gameObjects = background.Create(gameObjects);
        gameObjects = interactiveObjects.Create(gameObjects);

        playerOne = new Player("Player One");

        gameInput.control = new Control(playerOne, batch);
        EnemyControl.instance(gameInput.control);
        EnemyControl.instance(gameInput.control).run();
        gameObjects.add(playerOne);

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
        String position = "x: " + playerOne.getPositionX() + ", y: " + playerOne.getPositionY();
		font.draw(batch, position, -220, -220);
		batch.end();
	}

	@Override
	public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();
        deltaAccumulator += delta;
        while (deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }
        draw();
        AreThereEnemies();
	}

    public void AreThereEnemies(){

        try {
            if (EnemyControl.instance(gameInput.control)._enemies.size() > 0) {
                for (Enemy e : EnemyControl.instance(gameInput.control)._enemies) {
                    if (e.killMe)
                        killEnemy(e);
                }
            }
        }
        catch(Exception e)
        {

        }
        if (EnemyControl.spawnEnemy && EnemyControl._numberEnemies > 0) {
            if(EnemyControl.instance(gameInput.control)._enemies.size() < 2 && gameInput.control.addEnemy)  {
                newOpponent();
            }
        }
    }

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width,height);
	}

    public void newOpponent() {
        gameObjects.add(EnemyControl.instance(gameInput.control).AddEnemy());
        EnemyControl._numberEnemies--;
        EnemyControl.spawnEnemy = false;
    }

    private void killEnemy(Enemy enemy)
    {
        gameObjects.removeValue(enemy, true);
        EnemyControl.instance(gameInput.control).deleteEnemyFromList(enemy);
        EnemyControl._numberEnemies++;
        enemy = null;
    }
}

