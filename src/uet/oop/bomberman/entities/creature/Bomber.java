package uet.oop.bomberman.entities.creature;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public final class Bomber extends Creature {
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

    private int bombNumber = 1;
    public static int bombPower = 1;
    private int speed = 2;
    public Keyboard kb = new Keyboard();
    private final List<Bomb> bombs = new ArrayList<>();


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
        if (bombs.size() <= bombNumber) {
            int xpos = x / 32;
            double ypos = (double) y / 32;
            xpos = Math.round(xpos);
            ypos = Math.round(ypos);
            bombs.add(new Bomb(xpos, (int) ypos, Sprite.bomb.getFxImage(), bombPower));
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
        bombs.removeIf(Entity::isFlag);
        bombs.forEach(Bomb::update);
        xVec = 0;
        yVec = 0;
        solidArea.setX(x);
        solidArea.setY(y + 8);
    }

    @Override
    public void render(GraphicsContext gc) {
        bombs.forEach(bomb -> bomb.render(gc));
        super.render(gc);
    }
}