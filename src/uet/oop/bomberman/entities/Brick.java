package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Entity implements IAnimation {
    private boolean exploded = false;
    private int frameCount = 0;
    private long startTime;
    protected int animate = 0;
    private final Image[] brickAnimation = {
            Sprite.brick_exploded.getFxImage(),
            Sprite.brick_exploded1.getFxImage(),
            Sprite.brick_exploded2.getFxImage(),
            Sprite.brick_exploded.getFxImage(),
    };

    public Brick(int x, int y, Image img) {
        super(x, y, img);
        nodeNumber = 1;
    }

    public void setExploded(boolean exploded) {
        if (exploded) {
            startTime = System.currentTimeMillis();
        }
        this.exploded = exploded;
    }

    @Override
    public void updateAnimation() {
        animate++;
        if (animate > 10) {
            frameCount++;
            animate = 0;
        }
        frameCount %= 4;
        img = brickAnimation[frameCount];
        if (frameCount == 3) this.flag = true;
    }

    @Override
    public void update() {
        if (exploded) {
            frame = getCurrentFrame();
            updateAnimation();
        }
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 30 / 1000;
    }
}

