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

    boolean destroyed = false;

    public final int MAX_ANIMATE = 7500;
    int _animate = 0;
    public void swapAnimation() {
        if(destroyed) {
            long liveTime = System.currentTimeMillis() - startTime;

            if (liveTime > 1000 && liveTime - startTime <= 2500) {
                this.setImg(brickAnimation[0]);
            }
            if (liveTime > 2500 && liveTime <= 4000) {
                this.setImg(brickAnimation[1]);
            }
            if (liveTime > 4000 && liveTime < 5000) {
                this.setImg(brickAnimation[2]);
            }
            if (liveTime >= 5000) {
                this.setImg(Sprite.grass.getFxImage());
            }
        }
    }
    @Override
    public void update() {
        if(destroyed) {
            if(_animate < MAX_ANIMATE) {
                _animate++;
            }

        }
    }

    public void destroy() {
        this.destroyed = true;
    }

    public boolean collide(Entity e) {
        if(e instanceof Flame) {
            this.destroy();
        }
        return false;
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

