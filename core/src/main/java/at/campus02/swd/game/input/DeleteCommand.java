package at.campus02.swd.game.input;

import at.campus02.swd.game.gameobjects.EnemyBoy;
import at.campus02.swd.game.gameobjects.EnemyBoy2;
import at.campus02.swd.game.gameobjects.GameObject;
import at.campus02.swd.game.gameobjects.PlayerBoy;
import com.badlogic.gdx.utils.Array;


public class DeleteCommand implements Command{

    private Array<GameObject> gameObjects;
    private GameObject player;

    public DeleteCommand(Array<GameObject> gameObjects, GameObject player) {
        this.gameObjects = gameObjects;
        this.player = player;
    }
    public void execute(GameObject gameObject) {
        for (GameObject go: gameObjects
             ) {
            if (go instanceof EnemyBoy2) {
                EnemyBoy2 enemy2 = (EnemyBoy2) go;
                float distance = calculateDistance(player.getPositionX(), player.getPositionY(), enemy2.getPositionX(), enemy2.getPositionY());

                if (distance <= 50) {
                    gameObjects.removeValue(enemy2, true);
                }
            }
            else if (go instanceof EnemyBoy) {
                EnemyBoy enemy = (EnemyBoy) go;
                float distance = calculateDistance(player.getPositionX(), player.getPositionY(), enemy.getPositionX(), enemy.getPositionY());
                if (distance <= 50) {
                    gameObjects.removeValue(enemy, true);
                }
            }
        }

    }

    private float calculateDistance(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) Math.sqrt(dx * dx + dy * dy);
    }
}
