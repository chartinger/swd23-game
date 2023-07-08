package at.campus02.swd.game.logic;
import at.campus02.swd.game.gameobjects.EnemyObjects.Enemy;
import at.campus02.swd.game.gameobjects.interactiveObjects.InteractiveObject;
import at.campus02.swd.game.gameobjects.interactiveObjects.InteractiveObjectsObserver;
import at.campus02.swd.game.playerobjects.Background;
import at.campus02.swd.game.playerobjects.InteractiveObjects;
import at.campus02.swd.game.playerobjects.Player;
import at.campus02.swd.game.playerobjects.PlayerObserver;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Control {

    Player player;
    private Observer pObserver;
    private Observer interactiveOObserver;
    private BitmapFont font;
    private SpriteBatch batch;

    public Boolean LockPosition = false;
    public boolean addEnemy = true;

    public Control(Player player, SpriteBatch batch)  {
        pObserver = new PlayerObserver();
        pObserver.PushAction(player.name);

        interactiveOObserver = new InteractiveObjectsObserver();

        this.player = player;
        this.batch = batch;
        font = new BitmapFont();
        font.setColor(Color.WHITE);
    }

    public void alterLeft(){
        AlterLeft.execute(player);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterRight(){
        AlterRight.execute(player);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterDown(){
        AlterDown.execute(player);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterUp(){
        AlterUp.execute(player);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void playerShoot(){
        ActionShoot.execute(player);
        WriteLog(pObserver, "boom");
    }

    public void CheckPlayerAgainstInteractiveObject(){

        EnemyControl.instance(this).MoveEnemies();
        for (InteractiveObject iO: InteractiveObjects._listOfObjects) {
            interactiveOObserver.PushAction("InteractiveObject: ", iO.getPositionX(), iO.getPositionY());
            if (iO.getPositionX() == player.getPositionX() && iO.getPositionY() == player.getPositionY()
                || player.getPositionX() == Background.collisionPositionRight || player.getPositionX() == Background.collisionPositionLeft) {
                GameOver();
                break;
            }
        }
    }

    public void CheckPlayerAgainstEnemy(Enemy enemy){

        if(CheckPlayerFire(enemy))
            return;

        float enemyRange = 10;
        boolean xInrange = false;
        boolean yInfrange = false;
        for(float i = enemy.getPositionX() - enemyRange; i < enemy.getPositionX() + enemyRange; i++ )
        {
            if(i == player.getPositionX())
                xInrange = true;

            for(float j = enemy.getPositionX() - enemyRange; j < enemy.getPositionX() + enemyRange; j++ )
            {
                if (j == player.getPositionX()) {
                    yInfrange = true;
                    break;
                }
            }
        }
        if(xInrange && yInfrange)
            GameOver();
    }

    public boolean CheckPlayerFire(Enemy enemy) {

        if(!Player.playerShoot)
            return false;

        float playerRange = 20;
        boolean xInrange = false;
        boolean yInfrange = false;
        for(float i = enemy.getPositionX() - playerRange; i < enemy.getPositionX() + playerRange; i++ )
        {
            if(i == player.getPositionX())
                xInrange = true;

            for(float j = enemy.getPositionX() - playerRange; j < enemy.getPositionX() + playerRange; j++ )
            {
                if (j == player.getPositionX()) {
                    Player.playerShoot = false;
                    yInfrange = true;
                    break;
                }
            }
        }

        if(xInrange && yInfrange)
        {
            enemy.ChangeTexture();
            enemy.killEnemy();
            return true;
        }
        return false;
    }
    private void GameOver(){
        player.ChangeTexture();
        LockPosition = true;
        batch.begin();
        font.draw(batch, "Game Over", -110, -220);
        batch.end();
        EnemyControl.killEnemySpawn = true;
        System.out.println("----");
        System.out.println("ouch");
        System.out.println("----");
        pObserver.Print();
        EnemyControl.instance(this).eObserver.Print();
        interactiveOObserver.Print();
        UsedTextures.instance().Unload();
    }

    private void WriteLog(Observer observer, float x, float y){
        observer.PushAction(this.player.name, x,y);
    }

    private void WriteLog(Observer observer, String in){
        observer.PushAction(this.player.name + " " + in);
    }
}

