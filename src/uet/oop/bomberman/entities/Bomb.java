package uet.oop.bomberman.entities;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Flame.isActive;

public class Bomb extends Creature {


    private Flame flameLeft = new Flame();

    private List<Flame> leftFlame = new ArrayList<>();

    public List<Flame> getRightFlame() {
        return rightFlame;
    }

    public List<Flame> getUpFlame() {
        return upFlame;
    }

    public List<Flame> getDownFlame() {
        return downFlame;
    }

    private List<Flame> rightFlame = new ArrayList<>();
    private List<Flame> upFlame = new ArrayList<>();
    private List<Flame> downFlame = new ArrayList<>();
    public static int bombPower = 2;
    private Flame flameRight = new Flame();
    private Flame flameUp = new Flame();
    private Flame flameDown = new Flame();
    public boolean isExploded = false;
    private int frameCount = 0;

    public boolean hasBomb = true;

    static final Image[] activeBom = {
            Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(),
    };
    static final Image[] bombExplode = {
            Sprite.bomb_exploded.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded2.getFxImage(),
    };

    public List<Flame> getLeftFlame() {
        return leftFlame;
    }

    public Flame getFlameLeft() {
        return flameLeft;
    }

    public void setFlameLeft(Flame flameLeft) {
        this.flameLeft = flameLeft;
    }

    public Flame getFlameRight() {
        return flameRight;
    }

    public void setFlameRight(Flame flameRight) {
        this.flameRight = flameRight;
    }

    public Flame getFlameUp() {
        return flameUp;
    }

    public void setFlameUp(Flame flameUp) {
        this.flameUp = flameUp;
    }

    public Flame getFlameDown() {
        return flameDown;
    }

    public void setFlameDown(Flame flameDown) {
        this.flameDown = flameDown;
    }

    private int lifeSpan = 0;
    public static int isBomb = 0;

    public Bomb(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        for (int i = 0; i < bombPower - 1; i++) {
            leftFlame.add(new Flame(xUnit - 1 - i, yUnit, Sprite.explosion_horizontal.getFxImage()));
            rightFlame.add(new Flame(xUnit + 1 + i, yUnit, Sprite.explosion_horizontal.getFxImage()));
            upFlame.add(new Flame(xUnit, yUnit - i - 1, Sprite.explosion_vertical.getFxImage()));
            downFlame.add(new Flame(xUnit, yUnit + 1 + i, Sprite.explosion_vertical.getFxImage()));
        }
        leftFlame.add(new Flame((xUnit - bombPower), yUnit, Sprite.explosion_horizontal_left_last.getFxImage()));
        rightFlame.add(new Flame((xUnit + bombPower), yUnit, Sprite.explosion_horizontal_right_last.getFxImage()));
        upFlame.add(new Flame(xUnit, yUnit - bombPower, Sprite.explosion_vertical_top_last.getFxImage()));
        downFlame.add(new Flame(xUnit, yUnit + bombPower, Sprite.explosion_vertical_down_last.getFxImage()));
    }

    private void updateActiveAnimation() {
        long now = System.currentTimeMillis();

        if ((startTime - now) % 200 == 0) {
            frameCount++;
            frameCount = frameCount % 2;
        }
        if (lifeSpan == 20) {
            isExploded = true;
            ExplosionBomb();
        }
        if (lifeSpan == 30) {
            flag = true;

        }
    }

    public void activeBomb() {
        this.setImg(activeBom[frameCount]);
    }

    public void ExplosionBomb() {
        long liveTime = System.currentTimeMillis() - startTime;
        if (liveTime > 2500) {
            this.flag = true;
        } else if (liveTime > 2000) {
            this.setImg(bombExplode[0]);
            this.isExploded = true;

            leftFlame.forEach((Flame g) -> {
                g.checkFrame(this);
            });
            upFlame.forEach((Flame g) -> {
                g.checkFrame(this);
            });
//            flameLeft.addFrameLeft(this);
            rightFlame.forEach((Flame g) -> {
                g.checkFrame(this);
            });
            downFlame.forEach((Flame g) -> {
                g.checkFrame(this);
            });
        }
    }

    int findLeft() {
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < stillObjects.size(); i++) {
            if(this.getY() == stillObjects.get(i).getY()
                    && (stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick)) {

                int k = this.getX() - stillObjects.get(i).getX();

                if( k > 0 && k < result) {
                    result = k;
                }
            }
        }
        return result / 32;
    }

    private int findRight() {
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < stillObjects.size(); i++) {
            if(this.getY() == stillObjects.get(i).getY()
                    && (stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick)) {
                int k = this.getX() - stillObjects.get(i).getX();
                if( k < 0 && k > result) {
                    result = k;
                }
            }
        }
        return -result / 32;
    }
    private int findUp() {
        int result = Integer.MAX_VALUE;
        for(int i = 0; i < stillObjects.size(); i++) {
            if(this.getX() == stillObjects.get(i).getX()
                    && (stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick)) {
                int k = this.getY() - stillObjects.get(i).getY();
                if( k > 0 && k < result) {
                    result = k;
                }
            }
        }
        return result / 32;
    }

    private int findDown() {
        int result = Integer.MIN_VALUE;
        for(int i = 0; i < stillObjects.size(); i++) {
            if(this.getX() == stillObjects.get(i).getX()
                    && (stillObjects.get(i) instanceof Wall || stillObjects.get(i) instanceof Brick)) {
                int k = this.getY() - stillObjects.get(i).getY();
                if( k < 0 && k > result) {
                    result = k;
                }
            }
        }
        return -result / 32;
    }

    public void updateFlameLeft() {
        int k = findLeft();
        if(k > bombPower) return;
        for(int i = k - 1; i < leftFlame.size(); i++) {
            leftFlame.remove(i);
            i--;
        }
    }

    public void updateFlameRight() {
        int k = findRight();
        if(k > bombPower) return;
        for(int i = k - 1; i < rightFlame.size(); i++) {
            rightFlame.remove(i);
            i--;
        }
    }

    public void updateFlameUp() {
        int k = findUp();
        if(k > bombPower) return;
        for(int i = k - 1; i < upFlame.size(); i++) {
            upFlame.remove(i);
            i--;
        }
    }
    public void updateFlameDown() {
        int k = findDown();
        if(k > bombPower) return;
        for(int i = k - 1; i < downFlame.size(); i++) {
            downFlame.remove(i);
            i--;
        }
    }


    @Override
    public void update() {
        updateActiveAnimation();
        activeBomb();
        ExplosionBomb();
        updateFlameLeft();
        updateFlameRight();
        updateFlameUp();
        updateFlameDown();



    }


}