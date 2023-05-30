package at.campus02.swd.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Tile;
import at.campus02.swd.game.gameobjects.TileFactory;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.gameobjects.PlayerFactory;
import at.campus02.swd.game.input.GameInput;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    private ExtendViewport viewport = new ExtendViewport(480.0f, 480.0f, 480.0f, 480.0f);
    private GameInput gameInput = new GameInput();

    private Array<GameObject> gameObjects = new Array<>();

    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;

    private TileFactory tileFactory;
    private PlayerFactory playerFactory;
    private Player player;

    @Override
    public void create() {
        batch = new SpriteBatch();
        tileFactory = new TileFactory();
        playerFactory = new PlayerFactory();
        player = playerFactory.createPlayer();
        gameObjects.add(player);
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
        createTileBackground();
        placeTiles();
    }

    private void createTileBackground() {
        int numTilesX = 50;
        int numTilesY = 50;
        float tileSize = 64.0f;

        float worldWidth = numTilesX * tileSize;
        float worldHeight = numTilesY * tileSize;
        viewport.setWorldSize(worldWidth, worldHeight);
    }


    private void placeTiles() {
        int numTilesX = 10;
        int numTilesY = 10;
        float tileSize = 32.0f;

        for (int x = 0; x < numTilesX; x++) {
            for (int y = 0; y < numTilesY; y++) {
                Tile tile = tileFactory.createTile(1);
                if (tile != null) {
                    float tilePosX = x * tileSize;
                    float tilePosY = y * tileSize;
                    tile.setPosition(tilePosX, tilePosY);
                    gameObjects.add(tile);
                }
            }
        }
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();

        // Draw background
        for (GameObject gameObject : gameObjects) {
            if (!(gameObject instanceof Player)) {
                gameObject.draw(batch);
            }
        }

        // Draw player
        player.draw(batch);

        font.draw(batch, "Hello Game", -220, -220);
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
            draw();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
       // tileFactory.dispose();
       // playerFactory.dispose();
        font.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
