package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.Interaction.collision;


public class Bomb extends Entity implements IAnimation {
    private List<Flame> leftFlame = new ArrayList<>();
    private List<Flame> rightFlame = new ArrayList<>();
    private List<Flame> upFlame = new ArrayList<>();
    private List<Flame> downFlame = new ArrayList<>();
    public static int bombPower = 2;
    public boolean isExploded = false;
    private int frameCount = 0;
    private final long startTime = System.currentTimeMillis();

    static final Image[] activeBomb = {
            Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(),
    };
    static final Image[] bombExplode = {
            Sprite.bomb_exploded.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded2.getFxImage(),
    };

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

    @Override
    public void updateAnimation() {
        if(frame > 2 * FPS) {
            flag = true;
        } else if (frame > (int) (1.5 * FPS) ) {
            isExploded = true;
        }
        else if (frame % 20 == 0) {
            frameCount++;
            frameCount = frameCount % 2;
        }
        this.setImg(activeBomb[frameCount]);
    }


    public void ExplosionBomb() {
        if (frame > 150) {
            this.flag = true;
        } else {
            this.setImg(bombExplode[0]);
            this.isExploded = true;
            leftFlame.forEach(g -> g.checkFrame(this));
            upFlame.forEach(g -> g.checkFrame(this));
//            flameLeft.addFrameLeft(this);
            rightFlame.forEach(g -> g.checkFrame(this));
            downFlame.forEach(g -> g.checkFrame(this));
        }
    }

    int findLeft() {
        for(int i = 0; i < stillObjects.size(); i++) {
            if(!(stillObjects.get(i) instanceof Grass)) {
                for(int j = 0; j < leftFlame.size(); j++) {
                    if(collision(stillObjects.get(i), leftFlame.get(j))) {
                        return j + 1;
                    }
                }
            }
        }
        return leftFlame.size();
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
        frame = getCurrentFrame();
//        ExplosionBomb();
//        updateFlameLeft();
//        updateFlameRight();
//        updateFlameUp();
//        updateFlameDown();
        updateAnimation();
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 60 / 1000;
    }
}