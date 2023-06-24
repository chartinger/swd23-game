package at.campus02.swd.game;

import at.campus02.swd.game.factory.*;
import at.campus02.swd.game.gameobjects.AssetRepository;
import at.campus02.swd.game.gameobjects.Player;
import at.campus02.swd.game.observer.UIPositionObserver;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.input.GameInput;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private ExtendViewport viewport = new ExtendViewport(640.0f, 640.0f, 640.0f, 640.0f);
    private Player player;
    private GameInput gameInput;

    private Array<GameObject> gameObjects = new Array<>();

    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private OrthographicCamera camera;
    private static String onScreenPos;

    @Override
    public void create() {
        AssetRepository.INSTANCE.preload();
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        Factory tileFactory = new TileFactory();
        PlayerFactory playerFactory = new PlayerFactory();
        int tileSize = 48;
        for (int i = 0; i < (viewport.getMinWorldHeight() / tileSize); i++) {
            for (int j = 0; j < (viewport.getMinWorldHeight() / tileSize); j++) {
                int tileX = i * tileSize;
                int tileY = j * tileSize;
                gameObjects.add(tileFactory.create(Type.WATER,tileX,tileY,0));
            }
        }

        IslandBuilder ib = new IslandBuilder();
        for (GameObject o:ib.placeIsland(200,200,6,4)
             ) {
            gameObjects.add(o);
        }
        gameObjects.add(tileFactory.create(Type.SIGN, 250, 240,0));
        gameObjects.add(playerFactory.create(Type.ENEMY,320,80,0));
        player = (Player) playerFactory.create(Type.HUMAN,500,100,0);
        gameObjects.add(player);
        gameInput = new GameInput(player);




        font = new BitmapFont();

        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
        AssetRepository.INSTANCE.dispose();
    }

    private void act(float delta) {
        for (GameObject gameObject : gameObjects) {
            gameObject.act(delta);
        }
    }

    private void draw() {

        //Aktualisiert die Kameraeinstellungen.
        camera.update();

        // Setzt die Projektionsmatrix des Batches auf die kombinierten Kamera- und Viewport-Einstellungen
        batch.setProjectionMatrix(camera.combined);

        // Beginnt das Zeichnen mit dem Batch
        batch.begin();

        // Zeichnet jedes Spielobjekt mit dem Batch
        for (GameObject gameObject : gameObjects) {
            gameObject.draw(batch);
        }
        UIPositionObserver uiPositionObserver = new UIPositionObserver();
        uiPositionObserver.updatePosition(player.getSprite().getX(),player.getSprite().getY(),player.getSprite().getRotation());
        onScreenPos = uiPositionObserver.getPos();

       GlyphLayout layout = new GlyphLayout();
       layout.setText(font, onScreenPos);
       float textWidth = layout.width;
       float textX = 0;
       float textY = viewport.getWorldHeight();
       font.draw(batch, onScreenPos, textX, textY);
        batch.end();
    }

    @Override
    public void render() {

        // Zeichnet die Hintergrundfarbe.
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Aktualisiert die Kameraeinstellungen.
        camera.update();

        //Ermittelt die vergangene Zeit seit dem letzten Frame in Sekunden und speichert sie in der Variable delta.
        float delta = Gdx.graphics.getDeltaTime();

        //Addiert die vergangene Zeit zum Akkumulator, um die Gesamtzeit zu verfolgen.
        deltaAccumulator += delta;

        //Führt die Spiellogik in regelmäßigen Intervallen basierend auf logicFrameTime aus, um eine konstante Aktualisierungsrate zu erreichen.
        while (deltaAccumulator > logicFrameTime) {

            //Subtrahiert das Aktualisierungsintervall vom Akkumulator, um die verbrauchte Zeit abzuziehen.
            deltaAccumulator -= logicFrameTime;
            //Führt die Spiellogik basierend auf dem Aktualisierungsintervall aus.
            act(logicFrameTime);
        }

        //Ruft die Methode draw() auf, um die Spielobjekte zu zeichnen.
        draw();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
    }
}
