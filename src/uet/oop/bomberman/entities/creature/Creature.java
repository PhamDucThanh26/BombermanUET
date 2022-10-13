package uet.oop.bomberman.entities.creature;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.IGameEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class Creature extends Entity implements IAnimation, IGameEntity {
    public static List<Creature> creatures = new ArrayList<>();
    protected int frameCount = 0;

    // movement vector
    protected int xVec = 0;
    protected int yVec = 0;

    // collision detection
    protected Rectangle solidArea;

    public Creature(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x, y, width, height);
    }
    long startTime = System.currentTimeMillis();

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 60 / 1000;
    }

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(solidArea.getX() + xVec, solidArea.getY() + yVec,
                solidArea.getWidth(), solidArea.getHeight());
    }

    @Override
    public void addStage() {
        creatures.add(this);
    }

    @Override
    public void clearStage() {
        creatures.clear();
    }

    protected abstract void move();

    @Override
    public void update() {
        frame = getCurrentFrame();
        solidArea.setX(x);
        solidArea.setY(y);
    }
}
