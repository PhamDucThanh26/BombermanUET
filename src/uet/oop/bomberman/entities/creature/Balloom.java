package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Creature {
    final Image[] deadAnimation = {
            Sprite.balloom_dead.getFxImage(),
            Sprite.balloom_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };
    final Image[] leftAnimation = {
            Sprite.balloom_left1.getFxImage(),
            Sprite.balloom_left2.getFxImage(),
            Sprite.balloom_left3.getFxImage()
    };

    final Image[] rightAnimation = {
            Sprite.balloom_right1.getFxImage(),
            Sprite.balloom_right2.getFxImage(),
            Sprite.balloom_right3.getFxImage()
    };
    private double pivot;
    boolean moveHorizontal;

    public Balloom(int xUnit, int yUnit, Image img, int speed, boolean moveHorizontal) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, width - 2, height - 2);
        this.moveHorizontal = moveHorizontal;
        if(moveHorizontal) {
            pivot = x;
            xVec = speed;
            yVec = 0;
        } else {
            pivot = x;
            yVec = speed;
            xVec = 0;
        }
        SCORE = 50;
    }

    protected void move() {
        if (collision) {
            xVec = -xVec;
            collision = false;
        }
        x += xVec;
        if(moveHorizontal) {
            if (x + xVec > pivot + 3 * Sprite.SCALED_SIZE || x + xVec < pivot - 3 * Sprite.SCALED_SIZE) {
                x -= xVec;
                xVec = -xVec;
            }
        } else {
            if (y + yVec > pivot + 3 * Sprite.SCALED_SIZE || y + yVec < pivot - 3 * Sprite.SCALED_SIZE) {
                y -= yVec;
                yVec = -yVec;
            }
        }
    }

    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        if (xVec > 0) {
            img = leftAnimation[frameCount];
        } else {
            img = rightAnimation[frameCount];
        }
    }
    @Override
    public void update() {
        if(isLife) {
            super.update();
            move();
            updateAnimation();
            solidArea.setX(x + 1);
            solidArea.setY(y + 1);
        }
        else {
            dead();
        }
    }

    @Override
    public void dead() {
        animateDead++;
        if(animateDead % 40 == 0) {
            frameCount++;
        }

        frameCount %= 5;
        img = deadAnimation[frameCount];
        if(frameCount == 4) {
            this.flag = true;
        }
    }
}
