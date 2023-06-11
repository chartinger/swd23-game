package at.campus02.swd.game.board;

import at.campus02.swd.game.util.Position;

import java.util.Collections;
import java.util.List;

public record AidPack(List<Position> repairs, int cost) {
    public static final AidPack EMPTY_PACK = new AidPack(Collections.emptyList(), 0);

    public static AidPack empty() {
        return EMPTY_PACK;
    }
}
