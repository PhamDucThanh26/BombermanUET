package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

    private Flame flameLeft = new Flame();
    private Flame flameRight = new Flame();
    private Flame flameUp = new Flame();
    private Flame flameDown = new Flame();
    public static int bombPower = 1;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public boolean flag = false;
    private final int FPS = 60;
    private int frameRate = 0;
    private int frameCount = 0;

    static final Image[] activeBom = {
            Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(),
            Sprite.bomb_exploded.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded2.getFxImage(),
    };
    static final Image[] bombExplode = {

    };
    private int lifeSpan = 0;
    public int getFrameCount() {
        return this.frameCount;
    }
    private void updateActiveAnimation() {
        frameRate++;
        if (FPS / frameRate == 1) {
            frameRate = 0;
            frameCount++;
            frameCount = frameCount % 5;
            lifeSpan++;
            System.out.println(lifeSpan);

        }
        if (lifeSpan == 10) {
            flag = true;
        }

    }

        public void activeBomb() {
            System.out.println("Bomb location:" + this.getX() + " " + this.getY());
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                this.setImg(activeBom[frameCount]);
                if(lifeSpan > 6) {
                    flameLeft.addFrameLeft(this);
                    flameUp.addFrameUp(this);
                    flameDown.addFrameDown(this);
                    flameRight.addFrameRight(this);
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        } ).start();
    }
//    public void explosionBomb() {
//
//        if(lifeSpan > 6) {
//            int x = bomberman.getX() / 32;
//            int y = bomberman.getY() / 32 - 1;
//            upFlame = new Bomb(x , y , Sprite.explosion_vertical.getFxImage());
//
//            System.out.println("Toa do x va y UpFlame : " + upFlame.getX() + " " +  upFlame.getY());
////            entities.add(upFlame);
//
//        }
//    }


    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
//        explosionBomb();
//

    }
}
