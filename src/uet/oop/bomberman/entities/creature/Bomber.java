package uet.oop.bomberman.entities.creature;

import uet.oop.bomberman.sound_effect.SFX;
import uet.oop.bomberman.sound_effect.Sound;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Bomb;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.image.Image;
import uet.oop.bomberman.user_input.Keyboard;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.Interaction.collision;

public final class Bomber extends Creature {
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

    // sfx
    private final SFX bomberSFX = new SFX();

    // control
    public Keyboard kb = new Keyboard();

    // ability
    private final List<Bomb> bombs = new ArrayList<>();

    public List<Bomb> getBombs() {
        return bombs;
    }

    // stat
    public int bombNumber = 1;
    public int bombPower = 1;
    public int speed = 1;
    private boolean onceTime = true;
    private int animateDead = 0;

    public boolean isStillRender() {
        return stillRender;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Bomber(double x, double y, Image img) {
        super(x, y, img);
        solidArea = new Rectangle(x + 2, y + 8, 20, 24);
        nodeNumber = 2;
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
            double xPos = x / 32;
            double yPos = y / 32;
            xPos = Math.round(xPos);
            yPos = Math.round(yPos);
            bombs.add(new Bomb(xPos, yPos, Sprite.bomb.getFxImage(), bombPower));
            bomberSFX.playSFX(Sound.newBomb);
        }
    }

    private void updateAction() {
        if (kb.keySet.contains("SPACE")) {
            this.putBomb();
            kb.keySet.remove("SPACE");
        }
    }

    public void kbUpdate() {
        updateMove();
        updateAction();
    }

    @Override
    public void move() {
        bombs.forEach(bomb -> {
            if(bomb.isBomberOut() && collision(this, bomb)) {
                collision = true;
            }
        });
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
        if (isLife) {
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
        } else {
            dead();
        }
    }


    @Override
    public void update() {
        bombs.removeIf(Entity::isFlag);
        bombs.forEach(Bomb::update);
        if (isLife) {
            frame = getCurrentFrame();
            // game update
            move();
            // reset movement and solid
            xVec = 0;
            yVec = 0;
            solidArea.setX(x + 2);
            solidArea.setY(y + 8);
            updateAnimation();
        } else {
            updateAnimation();
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        bombs.forEach(bomb -> bomb.render(gc));
        super.render(gc);
    }

    @Override
    public void dead() {
        isLife = false;
        animateDead++;
        if (animateDead % 20 == 0) {
            frameCount++;
        }
        frameCount %= 5;
//
        this.setImg(deadAnimation[frameCount]);
        if (frameCount == 4) {
            if (onceTime) {
                bomberSFX.playSFX(Sound.bomberDie);
                onceTime = false;
            }
            stillRender = false;
        }
    }
}