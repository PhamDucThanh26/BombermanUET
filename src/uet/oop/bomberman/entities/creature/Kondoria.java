package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.graphics.Map.heightMap;
import static uet.oop.bomberman.graphics.Map.widthMap;
import static uet.oop.bomberman.level.Game.bomberman;

public class Kondoria extends Creature {
    public Kondoria (int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SCORE = 50;
    }
    private int speed = 2;
    private int randomMove;
    private final Image[] deadAnimation = {
            Sprite.kondoria_dead.getFxImage(),
            Sprite.kondoria_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

//    private final Image[] leftAnimation = {
//            Sprite.doll_left1.getFxImage(),
//            Sprite.doll_left2.getFxImage(),
//            Sprite.doll_left3.getFxImage(),
//    };

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

        if(randomMove == 0) {
            x += speed;
        }
        else if (randomMove == 1) {
            x -= speed;

        }
        else if(randomMove == 2) {
            y+= speed;

        }
        else if(randomMove == 3) {
            y -= speed;
        }
        bomberman.getBombs().forEach(Bomb -> {
            if (collision(Bomb, this)) {
                speed = 0;
            }
        });
        if(collision && this.getX() % 32 == 0 && this.getY() % 32 == 0) {
            speed = 0;
        }
        if(speed == 0) {

//            if(this.getX() % 32 == 0 && this.getY() % 32 == 0) {
            Random random = new Random();
            randomMove = random.nextInt(4);
            speed = 1;
//            }
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
