package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;
import uet.oop.bomberman.entities.Bomb;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Bomb.putBomb;

public class Bomber extends Entity {
    int xVec = 0;
    int yVec = 0;

    final int FPS = 60;
    int frameRate = 0;
    int frameCount = 0;
    private boolean stillEntityCollision = false;
    Image[] upAnimation = {
            Sprite.player_up.getFxImage(),
            Sprite.player_up_1.getFxImage(),
            Sprite.player_up_2.getFxImage()
    };

    Image[] downAnimation = {
            Sprite.player_down.getFxImage(),
            Sprite.player_down_1.getFxImage(),
            Sprite.player_down_2.getFxImage()
    };

    Image[] leftAnimation = {
            Sprite.player_left.getFxImage(),
            Sprite.player_left_1.getFxImage(),
            Sprite.player_left_2.getFxImage()
    };

    Image[] rightAnimation = {
            Sprite.player_right.getFxImage(),
            Sprite.player_right_1.getFxImage(),
            Sprite.player_right_2.getFxImage()
    };

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public boolean isStillEntityCollision() {
        return stillEntityCollision;
    }

    public void setStillEntityCollision(boolean stillEntityCollision) {
        this.stillEntityCollision = stillEntityCollision;
    }

    public void update(Keyboard a) {
        updateMove(a);
    }

    public void updateMove(Keyboard a) {
        xVec = 0;
        yVec = 0;
        if(a.up) {
            yVec -= 2;
            this.setImg(upAnimation[frameCount]);
        }
        if(a.down) {
            yVec += 2;
            this.setImg(downAnimation[frameCount]);
        }
        if(a.left) {
            xVec -= 2;
            this.setImg(leftAnimation[frameCount]);
        }
        if(a.right)  {
            xVec += 2;
            this.setImg(rightAnimation[frameCount]);
        }
    }

    private void updateAnimation() {
        frameRate++;
        if(FPS / frameRate == 4) {
            frameRate = 0;
            frameCount++;
            frameCount %= 3;
        }
    }

    private void move() {
        if(stillEntityCollision) {
            xVec = 0;
            yVec = 0;
            stillEntityCollision = false;
        }
        x += xVec;
        y += yVec;


        if( x + Sprite.wall.getSize() > 1080 ||
                x <= Sprite.wall.getSize()  ) {
            x -= xVec;
        }
        if( y + Sprite.wall.getSize() > 720 ||
                y <= Sprite.wall.getSize() ) {
            y -= yVec;
        }
    }

    public void updateMove(Keyboard a) {
        xVec = 0;
        yVec = 0;
        if(a.up) yVec -= 1;
        if(a.down) yVec += 1;
        if(a.left) xVec -= 1;
        if(a.right) xVec += 1;
        if(a.plant_bomb) {
            putBomb();
        }
    @Override
    public boolean intersects(Entity spr) {
        this.stillEntityCollision = super.intersects(spr);
        return this.stillEntityCollision;
    }

    @Override
    public void update() {
        move();
        updateAnimation();
    }
}
