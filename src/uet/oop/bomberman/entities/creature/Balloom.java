package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

public class Balloom extends Creature {
    private final double pivot;

    final Image[] leftAnimation = {
            Sprite.balloom_left1.getFxImage(),
            Sprite.balloom_left2.getFxImage(),
            Sprite.balloom_left3.getFxImage()
    };

    final Image[] rightAnimation = {
            Sprite.balloom_right1.getFxImage(),
            Sprite.balloom_right2.getFxImage(),
            Sprite.balloom_right3.getFxImage()
    };

    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, width - 2, height - 2);
        pivot = x;
        NodesNumber = 1;
    }

    private double xVec = 1;

    protected void move() {
        if (collision) {
            xVec = -xVec;
            collision = false;
        }
        x += xVec;
        if (x + xVec > pivot + 3 * Sprite.SCALED_SIZE || x + xVec < pivot - 3 * Sprite.SCALED_SIZE) {
            x -= xVec;
            xVec = -xVec;
        }
    }

    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        if (xVec > 0) {
            img = leftAnimation[frameCount];
        } else {
            img = rightAnimation[frameCount];
        }
    }

    @Override
    public void update() {
        super.update();
        move();
        updateAnimation();
        solidArea.setX(x + 1);
        solidArea.setY(y + 1);
    }
}
