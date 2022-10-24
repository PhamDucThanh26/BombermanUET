package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Minvo extends Creature {
    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SCORE = 200;
    }

    private final Image[] deadAnimation = {
            Sprite.minvo_dead.getFxImage(),
            Sprite.minvo_dead.getFxImage(),
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
            Sprite.minvo_left1.getFxImage(),
            Sprite.minvo_right1.getFxImage(),
            Sprite.minvo_left2.getFxImage(),
            Sprite.minvo_right2.getFxImage(),
            Sprite.minvo_left3.getFxImage(),
            Sprite.minvo_right3.getFxImage(),
    };




    @Override
    protected void move() {
        xVec = 0;
        yVec = 0;

    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            updateAnimation();
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

