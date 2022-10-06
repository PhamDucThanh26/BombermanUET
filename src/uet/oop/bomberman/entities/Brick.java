package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;


public class Brick extends Entity {

    private final Image[] brickAnimation = {
            Sprite.brick_exploded.getFxImage(),
            Sprite.brick_exploded1.getFxImage(),
            Sprite.brick_exploded2.getFxImage(),
    };
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }
    long startTime = System.currentTimeMillis();

    public void swapAnimation() {

        long liveTime = System.currentTimeMillis() - startTime;

        if(liveTime > 1000 && liveTime - startTime <= 2500) {
            this.setImg(brickAnimation[0]);
        }
        if(liveTime > 2500 && liveTime <= 4000) {
            this.setImg(brickAnimation[1]);
        }
        if(liveTime > 4000 && liveTime < 5000 ) {
            this.setImg(brickAnimation[2]);
        }
        if(liveTime >= 5000) {
            this.setImg(Sprite.grass.getFxImage());
        }
    }
    @Override
    public void update() {
    }
}

