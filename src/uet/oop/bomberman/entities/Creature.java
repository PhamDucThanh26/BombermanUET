package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.IAnimation;

public abstract class Creature extends Entity implements IAnimation {

    protected int frameCount = 0;
    public Creature() {
    }

    // movement vector
    int xVec = 0;
    int yVec = 0;

    // collision detection
    protected boolean collision = false;
    protected Rectangle solidArea;
    protected int saX;
    protected int saY;
    protected int saWidth;
    protected int saHeight;

    public Creature(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        saX = 0;
        saY = 0;
        saWidth = (int) width;
        saHeight = (int) height;
    }
    long startTime = System.currentTimeMillis();

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 60 / 1000;
    }

    protected void move() {

    }

    @Override
    public void update() {
        frame = getCurrentFrame();
    }
}
