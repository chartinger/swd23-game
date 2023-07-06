package at.campus02.swd.game.observer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class EnemyPositionObserver implements PositionObserver {


    @Override
    public void updatePosition(float x, float y, float rotation) {
        System.out.println(LocalDateTime.now() + " - Enemy at: x = " + x + ", y = " + y + ", rot = " + rotation + "^");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("log.txt", true))) {
            bw.write(LocalDateTime.now() + " - Enemy at: x = " + x + ", y = " + y + ", rot = " + rotation + "°");
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
