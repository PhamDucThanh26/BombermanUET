package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.yourScore;
import static uet.oop.bomberman.level.Game.bomberman;

public class Oneal extends Creature {
    private double playerX;
    private double playerY;
    final Image[] leftAnimation = {
            Sprite.oneal_left1.getFxImage(),
            Sprite.oneal_left2.getFxImage(),
            Sprite.oneal_left3.getFxImage()
    };

    final Image[] rightAnimation = {
            Sprite.oneal_right1.getFxImage(),
            Sprite.oneal_right2.getFxImage(),
            Sprite.oneal_right3.getFxImage()
    };

    final Image[] deadAnimation = {
            Sprite.oneal_dead.getFxImage(),
            Sprite.oneal_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

    public Oneal(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, width - 2, height - 2);
    }

    @Override
    public void move() {
        if (collision) {
            xVec = 0;
            yVec = 0;
            collision = false;
        }
        x += xVec;
        y += yVec;
    }

    public void getPlayerPos(Entity e) {
        playerX = e.getX();
        playerY = e.getY();
    }

    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        if (xVec > 0) {
            img = rightAnimation[frameCount];
        } else {
            img = leftAnimation[frameCount];
        }
    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            getPlayerPos(bomberman);
            move();
            updateAnimation();
        } else {
            dead();
            if(this.flag) {
                yourScore += 100;
            }
        }
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
