package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Creature {
    public boolean flag = false;
    private int frameCount = 0;
    static final Image[] activeBom = {
            Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(),
    };
    static final Image[] bombExplode = {
            Sprite.bomb_exploded.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded2.getFxImage(),
    };
    private int lifeSpan = 0;
    private long timeBomb;
    public static int bombNumber = 20;
    public static int isBomb = 0;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    private void updateActiveAnimation() {
        long now = System.currentTimeMillis();

        if( (startTime - now) % 200 == 0) {
            frameCount++;
            frameCount = frameCount % 2;
        }
        if(lifeSpan == 20) {
            ExplosionBomb();
        }
        if(lifeSpan == 24) {
            flag = true;
        }

    }

    public void activeBomb() {
        this.setImg(activeBom[frameCount]);
    }
    public void ExplosionBomb() {
        long liveTime = System.currentTimeMillis() - startTime;
        if(liveTime > 2500) {
            this.flag = true;
        }
        else if(liveTime > 2000) {
            this.setImg(bombExplode[0]);
            System.out.println("explode");
        }
    }



    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
        ExplosionBomb();
    }
}
