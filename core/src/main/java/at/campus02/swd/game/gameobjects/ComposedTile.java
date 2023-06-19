package at.campus02.swd.game.gameobjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ComposedTile implements ITile {
    private ITile northWest;
    private ITile northEast;
    private ITile southWest;
    private ITile southEast;
    private int columnOffset = 0;
    private int rowOffset = 0;

    public ComposedTile(ITile northWest, ITile northEast, ITile southWest, ITile southEast) {
        this.northWest = northWest;
        this.northEast = northEast;
        this.southWest = southWest;
        this.southEast = southEast;

        setScale(1f);
        layoutTiles();
    }

    private void layoutTiles() {
        this.northWest.setRelativePosition(0, 1);
        this.northEast.setRelativePosition(1, 1);
        this.southWest.setRelativePosition(0, 0);
        this.southEast.setRelativePosition(1, 0);
    }

    @Override
    public void draw(SpriteBatch batch) {
        northWest.draw(batch);
        northEast.draw(batch);
        southWest.draw(batch);
        southEast.draw(batch);
    }

    @Override
    public void setPosition(float x, float y) {
        final float tileOffsetX = getWidth() * getScale() * columnOffset;
        final float tileOffsetY = getHeight() * getScale() * rowOffset;
        final float actualX = x + tileOffsetX;
        final float actualY = y + tileOffsetY;

        northWest.setPosition(actualX, actualY);
        northEast.setPosition(actualX, actualY);
        southWest.setPosition(actualX, actualY);
        southEast.setPosition(actualX, actualY);
    }

    @Override
    public void setVisible(boolean visible) {
        northWest.setVisible(visible);
        northEast.setVisible(visible);
        southWest.setVisible(visible);
        southEast.setVisible(visible);
    }

    @Override
    public void setScale(float scale) {
        northWest.setScale(.5f * scale);
        northEast.setScale(.5f * scale);
        southWest.setScale(.5f * scale);
        southEast.setScale(.5f * scale);
    }

    @Override
    public void setRelativePosition(int columnOffset, int rowOffset) {
        this.columnOffset = columnOffset;
        this.rowOffset = rowOffset;
    }

    @Override
    public float getWidth() {
        return northWest.getWidth();
    }

    @Override
    public float getHeight() {
        return northWest.getHeight();
    }

    @Override
    public float getScale() {
        return northWest.getScale() / .5f;
    }

    @Override
    public void rotate() {
        northWest.rotate();
        northEast.rotate();
        southWest.rotate();
        southEast.rotate();

        ITile temp = northWest;
        northWest = southEast;
        southEast = temp;
        temp = northEast;
        northEast = southWest;
        southWest = temp;

        layoutTiles();
    }

    @Override
    public void act(float delta) {
    }

}
