package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
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
    protected Rectangle solidArea;

    public Creature(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x, y, width, height);
    }
    long startTime = System.currentTimeMillis();

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

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(solidArea.getX() + xVec, solidArea.getY() + yVec,
                solidArea.getWidth(), solidArea.getHeight());
    }

    protected abstract void move();

    @Override
    public void update() {
        frame = getCurrentFrame();
        solidArea.setX(x);
        solidArea.setY(y);
    }
}
