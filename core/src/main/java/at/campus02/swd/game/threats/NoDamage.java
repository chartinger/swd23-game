package at.campus02.swd.game.threats;

import at.campus02.swd.game.board.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.Collections;
import java.util.List;

public class NoDamage implements ThreatStrategy {
    public static ThreatStrategy.Builder builder() {
        return __ -> new NoDamage();
    }

    @Override
    public List<Position> wreakHavoc() {
        return Collections.emptyList();
    }
}
