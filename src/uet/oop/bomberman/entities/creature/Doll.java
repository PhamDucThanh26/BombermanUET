package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.level.Game.creatures;

public class Doll extends Creature {
    private final double pivot;
    private boolean bombDetected = false;

    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, 20, 20);
        pivot = x;
        xVec = 1;
        SCORE = 50;
    }

    private final Image[] deadAnimation = {
            Sprite.doll_dead.getFxImage(),
            Sprite.doll_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

    private final Image[] moveAnimation = {
            Sprite.doll_left1.getFxImage(),
            Sprite.doll_right1.getFxImage(),
            Sprite.doll_left2.getFxImage(),
            Sprite.doll_right2.getFxImage(),
            Sprite.doll_left3.getFxImage(),
            Sprite.doll_right3.getFxImage(),
    };

    Thread spawnUponDeath = new Thread(() -> {
        try {
            Thread.sleep(200);
            creatures.add(new Balloom( (int) (x + 10) / Sprite.SCALED_SIZE,
                    (int) y / Sprite.SCALED_SIZE, Sprite.doll_left1.getFxImage(), 1, true));
            creatures.add(new Balloom( (int) (x - 10) / Sprite.SCALED_SIZE,
                    (int) y / Sprite.SCALED_SIZE, Sprite.doll_left1.getFxImage(), -1, true));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    });

    @Override
    protected void move() {
        bomberman.getBombs().forEach(bomb -> {
            if( Math.abs(bomb.getX() - x) < 2 * Sprite.SCALED_SIZE
                    || Math.abs( bomb.getY() - y) < 2 * Sprite.SCALED_SIZE) {
                findSafePlace( (int) bomb.getX() / Sprite.SCALED_SIZE,
                        (int) bomb.getY() / Sprite.SCALED_SIZE);
                bombDetected = true;
            }
        });
    }

    private void findSafePlace(int dangerX, int dangerY) {

    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            move();
            updateAnimation();
            solidArea.setX(x + 1);
            solidArea.setY(y + 1);
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
            spawnUponDeath.start();
        }
    }
}
