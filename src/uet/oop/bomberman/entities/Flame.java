package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.entities.creature.Creature.creatures;

public class Flame extends Entity implements IAnimation {
    private boolean head = false;
    protected int animate = 0;
    protected int frameCount = 0;

    public Flame(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public Flame() {
        x = 0;
        y = 0;
        img = null;
        this.flag = false;
    }

    public void setHead(boolean head) {
        this.head = head;
    }

    protected final Image[] flameMidVerticalStatus = {
            Sprite.explosion_vertical1.getFxImage(),
            Sprite.explosion_vertical2.getFxImage(),
            Sprite.explosion_vertical1.getFxImage(),
            Sprite.explosion_vertical.getFxImage(),
    };
    protected final Image[] flameMidHorizonStatus = {
            Sprite.explosion_horizontal1.getFxImage(),
            Sprite.explosion_horizontal2.getFxImage(),
            Sprite.explosion_horizontal1.getFxImage(),
            Sprite.explosion_horizontal.getFxImage(),
    };
    protected final Image[] flameHeadDownStatus = {
            Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last2.getFxImage(),
            Sprite.explosion_vertical_down_last1.getFxImage(),
            Sprite.explosion_vertical_down_last.getFxImage()
    };
    protected final Image[] flameHeadLeftStatus = {
            Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last2.getFxImage(),
            Sprite.explosion_horizontal_left_last1.getFxImage(),
            Sprite.explosion_horizontal_left_last.getFxImage(),
    };
    protected final Image[] flameHeadRightStatus = {
            Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last2.getFxImage(),
            Sprite.explosion_horizontal_right_last1.getFxImage(),
            Sprite.explosion_horizontal_right_last.getFxImage(),
    };
    protected final Image[] flameHeadUpStatus = {
            Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last2.getFxImage(),
            Sprite.explosion_vertical_top_last1.getFxImage(),
            Sprite.explosion_vertical_top_last.getFxImage()
    };


    @Override
    public void update() {
        if(collision(bomberman, this)) {
            bomberman.setLife(false);
        }
        creatures.forEach(creature -> {
            if(collision(this, creature)) {
//                System.out.println("Flame has kill this" + creature.getClass());
                creature.setLife(false);
            }
        });
    }

    @Override
    public long getCurrentFrame() {
        return 0;
    }

    public void updateLeftAnimation() {
        animate++;
        if (animate == 10) {
            frameCount++;
            animate = 0;
        }
        frameCount %= 4;
        if (!this.head) {
            this.setImg(flameMidHorizonStatus[frameCount]);
        } else {
            this.setImg(flameHeadLeftStatus[frameCount]);
        }
//            System.out.println(frameCount);
    }

    public void updateRightAnimation() {
        animate++;
        if (animate == 10) {
            frameCount++;
            animate = 0;
        }
        frameCount %= 4;
        if (!this.head) {
            this.setImg(flameMidHorizonStatus[frameCount]);
        } else {
            this.setImg(flameHeadRightStatus[frameCount]);
        }
//        System.out.println(frameCount);
    }

    public void updateUpAnimation() {
        animate++;
        if (animate == 10) {
            frameCount++;
            animate = 0;
        }
        frameCount %= 4;
        if (!this.head) {
            this.setImg(flameMidVerticalStatus[frameCount]);
        } else {
            this.setImg(flameHeadUpStatus[frameCount]);
        }
//        System.out.println(frameCount);
    }

    public void updateDownAnimation() {
        animate++;
        if (animate == 10) {
            frameCount++;
            animate = 0;
        }
        frameCount %= 4;
        if (!this.head) {
            this.setImg(flameMidVerticalStatus[frameCount]);
        } else {
            this.setImg(flameHeadDownStatus[frameCount]);
        }
//        System.out.println(frameCount);
    }

    @Override
    public void updateAnimation() {

    }
}
