package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.entities.Bomber.bomb;


public class Brick extends Creature {

    private final Image[] brickAnimation = {
            Sprite.brick_exploded.getFxImage(),
            Sprite.brick_exploded1.getFxImage(),
            Sprite.brick_exploded2.getFxImage(),
    };
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        maskNumber = '*';
    }
    long startTime = System.currentTimeMillis();

    boolean destroyed = false;

    public final int MAX_ANIMATE = 7500;
    int _animate = 0;
    public void swapAnimation() {

        if (destroyed && bomb.isExploded) {
            _animate++;
            if(_animate > 30) {
                frameCount++;
                _animate = 0;
            }
            frameCount = frameCount % 3;
            this.setImg(brickAnimation[frameCount]);
            if(frameCount == 2) {
                this.flag = true;
            }

        }
    }
    @Override
    public void update() {
            swapAnimation();


    }

    @Override
    public void updateAnimation() {

    }

    public void destroy() {
        this.destroyed = true;

    }

    protected Sprite movingSprite(Sprite normal, Sprite x1, Sprite x2) {
        int calc = _animate % 30;

        if(calc < 10) {
            return normal;
        }

        if(calc < 20) {
            return x1;
        }

        return x2;
    }

}

