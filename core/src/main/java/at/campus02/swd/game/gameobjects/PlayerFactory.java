package at.campus02.swd.game.gameobjects;

public class PlayerFactory {
    private final TilePositioner tilePositioner;

    public PlayerFactory(TilePositioner tilePositioner) {
        this.tilePositioner = tilePositioner;
    }
    /**
     * Creates a new tile
     * @return a new tile to be drawn at the given position on the board
     */
    public Player create(PlayerType type, int column, int line) {
        int textureId;
        switch (type) {
            case READY_PLAYER_ONE:
                textureId = 94;
                break;
            default:
                throw new IllegalArgumentException();
        }

        String textureFile = String.format("sprites/mapTile_%03d.png", textureId);
        Player player = new Player(textureFile);
        player.setPosition(tilePositioner.translateToX(column), tilePositioner.translateToY(line));
        return player;
    }
}
