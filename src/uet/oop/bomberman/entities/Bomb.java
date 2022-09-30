package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

    private static long timeBomb;
    private static long timeTmp;
    static Entity bomb;
    static int swapActive = 1;
    static int swapExplosion = 1;
    private static List <Entity> list_bomb_middle_width = new ArrayList<>();
    private static List <Entity> list_bomb_middle_height = new ArrayList<>();
    private static int powerBomb = 0;
    private static int powerBombDown = 0;
    private static int powerBombUp = 0;
    private static int powerBombLeft = 0;
    private static int powerBombRight = 0;
    private static Entity edgeDown = null;
    private static Entity edgeRight = null;
    private static Entity edgeLeft = null;
    private static Entity edgeUp = null;
    private static int isBomb = 0;
    private static boolean isEdge = false;
    private static boolean isMiddle = false;
    public static int bombNumber = 20;
    public static int timeNumber = 120;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void putBomb(){
        if(isBomb == 0 && bombNumber > 0){
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = bomberman.getX() / 32;
            int y = bomberman.getY() / 32;
            x = Math.round((float) x);
            y = Math.round((float) y );
            bomb = new Bomb(x,y, Sprite.bomb.getFxImage());
            entities.add(bomb);
            idObjects[bomberman.getX()][bomberman.getY()] = 4;
        }
    }

    public static void activeBomb() {   // Show the animation from the time the bomb is placed to the time it explodes
        if (swapActive == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            swapActive = 2;
        }
        else if (swapActive == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 3;
        }
        else if (swapActive == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            swapActive = 4;
        }
        else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 1;
        }
    }

    public static void createEdge() {   // Create an egde to prevent the character's movement as well as the explosion range of the bomb
        int i;
        if (Blocked.block_down_bomb(bomb, 0)) {
            edgeDown = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + 1, Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for (i = 1; i <= powerBomb && Blocked.block_down_bomb(bomb, i); ++i) {
                    edgeDown.setY(bomb.getY() + 32 + i * 32);
                    ++powerBombDown;
                }
            }

            block.add(edgeDown);
        }
        if (Blocked.block_up_bomb(bomb, 0)) {
            edgeUp = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - 1, Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for(i = 1; i <= powerBomb && Blocked.block_up_bomb(bomb, i); ++i) {
                    edgeUp.setY(bomb.getY() - 32 - i * 32);
                    ++powerBombUp;
                }
            }

            block.add(edgeUp);
        }

        if (Blocked.block_left_bomb(bomb, 0)) {
            edgeLeft = new Bomb(bomb.getX() / 32 - 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for(i = 1; i <= powerBomb && Blocked.block_left_bomb(bomb, i); ++i) {
                    edgeLeft.setX(bomb.getX() - 32 - i * 32);
                    ++powerBombLeft;
                }
            }

            block.add(edgeLeft);
        }

        if (Blocked.block_right_bomb(bomb, 0)) {
            edgeRight = new Bomb(bomb.getX() / 32 + 1, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            if (powerBomb > 0) {
                for(i = 1; i <= powerBomb && Blocked.block_right_bomb(bomb, i); ++i) {
                    edgeRight.setX(bomb.getX() + 32 + i * 32);
                    ++powerBombRight;
                }
            }

            block.add(edgeRight);
        }

    }
    public static void createMiddle() {     // Adjust the bomb to explode at the center position
        Entity middle;
        int i;
        for(i = 1; i <= powerBombDown; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 + i, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_height.add(middle);
        }

        for(i = 1; i <= powerBombUp; i++) {
            middle = new Bomb(bomb.getX() / 32, bomb.getY() / 32 - i, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_height.add(middle);
        }

        for(i = 1; i <= powerBombLeft; i++) {
            middle = new Bomb(bomb.getX() / 32 - i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_width.add(middle);
        }

        for(i = 1; i <= powerBombRight; i++) {
            middle = new Bomb(bomb.getX() / 32 + i, bomb.getY() / 32, Sprite.bomb_exploded.getFxImage());
            list_bomb_middle_width.add(middle);
        }

        block.addAll(list_bomb_middle_width);
        block.addAll(list_bomb_middle_height);
    }

    public static void explosionCenter() {      // Determine the explosion center of the bomb
        if (swapExplosion == 1) {
            bomb.setImg(Sprite.bomb_exploded.getFxImage());
            list_kill[bomb.getX() / 32][bomb.getY() / 32] = 4;
            if (Blocked.block_down_bomb(bomb, powerBombDown)) {
                edgeDown.setImg(Sprite.explosion_vertical_down_last.getFxImage());
                list_kill[edgeDown.getX() / 32][edgeDown.getY() / 32] = 4;
            }

            if (Blocked.block_up_bomb(bomb, powerBombUp)) {
                edgeUp.setImg(Sprite.explosion_vertical_top_last.getFxImage());
                list_kill[edgeUp.getX() / 32][edgeUp.getY() / 32] = 4;
            }

            if (Blocked.block_left_bomb(bomb, powerBombLeft)) {
                edgeLeft.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
                list_kill[edgeLeft.getX() / 32][edgeLeft.getY() / 32] = 4;
            }

            if (Blocked.block_right_bomb(bomb, powerBombRight)) {
                edgeRight.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
                list_kill[edgeRight.getX() / 32][edgeRight.getY() / 32] = 4;
            }

            if (list_bomb_middle_height.size() > 0) {
                for (Entity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical.getFxImage());
                    list_kill[e.getX() / 32][e.getY() / 32] = 4;
                }
            }

            if (list_bomb_middle_width.size() > 0) {
                for (Entity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal.getFxImage());
                    list_kill[e.getX() / 32][e.getY() / 32] = 4;
                }
            }

            swapExplosion = 2;
        }
        else if (swapExplosion == 2) {
            bomb.setImg(Sprite.bomb_exploded1.getFxImage());
            if (Blocked.block_down_bomb(bomb, powerBombDown)) {
                edgeDown.setImg(Sprite.explosion_vertical_down_last1.getFxImage());
            }

            if (Blocked.block_up_bomb(bomb, powerBombUp)) {
                edgeUp.setImg(Sprite.explosion_vertical_top_last1.getFxImage());
            }

            if (Blocked.block_left_bomb(bomb, powerBombLeft)) {
                edgeLeft.setImg(Sprite.explosion_horizontal_left_last1.getFxImage());
            }

            if (Blocked.block_right_bomb(bomb, powerBombRight)) {
                edgeRight.setImg(Sprite.explosion_horizontal_right_last1.getFxImage());
            }

            if (isMiddle) {
                for (Entity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical1.getFxImage());
                }
                for (Entity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal1.getFxImage());
                }
            }

            swapExplosion = 3;
        }
        else if (swapExplosion == 3) {
            if (Blocked.block_down_bomb(bomb, powerBombDown)) {
                edgeDown.setImg(Sprite.explosion_vertical_down_last2.getFxImage());
            }

            if (Blocked.block_up_bomb(bomb, powerBombUp)) {
                edgeUp.setImg(Sprite.explosion_vertical_top_last2.getFxImage());
            }

            if (Blocked.block_left_bomb(bomb, powerBombLeft)) {
                edgeLeft.setImg(Sprite.explosion_horizontal_left_last2.getFxImage());
            }

            if (Blocked.block_right_bomb(bomb, powerBombRight)) {
                edgeRight.setImg(Sprite.explosion_horizontal_right_last2.getFxImage());
            }

            if (isMiddle) {
                for (Entity e : list_bomb_middle_height) {
                    e.setImg(Sprite.explosion_vertical2.getFxImage());
                }
                for (Entity e : list_bomb_middle_width) {
                    e.setImg(Sprite.explosion_horizontal2.getFxImage());
                }
            }

            swapExplosion = 1;
        }

    }

    private static void checkActive() {     // Check what stages the bomb has gone through before detonating
        if (isBomb == 1) {
            if (System.currentTimeMillis() - timeBomb < 2000L) {
                if (System.currentTimeMillis() - timeTmp > 100L) {
                    activeBomb();
                    timeTmp += 100L;
                }
            }
            else {
                isBomb = 2;
                timeBomb = System.currentTimeMillis();
                timeTmp = timeBomb;
            }
        }
    }
    private static void checkExplosion() {      // Check the bomb's detonation time after the bomb is activated
        if (isBomb == 2) {
            if (System.currentTimeMillis() - timeBomb < 1000L) {
                if (System.currentTimeMillis() - timeTmp > 100L) {
                    if (!isEdge) {
                        createEdge();
                        isEdge = true;
                    }

                    if (powerBomb > 0 && !isMiddle) {
                        createMiddle();
                        isMiddle = true;
                    }


                    explosionCenter();
                    timeTmp += 100L;
                }
            }
            else {
                isBomb = 0;
                idObjects[bomb.getX() / 32][bomb.getY() / 32] = 0;
                list_kill[bomb.getX() / 32][bomb.getY() / 32] = 0;
                bomb.setImg(Sprite.bomb.getFxImage());
                if (Blocked.block_down_bomb(bomb, powerBombDown)) {
                    edgeDown.setImg(Sprite.bomb.getFxImage());
                    idObjects[edgeDown.getX() / 32][edgeDown.getY() / 32] = 0;
                    list_kill[edgeDown.getX() / 32][edgeDown.getY() / 32] = 0;
                }

                if (Blocked.block_up_bomb(bomb, powerBombUp)) {
                    edgeUp.setImg(Sprite.bomb.getFxImage());
                    idObjects[edgeUp.getX() / 32][edgeUp.getY() / 32] = 0;
                    list_kill[edgeUp.getX() / 32][edgeUp.getY() / 32] = 0;
                }

                if (Blocked.block_left_bomb(bomb, powerBombLeft)) {
                    edgeLeft.setImg(Sprite.bomb.getFxImage());
                    idObjects[edgeLeft.getX() / 32][edgeLeft.getY() / 32] = 0;
                    list_kill[edgeLeft.getX() / 32][edgeLeft.getY() / 32] = 0;
                }

                if (Blocked.block_right_bomb(bomb, powerBombRight)) {
                    edgeRight.setImg(Sprite.bomb.getFxImage());
                    idObjects[edgeRight.getX() / 32][edgeRight.getY() / 32] = 0;
                    list_kill[edgeRight.getX() / 32][edgeRight.getY() / 32] = 0;
                }

                if (isMiddle) {
                    for (Entity e : list_bomb_middle_width) {
                        list_kill[e.getX() / 32][e.getY() / 32] = 0;
                        idObjects[e.getX() / 32][e.getY() / 32] = 0;
                    }
                    for (Entity e : list_bomb_middle_height) {
                        list_kill[e.getX() / 32][e.getY() / 32] = 0;
                        idObjects[e.getX() / 32][e.getY() / 32] = 0;
                    }
                }

                block.removeAll(list_bomb_middle_height);
                block.removeAll(list_bomb_middle_width);
                list_bomb_middle_height.clear();
                list_bomb_middle_width.clear();
                isEdge = false;
                isMiddle = false;
                powerBombDown = 0;
                powerBombUp = 0;
                powerBombLeft = 0;
                powerBombRight = 0;
            }
        }

    }

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





    private void updateActiveAnimation() {
        frameRate++;
        if (FPS / frameRate == 1) {
            frameRate = 0;
            frameCount++;
            frameCount = frameCount % 2;
            lifeSpan++;
            System.out.println(lifeSpan);
        }
        if (lifeSpan == 30) {
            flag = true;
        }

    }

//        public void activeBomb() {
//        this.setImg(activeBom[frameCount]);

//    }
    public void ExplosionBomb() {
//
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
//        updateActiveAnimation();
//        activeBomb();
////        ExplosionBomb();
        checkActive();
        checkExplosion();
    }
}
