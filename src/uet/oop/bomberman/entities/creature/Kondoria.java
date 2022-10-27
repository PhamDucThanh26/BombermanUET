package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.level.Game.bomberman;

public class Kondoria extends Creature {
    private final double pivotX;
    private final double pivotY;

    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            System.out.println("randomed");
            // randomize movement
            Random random = new Random();
            if(random.nextInt(100) < 50 ) {
                moveHorizontal = !moveHorizontal;
                if (moveHorizontal) {
                    xVec = (random.nextInt(2) == 0 ? -1 : 1) * xVec;
                    yVec = 0;
                } else  {
                    yVec = (random.nextInt(2) == 0 ? -1 : 1) * yVec;
                    xVec = 0;
                }
            }

            if(random.nextInt(100) < 5) {
                xVec = -xVec;
                yVec = -yVec;
            }
        }
    };
    Timer timer = new Timer(true);

    public Kondoria (int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        moveHorizontal = true;
        pivotX = x;
        pivotY = y;
        xVec = 1;
        yVec = 1;
        SCORE = 50;
        timer.schedule(timerTask, 3000);
    }
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

    @Override
    protected void move() {

//        if(randomMove == 0) {
//            x += speed;
//        }
//        else if (randomMove == 1) {
//            x -= speed;
//
//        }
//        else if(randomMove == 2) {
//            y += speed;
//
//        }
//        else if(randomMove == 3) {
//            y -= speed;
//        }
//        bomberman.getBombs().forEach(Bomb -> {
//            if (collision(Bomb, this)) {
//                speed = 0;
//            }
//        });
//        if(collision && this.getX() % 32 == 0 && this.getY() % 32 == 0) {
//            speed = 0;
//        }
//        if(speed == 0) {
//            Random random = new Random();
//            randomMove = random.nextInt(4);
//            speed = 1;
//        }
        if(moveHorizontal) {

        }
        x += xVec;
        y += yVec;
        // out of bound then calculate direction and speed again
        if (Math.abs(x - pivotX ) > 3 * Sprite.SCALED_SIZE) {
            x -= xVec;
            xVec = -xVec;
        }

        if(Math.abs(y - pivotY ) > 3 * Sprite.SCALED_SIZE) {
            y -= yVec;
            yVec = -yVec;
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
