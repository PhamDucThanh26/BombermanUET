package uet.oop.bomberman.entities;

import uet.oop.bomberman.sound_effect.SFX;
import uet.oop.bomberman.sound_effect.Sound;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.level.Game.stillObjects;
import static uet.oop.bomberman.entities.Interaction.collision;

public class Bomb extends Entity implements IAnimation {
    public boolean isExploded = false;

    public boolean _allowedToPassThroght = true;
    protected boolean isAllowedToPassThroght() {
        return _allowedToPassThroght;
    }


    int animate = 0;
    private int frameCountEx = 0;
    private int frameCount = 0;
    private final long startTime = System.currentTimeMillis();
    protected SFX bombSFX = new SFX();
    private final List<Flame> leftFlame = new ArrayList<>();
    private final List<Flame> rightFlame = new ArrayList<>();
    private final List<Flame> upFlame = new ArrayList<>();
    private final List<Flame> downFlame = new ArrayList<>();
    static final Image[] activeBomb = {
            Sprite.bomb.getFxImage(),
            Sprite.bomb_1.getFxImage(),
            Sprite.bomb_2.getFxImage(),
            Sprite.bomb_1.getFxImage(),
    };

    final Image[] bombExplode = {
            Sprite.bomb_exploded2.getFxImage(),
            Sprite.bomb_exploded2.getFxImage(),
            Sprite.bomb_exploded1.getFxImage(),
            Sprite.bomb_exploded.getFxImage(),
    };

    public Bomb(double xUnit, double yUnit, Image img, int bombPower) {
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

        leftFlame.get(leftFlame.size() - 1).setHead(true);
        rightFlame.get(leftFlame.size() - 1).setHead(true);
        upFlame.get(leftFlame.size() - 1).setHead(true);
        downFlame.get(leftFlame.size() - 1).setHead(true);
    }

    @Override
    public void updateAnimation() {
        if (frame > 3 * FPS) {
            flag = true;
        } else if (frame == (int) (2.5 * FPS)) {
            isExploded = true;
            bombSFX.playSFX(Sound.bombExplosion);
        } else if ((frame + 1) % 20 == 0) {
            frameCount++;
            frameCount %= 4;
        }
        if (isExploded) {
            animate++;
            if (animate == 10) {
                frameCountEx++;
                animate = 0;
            }
            frameCountEx %= 4;
            img = bombExplode[frameCountEx];
        } else {
            img = activeBomb[frameCount];
        }
    }

    public void updateFlameList(List<Flame> flameList) {
        int rmvIdx = flameList.size();
        boolean hit = false;
        for (int i = 0; i < flameList.size(); i++) {
            for (Entity stillObject : stillObjects) {
                if (collision(flameList.get(i), stillObject)
                        && (stillObject instanceof Brick || stillObject instanceof Wall)) {
                    rmvIdx = i;
                    if (stillObject instanceof Brick) {
                        ((Brick) stillObject).setExploded(true);
                    }
                    hit = true;
                    break;
                }
            }
            if (hit) {
                break;
            }
        }
        while (rmvIdx < flameList.size()) {
            flameList.remove(rmvIdx);
        }
    }

    @Override
    public void update() {
        frame = getCurrentFrame();
        updateAnimation();

        if (isExploded) {
            updateFlameList(leftFlame);
            leftFlame.forEach(flame -> {
                flame.updateLeftAnimation();
                flame.update();
            });
            updateFlameList(rightFlame);
            rightFlame.forEach(flame -> {
                flame.updateRightAnimation();
                flame.update();
            });
            updateFlameList(upFlame);
            upFlame.forEach(flame -> {
                flame.updateUpAnimation();
                flame.update();
            });
            updateFlameList(downFlame);
            downFlame.forEach(flame -> {
                flame.updateDownAnimation();
                flame.update();
            });
            if (collision(bomberman, this)) {
                bomberman.setLife(false);
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {
        super.render(gc);
        if (isExploded) {
            leftFlame.forEach(fl -> fl.render(gc));
            rightFlame.forEach(fl -> fl.render(gc));
            upFlame.forEach(fl -> fl.render(gc));
            downFlame.forEach(fl -> fl.render(gc));
        }
    }

    @Override
    public long getCurrentFrame() {
        return (System.currentTimeMillis() - startTime) * 60 / 1000;
    }
}