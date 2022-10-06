package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

public class Creature extends Entity {
    // movement vector
    int xVec = 0;
    int yVec = 0;

    // collision detection
    protected boolean collision = false;
    protected Rectangle2D solidArea;

    // timer
    final int FPS = 60;
    protected final long startTime = System.currentTimeMillis();
    protected long frame = getFrame();
    public Creature(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Rectangle2D getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle2D solidArea) {
        this.solidArea = solidArea;
    }

    protected long getFrame() {
        return (System.currentTimeMillis() - startTime) * 60 / 1000;
    }

    protected void move() {
    }
    @Override
    public void update() {
        frame = getFrame();
    }
}
