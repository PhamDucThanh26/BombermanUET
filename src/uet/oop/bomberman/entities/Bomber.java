package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import static uet.oop.bomberman.BombermanGame.entities;

public final class Bomber extends Creature {
    public Keyboard kb = new Keyboard();
    public int bombCD = 0;
    public static Bomb bomb = new Bomb();
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

    @Override
    public Rectangle2D getBoundary() {
        return new Rectangle2D(solidArea.getX() + xVec, solidArea.getY() + yVec,
                solidArea.getWidth(), solidArea.getHeight());
    }

    public void updateMove() {
        xVec = 0;
        yVec = 0;
        if (kb.up) {
            yVec -= 2;
            this.setImg(upAnimation[frameCount]);
        }
        if (kb.down) {
            yVec += 2;
            this.setImg(downAnimation[frameCount]);
        }
        if (kb.left) {
            xVec -= 2;
            this.setImg(leftAnimation[frameCount]);
        }
        if (kb.right) {
            xVec += 2;
            this.setImg(rightAnimation[frameCount]);
        }
    }

    public void putBomb() {
        if (bombCD < 0) {
            bombCD = 2 * FPS;
            int xpos = x / 32;
            double ypos = (double) y / 32;
            xpos = Math.round(xpos);
            ypos = Math.round(ypos);
            bomb = new Bomb(xpos, (int) ypos, Sprite.bomb.getFxImage());
            entities.add(bomb);
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
    }

    @Override
    public void update() {
        super.update();
        move();
        updateAnimation();
        bombCD--;
        solidArea.setX(x);
        solidArea.setY(y + 8);
    }
}