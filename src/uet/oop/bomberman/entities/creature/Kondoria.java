package uet.oop.bomberman.entities.creature;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Kondoria extends Creature {
    private final double centerX;
    private final double centerY;
    private int randomMove;
    private boolean chooseToTurn = false;
    TimerTask randomTurn = new TimerTask() {
        @Override
        public void run() {
            Random random = new Random();
            if(random.nextInt(100) < 70) {
                chooseToTurn = true;
                System.out.println("choosed to move");
            }
        }
    };
    Timer timer = new Timer(true);
    private final Image[] deadAnimation = {
            Sprite.kondoria_dead.getFxImage(),
            Sprite.kondoria_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };
    private final Image[] moveAnimation = {
            Sprite.kondoria_left1.getFxImage(),
            Sprite.kondoria_right1.getFxImage(),
            Sprite.kondoria_left2.getFxImage(),
            Sprite.kondoria_right2.getFxImage(),
            Sprite.kondoria_left3.getFxImage(),
            Sprite.kondoria_right3.getFxImage(),
    };
    public Kondoria(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, 20, 20);
        SCORE = 50;
        centerX = x;
        centerY = y;
        Random random = new Random();
        randomMove = random.nextInt(4);
        timer.schedule(randomTurn, 0, 1000);
    }
    private void randomMove() {
        if(x % 32 < 2 && y % 32 < 2 && chooseToTurn) {
            System.out.println("in this");
            Random random = new Random();
            chooseToTurn = false;
            int nextMove = random.nextInt(4);
            while (nextMove == randomMove) {
                nextMove = random.nextInt(4);
            }
//            System.exit(1);
            randomMove = nextMove;
        }
        if (randomMove == 0) {
            xVec = 1;
        } else if (randomMove == 3) {
            xVec = -1;

        } else if (randomMove == 1) {
            yVec = 1;

        } else if (randomMove == 2) {
            yVec = -1;
        }
    }
    @Override
    protected void move() {
        Random random = new Random();
        xVec = 0;
        yVec = 0;
        randomMove();
        x += xVec;
        y += yVec;

        // border checking
        if (Math.abs(x - centerX) >= 3 * Sprite.SCALED_SIZE
                || Math.abs(y - centerY) >= 3 * Sprite.SCALED_SIZE) {
            x -= xVec;
            y -= yVec;
            int nextMove = random.nextInt(4);
            while (nextMove == randomMove) {
                nextMove = random.nextInt(4);
            }
            randomMove = nextMove;
        }
    }
    @Override
    public void update() {
        if (isLife) {
            super.update();
            move();
            updateAnimation();
        } else {
            dead();
        }
    }
    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 6;
        }
        img = moveAnimation[frameCount];
    }
    @Override
    public void dead() {
        timer.cancel();
        timer.purge();
        animateDead++;
        if (animateDead % 40 == 0) {
            frameCount++;
        }
        frameCount %= 5;
        img = deadAnimation[frameCount];
        if (frameCount == 4) {
            this.flag = true;
        }
    }
}
