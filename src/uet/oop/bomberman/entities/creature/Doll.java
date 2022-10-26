package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Creature {
    private final double pivotX;
    private final double pivotY;

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        pivotX = x;
        pivotY = y;
        SCORE = 50;
    }

    private final Image[] deadAnimation = {
            Sprite.doll_dead.getFxImage(),
            Sprite.doll_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

//    private final Image[] leftAnimation = {
//            Sprite.doll_left1.getFxImage(),
//            Sprite.doll_left2.getFxImage(),
//            Sprite.doll_left3.getFxImage(),
//    };

    private final Image[] moveAnimation = {
            Sprite.doll_left1.getFxImage(),
            Sprite.doll_right1.getFxImage(),
            Sprite.doll_left2.getFxImage(),
            Sprite.doll_right2.getFxImage(),
            Sprite.doll_left3.getFxImage(),
            Sprite.doll_right3.getFxImage(),
    };




    @Override
    protected void move() {
        xVec = 0;
        yVec = 0;

        if (collision) {
            xVec = -xVec;
            yVec = -yVec;
            collision = false;
        }
        x += xVec;
        if (x + xVec > pivotX + 3 * Sprite.SCALED_SIZE || x + xVec < pivotX - 3 * Sprite.SCALED_SIZE) {
            x -= xVec;
            xVec = -xVec;
        }
    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            move();
            updateAnimation();
            solidArea.setX(x + 1);
            solidArea.setY(y + 1);
        } else {
            dead();
        }
    }

    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 6;
        }
        img = moveAnimation[frameCount];
    }


    @Override
    public void dead() {
        animateDead++;
        if (animateDead % 40 == 0) {
            frameCount++;
        }

        frameCount %= 5;
        img = deadAnimation[frameCount];
        if (frameCount == 4) {
            this.flag = true;
        }
    }
}
