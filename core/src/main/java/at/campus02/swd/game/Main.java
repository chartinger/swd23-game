package at.campus02.swd.game;

import at.campus02.swd.game.factory.*;
import at.campus02.swd.game.gameobjects.*;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import at.campus02.swd.game.input.GameInput;

public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private GameInput gameInput;
    private Array<GameObject> gameObjects = new Array<>();
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private Player player;
    private Table uiTable;
    private Label positionLabel;

    private Stage stage;

    @Override
    public void create() {

        batch = new SpriteBatch();

        PlayerFactory playerFactory = new PlayerFactory();

        MeadowBuilder mb = new MeadowBuilder();
        for (GameObject o : mb.placeTile(80, 80, 5, 5)) {
            gameObjects.add(o);
        }

        player = (Player) playerFactory.create(Type.PLAYER, 235, 235);
        gameObjects.add(player);

        gameInput = new GameInput(player);

        Gdx.input.setInputProcessor(this.gameInput);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);

        positionLabel = new Label("", new Label.LabelStyle(font, Color.WHITE));
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiTable.top().left();


        uiTable.add(positionLabel).left().top();

        stage = new Stage(viewport, batch);
        stage.addActor(uiTable);

        PositionObserver uiObserver = new UIPositionObserver(positionLabel);


        PositionObserver logObserver = new LogPositionObserver();
        player.addObserver(uiObserver);
        player.addObserver(logObserver);
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
        player.act(delta);
    }

    private void draw() {
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        font.draw(batch, "Hello Game", -220, -220);// Zeichnet den Text "Hello Game" an der angegebenen Position
        batch.end();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.52f, 0.81f, 0.92f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float delta = Gdx.graphics.getDeltaTime();

        while (deltaAccumulator > logicFrameTime) {
            deltaAccumulator -= logicFrameTime;
            act(logicFrameTime);
        }

        player.act(delta);

        draw();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.setScreenSize(width, height);
        viewport.setWorldSize(480, 480);
        viewport.apply(true);
        stage.getViewport().update(width, height, true);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            player.moveUp();
        } else if (keycode == Input.Keys.DOWN) {
            player.moveDown();
        } else if (keycode == Input.Keys.LEFT) {
            player.moveLeft();
        } else if (keycode == Input.Keys.RIGHT) {
            player.moveRight();
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.W) {
            player.stopMovingUp();
        } else if (keycode == Input.Keys.S) {
            player.stopMovingDown();
        } else if (keycode == Input.Keys.A) {
            player.stopMovingLeft();
        } else if (keycode == Input.Keys.D) {
            player.stopMovingRight();
        }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
