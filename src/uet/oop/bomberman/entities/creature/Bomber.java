package uet.oop.bomberman.entities.creature;

import uet.oop.bomberman.audio.Sound;
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
    private int animateDead = 0;

    private boolean stillRender = true;
    final Image[] upAnimation = {
            Sprite.player_up.getFxImage(),
            Sprite.player_up_1.getFxImage(),
            Sprite.player_up_2.getFxImage()
    };
    private final Image[] downAnimation = {
            Sprite.player_down.getFxImage(),
            Sprite.player_down_1.getFxImage(),
            Sprite.player_down_2.getFxImage()
    };

    private final Image[] leftAnimation = {
            Sprite.player_left.getFxImage(),
            Sprite.player_left_1.getFxImage(),
            Sprite.player_left_2.getFxImage()
    };
    private final Image[] rightAnimation = {
            Sprite.player_right.getFxImage(),
            Sprite.player_right_1.getFxImage(),
            Sprite.player_right_2.getFxImage()
    };

    private final Image[] deadAnimation = {
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

    public Keyboard kb = new Keyboard();
    private final List<Bomb> bombs = new ArrayList<>();

    public int getBombNumber() {
        return bombNumber;
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

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        solidArea = new Rectangle(x, y + 8, 24, 24);
        NodesNumber = 1;
        center = true;
    }

    public void updateMove() {
        xVec = 0;
        yVec = 0;
        if (kb.keySet.contains("UP") || kb.keySet.contains("W")) {
            yVec -= speed;
        }
        if (kb.keySet.contains("DOWN") || kb.keySet.contains("S")) {
            yVec += speed;
        }
        if (kb.keySet.contains("LEFT") || kb.keySet.contains("A")) {
            xVec -= speed;
        }
        if (kb.keySet.contains("RIGHT") || kb.keySet.contains("D")) {
            xVec += speed;
        }
    }

    public void putBomb() {
        if (bombs.size() < bombNumber) {
            double xpos = x / 32;
            double ypos = y / 32;
            xpos = Math.round(xpos);
            ypos = Math.round(ypos);
            bombs.add(new Bomb(xpos, ypos, Sprite.bomb.getFxImage(), bombPower));
            bombSound.playPlaceNewBomb();
        }
    }

    private void updateAction() {
        if (kb.keySet.contains("SPACE")) {
            this.putBomb();
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
            if (kb.keySet.contains("UP") || kb.keySet.contains("W")) {
                this.setImg(upAnimation[frameCount]);
            }
            if (kb.keySet.contains("DOWN") || kb.keySet.contains("S")) {
                this.setImg(downAnimation[frameCount]);
            }
            if (kb.keySet.contains("LEFT") || kb.keySet.contains("A")) {
                this.setImg(leftAnimation[frameCount]);
            }
            if (kb.keySet.contains("RIGHT") || kb.keySet.contains("D")) {
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
            // Entity update
            super.update();

            // game update
            move();
//            if(x < Widt)

            // solid reset
            xVec = 0;
            yVec = 0;
            solidArea.setX(x);
            solidArea.setY(y + 8);

            updateAnimation();
        }
        else {
            updateAnimation();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        bombs.forEach(bomb -> bomb.render(gc));
        super.render(gc);
    }
}