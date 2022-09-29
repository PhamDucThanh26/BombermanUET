package uet.oop.bomberman.entities;

import javafx.geometry.Rectangle2D;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import static uet.oop.bomberman.entities.Bomb.putBomb;

public class Bomber extends Entity {
    int xVec = 0;
    int yVec = 0;
    final int FPS = 60;
    int frameRate = 0;
    int frameCount = 0;
    private boolean collision = false;
    private Rectangle2D solidArea;
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
        solidArea = new Rectangle2D(x + 2, y + 6, 6, 6);
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public void update(Keyboard a) {
        updateMove(a);
        updateAction(a);
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

    private void updateAction(Keyboard a) {
        if(a.plant_bomb) {
            putBomb(this);
            a.plant_bomb = false;
        }
        if(a.speed_up) {

        }
    }

    @Override
    public void update() {
        move();
        updateAnimation();
        updateSolidArea();
    }

    private void move() {
        if(collision) {
            xVec = 0;
            yVec = 0;
            collision = false;
            return;
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

    // method update sprite animation
    private void updateAnimation() {
        frameRate++;
        if(FPS / frameRate == 4) {
            frameRate = 0;
            frameCount++;
            frameCount %= 3;
        }
    }

    private void updateSolidArea() {

    }

    @Override
    public boolean intersects(Entity spr) {
        Rectangle2D futureFrame = new Rectangle2D(
                x + xVec, y + 2 + yVec, 24, 28);
        return futureFrame.intersects(spr.getBoundary());
    }
}
