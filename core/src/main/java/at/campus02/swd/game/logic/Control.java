package at.campus02.swd.game.logic;
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

    private final float stepSize = 10.0F;

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
        player.setPosition(player.X() - stepSize , player.Y()  );
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterRight(){
        player.setPosition(player.X() + stepSize , player.Y() );
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterDown(){
        player.setPosition(player.X(), player.Y()  - stepSize);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void alterUp(){
        player.setPosition(player.X(), player.Y()  + stepSize);
        WriteLog(pObserver, player.getPositionX(), player.getPositionY());
    }

    public void CheckPlayerAgainstInteractiveObject(){
//        System.out.println(InteractiveObjects._listOfObjects.get(1).getPositionX() + " XO :: XP " + player.X() + " :: "
//                          + InteractiveObjects._listOfObjects.get(1).getPositionY() + " YO :: YP" + player.Y());

        for (InteractiveObject iO: InteractiveObjects._listOfObjects) {
            interactiveOObserver.PushAction("InteractiveObject: ", iO.getPositionX(), iO.getPositionY());
            if (iO.getPositionX() == player.getPositionX() && iO.getPositionY() == player.getPositionY()
                || player.getPositionX() == Background.collisionPositionRight || player.getPositionX() == Background.collisionPositionLeft) {
                player.ChangeTexture();
                LockPosition = true;
                batch.begin();
                font.draw(batch, "Game Over", -110, -220);
                batch.end();
                System.out.println("----");
                System.out.println("ouch");
                System.out.println("----");
                pObserver.Print();
                interactiveOObserver.Print();
                UsedTextures.instance().Unload();
                break;
            }
        }
    }

    private void WriteLog(Observer observer, float x, float y){
        observer.PushAction(this.player.name, x,y);
    }
}

