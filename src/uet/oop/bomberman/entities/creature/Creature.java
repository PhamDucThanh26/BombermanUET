package uet.oop.bomberman.entities.creature;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.Game;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.Game.bomberman;

public abstract class Creature extends Entity implements IAnimation {
    public int SCORE;
    protected double pivot;
    protected boolean moveHorizontal;
    // animation timer
    long startTime = System.currentTimeMillis();
    protected int animateDead = 0;
    protected int frameCount = 0;

    // movement vector
    protected int xVec = 0;
    protected int yVec = 0;

    // collision detection
    protected Rectangle solidArea;

    public Creature(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x, y, width, height);
        nodeNumber = 2;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime - Game.totalPauseDuration) * 60 / 1000;
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
        if(collision(this, bomberman ) && this.isLife) {
            bomberman.setLife(false);
        }
    }
    public abstract void dead();
}
