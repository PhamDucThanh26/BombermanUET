package uet.oop.bomberman.entities.creature;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;
import java.util.Random;

public class Kondoria extends Creature {
    private double centerX;
    private double centerY;
    private int randomMove;
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
        SCORE = 50;
        centerX = x;
        centerY = y;
        Random random = new Random();
        randomMove = random.nextInt(4);
    }
    private void randomMove() {
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

        if (Math.abs(x - centerX) >= 5 * Sprite.SCALED_SIZE
                || Math.abs(y - centerY) >= 8 * Sprite.SCALED_SIZE) {
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
            System.out.println(x + " " + y);
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
