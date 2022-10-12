package uet.oop.bomberman.entities;

import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.entities.Bomb.bombPower;

public final class Bomber extends Creature {

    private int speed = 2;
    public Keyboard kb = new Keyboard();
    private int bombNumber = 1;

    public int getBombNumber() {
        return bombNumber;
    }

    public void setBombNumber(int bombNumber) {
        this.bombNumber = bombNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    int frameCount = 0;
    final Image[] upAnimation = {
            Sprite.player_up.getFxImage(),
            Sprite.player_up_1.getFxImage(),
            Sprite.player_up_2.getFxImage()
    };
    final Image[] downAnimation = {
            Sprite.player_down.getFxImage(),
            Sprite.player_down_1.getFxImage(),
            Sprite.player_down_2.getFxImage()
    };

    final Image[] leftAnimation = {
            Sprite.player_left.getFxImage(),
            Sprite.player_left_1.getFxImage(),
            Sprite.player_left_2.getFxImage()
    };
    final Image[] rightAnimation = {
            Sprite.player_right.getFxImage(),
            Sprite.player_right_1.getFxImage(),
            Sprite.player_right_2.getFxImage()
    };

//    public static int[] bomberMask = {
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//    };

    public Bomber(int x, int y, Image img) {
        super(x, y, img);
        solidArea = new Rectangle(x, y + 8, 24, 24);
        maskNumber = 1;
    }

    public void updateMove() {
        xVec = 0;
        yVec = 0;
        if (kb.up) {
            yVec -= speed;
        }
        if (kb.down) {
            yVec += speed;
        }
        if (kb.left) {
            xVec -= speed;
        }
        if (kb.right) {
            xVec += speed;
        }
    }

    public void putBomb() {
        if (bombNumber > 0) {
            int xpos = x / 32;
            double ypos = (double) y / 32;
            xpos = Math.round(xpos);
            ypos = Math.round(ypos);
            Bomb bomb = new Bomb(xpos, (int) ypos, Sprite.bomb.getFxImage(), bombPower);
            entities.add(bomb);
            bombNumber--;
        }
    }

    private void updateAction() {
        if (kb.plant_bomb) {
            this.putBomb();
            kb.plant_bomb = false;
        }
    }

    public void kbUpdate() {
        updateMove();
        updateAction();
    }

    @Override
    public void move() {
        if (collision) {
            xVec = 0;
            yVec = 0;
            collision = false;
            return;
        }
        x += xVec;
        y += yVec;
    }

    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        if (kb.up) {
            this.setImg(upAnimation[frameCount]);
        }
        if (kb.down) {
            this.setImg(downAnimation[frameCount]);
        }
        if (kb.left) {
            this.setImg(leftAnimation[frameCount]);
        }
        if (kb.right) {
            this.setImg(rightAnimation[frameCount]);
        }
    }

    @Override
    public void update() {
        super.update();
        move();
        updateAnimation();

        solidArea.setX(x);
        solidArea.setY(y + 8);
    }
}