package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Oneal extends Creature {
    int playerX;
    int playerY;
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
        solidArea = new Rectangle(x + 1, y + 1, width - 2, height - 2);
    }

    @Override
    public void move() {
        if(collision) {
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
        xVec = (x - playerX) > 0 ? -1 : 1;
        yVec = (y - playerY) > 0 ? -1 : 1;
    }
}
