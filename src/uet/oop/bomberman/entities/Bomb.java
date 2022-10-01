package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

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





    private void updateActiveAnimation() {
        frameRate++;
        if (FPS / frameRate == 1) {
            frameRate = 0;
            frameCount++;
            frameCount = frameCount % 5;
            lifeSpan++;
            System.out.println(lifeSpan);

        }
        if (lifeSpan == 9) {
            flag = true;
        }

    }

        public void activeBomb() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                this.setImg(activeBom[frameCount]);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        } ).start();




    }
    public void ExplosionBomb() {

        new Thread(() -> {
            for (int i = 0; i < bombExplode.length; i++) {
                this.setImg(bombExplode[i]);
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            flag = true;
        }).start();
    }


    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
//

    }
}
