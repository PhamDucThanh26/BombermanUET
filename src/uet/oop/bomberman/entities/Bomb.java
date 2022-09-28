package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Bomb extends Entity {

    private static long timeBomb;
    private static long timeTmp;
    private static Entity bomb;
    private static int swapActive = 1;
    private static int swapExplosion = 1;
    private static final List<Entity> listBombMiddleW = new ArrayList<>();
    private static final List<Entity> listBombMiddleH = new ArrayList<>();
    public static int powerBomb = 0;
    private static int powerBombDown = 0;
    private static int powerBombUp = 0;
    private static int powerBombLeft = 0;
    private static int powerBombRight = 0;

    private static Entity edge_down = null;
    private static Entity edge_up = null;
    private static Entity edge_left = null;
    private static Entity edge_right = null;
    private static boolean isEdge = false;
    private static boolean isMiddle = false;
    public static int bombNumber = 20;
    public static int timeNumber = 120;
    public static int isBomb = 0;
    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public static void putBomb(Entity bomberman) {
        if (isBomb == 0 && bombNumber > 0) {
            bombNumber--;
            isBomb = 1;
            timeBomb = System.currentTimeMillis();
            timeTmp = timeBomb;
            int x = bomberman.getX() / 32;
            int y = bomberman.getY() / 32 + 1;
            x = Math.round(x);
            y = Math.round(y);
            bomb = new Bomb(x, y, Sprite.bomb.getFxImage());
            entities.add(bomb);
        }
    }
    public static void activeBomb() {
        if(swapActive == 1) {
            bomb.setImg(Sprite.bomb.getFxImage());
            swapActive = 2;
        }
        else if(swapActive == 2) {
            bomb.setImg(Sprite.bomb_1.getFxImage());
            swapActive = 3;
        }
        else if(swapActive == 3) {
            bomb.setImg(Sprite.bomb_2.getFxImage());
            swapActive = 3;
            swapActive = 4;
        }
        else {
            bomb.setImg(Sprite.bomb_1.getFxImage());
        }
    }


    @Override
    public void update() {

    }
}
