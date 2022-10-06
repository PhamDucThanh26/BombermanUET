package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends Creature {
    public boolean flag = false;
    public static boolean isExploded;
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
        if( frame % 15 == 0 && frame < 120) {
            frameCount++;
            frameCount = frameCount % 2;
            this.setImg(activeBom[frameCount]);
        } else {
            ExplosionBomb();
        }
    }

    public void ExplosionBomb() {
        if(frame == 120) {
            this.setImg(bombExplode[0]);
            System.out.println("explode");
        }
        else if(frame == 180) {
            this.flag = true;
        }
    }

    @Override
    public void update() {
        super.update();
        updateActiveAnimation();
    }
}
