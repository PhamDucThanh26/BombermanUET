package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

    public boolean flag = false;
    private final int FPS = 60;
    private int frameRate = 0;
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
        frameRate++;
        if(FPS / frameRate == 2) {
            frameRate = 0;
            frameCount++;
            frameCount = frameCount % 2;
            lifeSpan++;
            System.out.println(lifeSpan);
        }
//        if(lifeSpan == 20) {
//            ExplosionBomb();
//        }
//        if(lifeSpan == 24) {
//            flag = true;
//        }

    }

    public void activeBomb() {
        this.setImg(activeBom[frameCount]);
    }
    int count = 0;
    public void ExplosionBomb() {
        count++;
        if(count == 2 * 1000) {
            this.setImg(bombExplode[0]);
            System.out.println("11:22");
        }
        if(count == 3 * 1000) {
            this.flag = true;
        }

    }



    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
        ExplosionBomb();
    }
}
