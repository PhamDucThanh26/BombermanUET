package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Creature{
    int playerX;
    int playerY;
    int frameCount = 0;
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

    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void move() {
        xVec = (x - playerX) > 0 ? -1 : 1;
        yVec = (y - playerY) > 0 ? -1 : 1;
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
        if(xVec > 0) {
            img = rightAnimation[frameCount];
        }
        else {
            img = leftAnimation[frameCount];
        }
    }

    @Override
    public void update() {
        super.update();
        move();
        updateAnimation();
    }

}
