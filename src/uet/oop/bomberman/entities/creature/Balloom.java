package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.graphics.Map.heightMap;
import static uet.oop.bomberman.graphics.Map.widthMap;
import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.level.Game.stillObjects;

public class Balloom extends Creature {

    private int randomMove;
    private int speed = 1;


    private String direction;

    final Image[] deadAnimation = {
            Sprite.balloom_dead.getFxImage(),
            Sprite.balloom_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };


    final Image[] moveAnimation = {
            Sprite.balloom_left1.getFxImage(),
            Sprite.balloom_right1.getFxImage(),
            Sprite.balloom_left2.getFxImage(),
            Sprite.balloom_right2.getFxImage(),
            Sprite.balloom_left3.getFxImage(),
            Sprite.balloom_right3.getFxImage()
    };

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x, y, width , height);
        SCORE = 50;
    }



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

                }
                break;
            case "LEFT":
                moveLeft();
                if(collision) {
                   moveRight();

                }
                break;
            case "RIGHT":
                moveRight();
                if(collision) {
                    moveLeft();

                } ;
                break;
        }



    }

    boolean checkLocation() {
        return (y % 32 == 0 && x % 32 == 0);
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
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 6;
        }
        img = moveAnimation[frameCount];
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
