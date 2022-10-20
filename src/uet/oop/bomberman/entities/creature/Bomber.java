package uet.oop.bomberman.entities.creature;

import SoundEffect.Sound;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import java.util.ArrayList;
import java.util.List;

public final class Bomber extends Creature {
    protected double screenX;
    protected double screenY;

    private int animateDead = 0;

    private boolean stillRender = true;
    final Image[] upAnimation = {
            Sprite.player_up.getFxImage(),
            Sprite.player_up_1.getFxImage(),
            Sprite.player_up_2.getFxImage()
    };
    protected final Image[] downAnimation = {
            Sprite.player_down.getFxImage(),
            Sprite.player_down_1.getFxImage(),
            Sprite.player_down_2.getFxImage()
    };

    protected final Image[] leftAnimation = {
            Sprite.player_left.getFxImage(),
            Sprite.player_left_1.getFxImage(),
            Sprite.player_left_2.getFxImage()
    };
    protected final Image[] rightAnimation = {
            Sprite.player_right.getFxImage(),
            Sprite.player_right_1.getFxImage(),
            Sprite.player_right_2.getFxImage()
    };

    protected final Image[] deadAnimation = {
            Sprite.player_dead1.getFxImage(),
            Sprite.player_dead1.getFxImage(),
            Sprite.player_dead2.getFxImage(),
            Sprite.player_dead2.getFxImage(),
            Sprite.player_dead3.getFxImage(),
    };
    private Sound bombSound = new Sound();
    private int bombNumber = 1;
    public static int bombPower = 1;
    private int speed = 1;

    private boolean isLife = true;

    public boolean isStillRender() {
        return stillRender;
    }

    public void setStillRender(boolean stillRender) {
        this.stillRender = stillRender;
    }

    public Keyboard kb = new Keyboard();
    private final List<Bomb> bombs = new ArrayList<>();

    public int getBombNumber() {
        return bombNumber;
    }

    public boolean isLife() {
        return isLife;
    }

    public void setLife(boolean life) {
        isLife = life;
    }

    public void setBombNumber(int bombNumber) {
        this.bombNumber = bombNumber;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getScreenX() {
        return screenX;
    }

    public void setScreenX(double screenX) {
        this.screenX = screenX;
    }

    public double getScreenY() {
        return screenY;
    }

    public void setScreenY(double screenY) {
        this.screenY = screenY;
    }

    //    public static int[] bomberNodes = {
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
//    };

    public Bomber(double x, double y, Image img) {
        super(x, y, img);

        screenX = Sprite.WIDTH / 2;
        screenY = Sprite.HEIGHT /2 - 2 * Sprite.SCALED_SIZE;

        solidArea = new Rectangle(x, y + 8, 24, 24);
        NodesNumber = 1;
    }

    public void updateMove() {
        xVec = 0;
        yVec = 0;
        if (kb.up) {
            yVec -= speed;
        }
        if (kb.down) {
            yVec += speed;
        }
        if (kb.left) {
            xVec -= speed;
        }
        if (kb.right) {
            xVec += speed;
        }
    }

    public void putBomb() {
        if (bombs.size() <= bombNumber) {
            double xpos = x / 32;
            double ypos = y / 32;
            xpos = Math.round(xpos);
            ypos = Math.round(ypos);
            bombs.add(new Bomb(xpos, ypos, Sprite.bomb.getFxImage(), bombPower));
            bombSound.playPlaceNewBomb();
        }
    }

    private void updateAction() {
        if (kb.plant_bomb) {
            this.putBomb();
            kb.plant_bomb = false;
        }
    }

    public void kbUpdate() {
        updateMove();
        updateAction();
    }

    @Override
    public void move() {
        if (collision) {
            xVec = 0;
            yVec = 0;
            collision = false;
            return;
        }
        x += xVec;
        y += yVec;
    }

    public void updateAnimation() {
        if(isLife) {

            if (frame % 10 == 0) {
                frameCount++;
                frameCount %= 3;
            }
            if (kb.up) {
                this.setImg(upAnimation[frameCount]);
            }
            if (kb.down) {
                this.setImg(downAnimation[frameCount]);
            }
            if (kb.left) {
                this.setImg(leftAnimation[frameCount]);
            }
            if (kb.right) {
                this.setImg(rightAnimation[frameCount]);
            }
        }
        else {
            animateDead++;

            if(animateDead % 20 == 0) {
                frameCount++;
            }
                frameCount %= 5;
            System.out.println(animateDead + " " + frameCount);
                this.setImg(deadAnimation[frameCount]);
                if(frameCount == 4) {
                    stillRender = false;

                }
            }

        }


    @Override
    public void update() {
        bombs.removeIf(Entity::isFlag);
        bombs.forEach(Bomb::update);
        if (isLife) {
            super.update();
            move();
            updateAnimation();
            xVec = 0;
            yVec = 0;
            solidArea.setX(x);
            solidArea.setY(y + 8);
        }
        else {
            updateAnimation();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        bombs.forEach(bomb -> bomb.render(gc));
//        screenX = x < Sprite.WIDTH / 2 ? x : Sprite.WIDTH;
//        screenY = y < Sprite.HEIGHT / 2 ? y : Sprite.HEIGHT;
        if ( x < Sprite.WIDTH / 2  || x < Sprite.HEIGHT / 2) {
                gc.drawImage(img, x, y);
        } else {
            gc.drawImage(img, screenX, screenY);
        }
    }
}