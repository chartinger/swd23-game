package at.campus02.swd.game;

import at.campus02.swd.game.factory.Factory;
import at.campus02.swd.game.factory.TileFactory;
import at.campus02.swd.game.factory.TileType;
import at.campus02.swd.game.gameobjects.Tile;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.Sign;
import at.campus02.swd.game.input.GameInput;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;

    private ExtendViewport viewport = new ExtendViewport(640.0f, 640.0f, 640.0f, 640.0f);
    private GameInput gameInput = new GameInput();

    private Array<GameObject> gameObjects = new Array<>();

    private final float updatesPerSecond = 60;
    private final float logicFrameTime = 1 / updatesPerSecond;
    private float deltaAccumulator = 0;
    private BitmapFont font;
    private OrthographicCamera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewport.getWorldWidth(), viewport.getWorldHeight());
        camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
        camera.update();
        Factory tf = new TileFactory();
        int tileSize = 48;
        for (int i = 0; i < (viewport.getMinWorldHeight() / tileSize); i++) {
            for (int j = 0; j < (viewport.getMinWorldHeight() / tileSize); j++) {
                int tileX = i * tileSize;
                int tileY = j * tileSize;
                GameObject tile = tf.create(TileType.WATER);
                gameObjects.add(tile);
                tile.setPosition(tileX,tileY);
            }
        }


        font = new BitmapFont();
        font.setColor(Color.WHITE);
        Gdx.input.setInputProcessor(this.gameInput);
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

        // Zeichnet den Text "Hello Game" relativ zur Viewportgröße mit dem Batch
        //String text = "Hello Game";
        //GlyphLayout layout = new GlyphLayout();
        //layout.setText(font, text);
        //float textWidth = layout.width;
        //float textX = viewport.getWorldWidth() / 2 - textWidth / 2;
        //float textY = viewport.getWorldHeight() / 2;
        //font.draw(batch, text, textX, textY);
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
