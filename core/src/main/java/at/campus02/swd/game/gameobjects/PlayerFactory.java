package at.campus02.swd.game.gameobjects;

public class PlayerFactory implements GameObjectFactory<PlayerType> {
    @Override
    public Player create(PlayerType type) {
        int textureId;
        switch (type) {
            case READY_PLAYER_ONE:
                textureId = 94;
                break;
            default:
                throw new IllegalArgumentException();
        }

        String textureFile = String.format("sprites/mapTile_%03d.png", textureId);
        return new Player(textureFile);
    }
}
