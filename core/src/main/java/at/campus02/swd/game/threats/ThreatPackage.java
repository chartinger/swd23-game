package at.campus02.swd.game.threats;

import at.campus02.swd.game.game.BoardView;
import at.campus02.swd.game.game.ThreatStrategy;
import at.campus02.swd.game.util.Position;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ThreatPackage implements ThreatStrategy {
    private final List<ThreatStrategy> threats;

    public static Builder withThreat(ThreatStrategy.Builder threatBuilder) {
        return new Builder(threatBuilder);
    }

    private ThreatPackage(List<ThreatStrategy> threats) {
        this.threats = List.copyOf(threats);
    }

    @Override
    public List<Position> wreakHavoc() {
        return threats.stream()
            .map(ThreatStrategy::wreakHavoc)
            .flatMap(Collection::stream)
            .toList();
    }

    public static class Builder implements ThreatStrategy.Builder {
        private final List<ThreatStrategy.Builder> threatBuilders = new ArrayList<>();

        public Builder(ThreatStrategy.Builder threatBuilder) {
            threatBuilders.add(threatBuilder);
        }

        @Override
        public ThreatStrategy forBoard(BoardView gameBoard) {
            List<ThreatStrategy> threats = threatBuilders.stream()
                .map(threatBuilder -> threatBuilder.forBoard(gameBoard))
                .toList();
            return new ThreatPackage(threats);
        }

        public Builder andThreat(ThreatStrategy.Builder threatBuilder) {
            threatBuilders.add(threatBuilder);
            return this;
        }
    }
}
