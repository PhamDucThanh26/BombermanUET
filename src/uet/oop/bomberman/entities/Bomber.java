package uet.oop.bomberman.entities;

import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.user_input.Keyboard;

public class Bomber extends Entity {
    int xVec = 0;
    int yVec = 0;

    final int FPS = 60;
    int frameRate = 0;
    int frameCount = 0;
    private boolean moving = false;

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

    @Override
    public void update() {

    }

    public void update(Keyboard a) {
        updateMove(a);
        move();
        updateAnimation();
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
            moving = true;
            this.setImg(downAnimation[frameCount]);
        }
        if(a.left) {
            xVec -= 2;
            moving = true;
            this.setImg(leftAnimation[frameCount]);
        }
        if(a.right)  {
            xVec += 2;
            moving = true;
            this.setImg(rightAnimation[frameCount]);
        }
        if(a.plant_bomb) {
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
        x += xVec;
        y += yVec;


        if( x + Sprite.DEFAULT_SIZE >= Sprite.SCALED_SIZE * 20 - 32 || x < 16 + 12) {
            x -= xVec;
        }
        if( y + Sprite.DEFAULT_SIZE >= Sprite.SCALED_SIZE * 15 - 45 || y < 16 + 12) {
            y -= yVec;
        }
    }

}
