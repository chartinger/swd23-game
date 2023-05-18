package at.campus02.swd.game.gameobjects;

import java.util.Objects;

public class PlayerFactory implements GameObjectFactory<PlayerType> {
    @Override
    public Player create(PlayerType type) {
        int textureId = switch (Objects.requireNonNull(type)) {
            case READY_PLAYER_ONE -> 94;
        };

        String textureFile = String.format("sprites/mapTile_%03d.png", textureId);
        return new Player(textureFile);
    }
}
