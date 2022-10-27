package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Doll extends Creature {
    private final double pivotX;
    private final double pivotY;

    private int randomMove;

    private String direction;

    private int speed = 3;

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
        setRandomMove();
        switch (direction) {
            case "UP":
                moveUp();
                if(collision) {
                    moveDown();

                }
                break;
            case "DOWN":
                moveDown();
                if(collision) {
                    moveUp();
                    moveUp();
                }
                break;
            case "LEFT":
                moveLeft();
                if(collision) {
                    moveRight();
                    moveRight();
                }
                break;
            case "RIGHT":
                moveRight();
                if(collision) {
                    moveLeft();
                    moveLeft();

                } ;
                break;
        }



    }

    boolean checkLocation() {
        return ((int)y % 32 == 0 && (int)x % 32 == 0);
    }

    public void setRandomMove() {
        if (checkLocation()) {
            randomMove = new Random().nextInt(4);
            switch (randomMove) {
                case 0:
                    direction = "UP";
                    break;
                case 1:
                    direction = "DOWN";
                    break;
                case 2:
                    direction = "LEFT";
                    break;
                case 3:
                    direction = "RIGHT";
                    break;
            }
        }
    }
    private void moveUp() {
        y -= speed;
    }
    private void moveDown() {
        y += speed;
    }
    private void moveLeft() {
        x -= speed;
    }
    private void moveRight() {
        x += speed;
    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
//            move();
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
