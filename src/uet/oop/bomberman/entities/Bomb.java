package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.entities;
import static uet.oop.bomberman.BombermanGame.gc;
import static uet.oop.bomberman.entities.Flame.isActive;

public class Bomb extends Creature {
    private Flame flameLeft = new Flame();

    public static int bombPower = 1;
    private Flame flameRight = new Flame();
    private Flame flameUp = new Flame();
    private Flame flameDown = new Flame();
    public boolean isExploded = false;
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

    public Flame getFlameLeft() {
        return flameLeft;
    }

    public void setFlameLeft(Flame flameLeft) {
        this.flameLeft = flameLeft;
    }

    public Flame getFlameRight() {
        return flameRight;
    }

    public void setFlameRight(Flame flameRight) {
        this.flameRight = flameRight;
    }

    public Flame getFlameUp() {
        return flameUp;
    }

    public void setFlameUp(Flame flameUp) {
        this.flameUp = flameUp;
    }

    public Flame getFlameDown() {
        return flameDown;
    }

    public void setFlameDown(Flame flameDown) {
        this.flameDown = flameDown;
    }

    private int lifeSpan = 0;

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
            isExploded = true;
            ExplosionBomb();
        }
        if(lifeSpan == 30) {
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
            this.isExploded = true;
            flameLeft.addFrameLeft(this);
            flameRight.addFrameRight(this);
            flameDown.addFrameDown(this);
            flameUp.addFrameUp(this);
        }
    }

    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
        ExplosionBomb();
    }
}