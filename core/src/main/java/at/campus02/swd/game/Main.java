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

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Main extends ApplicationAdapter implements InputProcessor {
    private SpriteBatch batch;
    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private GameInput gameInput;
    private Array<GameObject> gameObjects = new Array<>();

    private Array<Player> players = new Array<>();
    private Array<Enemy> enemies = new Array<>();
    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private Player player;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    private Table uiTable, uiTable2;
    private Label positionLabel, survivalTime, highScoreTime;
    private Stage stage;
    private long startTime;

    private float highscore = 0, elapsedTime;

    @Override
    public void create() {

        batch = new SpriteBatch();

        PlayerFactory playerFactory = new PlayerFactory();
        EnemyFactory enemyFactory = new EnemyFactory();

        MeadowBuilder mb = new MeadowBuilder();
        for (GameObject o : mb.placeTile(80, 80, 5, 5)) {
            gameObjects.add(o);
        }

        player = (Player) playerFactory.create(Type.PLAYER, 235, 235);
        gameObjects.add(player);
        players.add(player);


        //Enemy spawn at random position
        int max = 370;
        int min = 100;
        int range = max - min + 1;
        int randX = 0;
        int randY = 0;

        randX = (int) (Math.random() * range) + min;
        randY = (int) (Math.random() * range) + min;

        enemy1 = (Enemy) enemyFactory.create(Type.ENEMY1, randX, randY);
        gameObjects.add(enemy1);
        enemies.add(enemy1);

        randX = (int) (Math.random() * range) + min;
        randY = (int) (Math.random() * range) + min;

        enemy2 = (Enemy) enemyFactory.create(Type.ENEMY2, randX, randY);
        gameObjects.add(enemy2);
        enemies.add(enemy2);

        randX = (int) (Math.random() * range) + min;
        randY = (int) (Math.random() * range) + min;

        enemy3 = (Enemy) enemyFactory.create(Type.ENEMY3, randX, randY);
        gameObjects.add(enemy3);
        enemies.add(enemy3);

        gameInput = new GameInput(player);

        Gdx.input.setInputProcessor(this.gameInput);

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);

        positionLabel = new Label("", new Label.LabelStyle(font, Color.WHITE));
        uiTable = new Table();
        uiTable.setFillParent(true);
        uiTable.top().left();

        uiTable.add(positionLabel);


        survivalTime = new Label("Current: ", new Label.LabelStyle(font, Color.WHITE));
        highScoreTime = new Label("   Highscore: ", new Label.LabelStyle(font, Color.WHITE));
        uiTable2 = new Table();
        uiTable2.setFillParent(true);
        uiTable2.top().right();
        uiTable2.add(survivalTime,highScoreTime);

        stage = new Stage(viewport, batch);
        stage.addActor(uiTable);
        stage.addActor(uiTable2);

        PositionObserver uiObserver = new UIPositionObserver(positionLabel);

        PositionObserver logObserver = new LogPositionObserver();
        player.addObserver(uiObserver);
        player.addObserver(logObserver);

        enemy1.addObserver(logObserver);
        enemy2.addObserver(logObserver);
        enemy3.addObserver(logObserver);

        startTime = System.currentTimeMillis();
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
        player.act(delta);

        System.out.println("This is called");
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

    // game loop
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
        enemy1.act(delta, 2,"default");
        enemy2.act(delta,1,"horizontal");
        enemy3.act(delta, (float)0.5,"vertical");

        draw();



        stage.act();
        stage.draw();
        elapsedTime = (System.currentTimeMillis() - startTime)/1000F;
        if(highscore < elapsedTime){
            highscore = elapsedTime;
        }
        highScoreTime.setText("   Highscore: " + highscore);
        checkCollisions();

    }

    private void checkCollisions(){
        survivalTime.setText("Current: " + elapsedTime);

        for (Enemy e : enemies
             ) {

            int distanceX = Math.abs(player.getPosition()[0] - e.getPosition()[0]);
            int distanceY = Math.abs(player.getPosition()[1] - e.getPosition()[1]);

            if(distanceX < 16 && distanceY < 16){
                System.out.println("##### COLLISION #####");
                startTime = System.currentTimeMillis();
            }


        }
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
