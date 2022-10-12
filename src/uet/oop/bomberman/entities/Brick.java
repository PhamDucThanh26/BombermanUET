package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;


public class Brick extends Entity implements IAnimation {
    private boolean exploded = false;
    private int frameCount = 0;
    private long startTime;

    private final Image[] brickAnimation = {
            Sprite.brick_exploded.getFxImage(),
            Sprite.brick_exploded1.getFxImage(),
            Sprite.brick_exploded2.getFxImage(),
    };

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        maskNumber = 1;
    }

    public void setExploded(boolean exploded) {
        if (exploded) {
            startTime = System.currentTimeMillis();
        }
        this.exploded = exploded;
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 30 / 1000;
    }

    @Override
    public void updateAnimation() {
        if (frame == FPS / 2) {
            flag = true;
            return;
        }
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        img = brickAnimation[frameCount];
    }

    @Override
    public void update() {
        if (exploded) {
            frame = getCurrentFrame();
            updateAnimation();
        }
    }
}

