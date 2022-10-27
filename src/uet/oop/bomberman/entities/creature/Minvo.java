package uet.oop.bomberman.entities.creature;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Game.stillObjects;
import static uet.oop.bomberman.graphics.Map.mapNodes;

public class Minvo extends Creature {
    int buildCD = FPS;
    int shieldCD = FPS;
    boolean shield = false;

    public Minvo(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        SCORE = 200;
        solidArea = new Rectangle(x + 1, y + 1, 24, 24);
        buildWall();
    }

    @Override
    public void setCollision(boolean collision) {
        if (shield) return;
        super.setCollision(collision);
    }

    @Override
    public void setLife(boolean life) {
        if (shield) return;
        super.setLife(life);
    }

    private final Image[] deadAnimation = {
            Sprite.minvo_dead.getFxImage(),
            Sprite.minvo_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

    private final Image[] moveAnimation = {
            Sprite.minvo_left1.getFxImage(),
            Sprite.minvo_right1.getFxImage(),
            Sprite.minvo_left2.getFxImage(),
            Sprite.minvo_right2.getFxImage(),
            Sprite.minvo_left3.getFxImage(),
            Sprite.minvo_right3.getFxImage(),
    };


    @Override
    protected void move() {
        xVec = 0;
        yVec = 0;
    }

    protected void buildWall() {
        // get position where there is no walls
        int positionX = (int) solidArea.getX() / Sprite.SCALED_SIZE;
        int positionY = (int) solidArea.getY() / Sprite.SCALED_SIZE;

        for (int i = -1; i < 2; i++) {
            if (mapNodes[positionX + i][positionY + 2] == 0) {
                stillObjects.add(new Brick(i + positionX, 2 + positionY, Sprite.brick.getFxImage()));
                return;
            }

            if (mapNodes[positionX + i][positionY - 2] == 0) {
                stillObjects.add(new Brick(i + positionX, positionY - 2, Sprite.brick.getFxImage()));
                return;
            }
        }

        for (int i = -1; i < 2; i++) {
            if (mapNodes[positionX + 2][positionY + i] == 0) {
                stillObjects.add(new Brick(2 + positionX, i + positionY, Sprite.brick.getFxImage()));
                return;
            }

            if (mapNodes[positionX - 2][positionY + i] == 0) {
                stillObjects.add(new Brick(positionX - 2, positionY + i, Sprite.brick.getFxImage()));
                return;
            }
        }
    }

    void shieldDelay() {
        if (--shieldCD < 0) {
            shieldCD = 2 * FPS;
            shield = false;
        } else if (shieldCD < FPS) {
            shield = true;
        }
    }

    void buildDelay() {
        if (--buildCD < 0) {
            buildCD = 2 * FPS;
            buildWall();
        }
    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            buildDelay();
            shieldDelay();
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

    @Override
    public void render(GraphicsContext gc) {
        if (shield && getCurrentFrame() % 2 == 0) {
            super.render(gc);
        } else if (!shield) {
            super.render(gc);
        }
    }
}