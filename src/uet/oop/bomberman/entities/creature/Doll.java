package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

public class Doll extends Creature {
    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    final Image[] deadAnimation = {
            Sprite.doll_dead.getFxImage(),
            Sprite.doll_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };

    @Override
    protected void move() {

    }

    @Override
    public void update() {
        if (isLife) {
            super.update();
            updateAnimation();
        } else {
            dead();
        }
    }

    @Override
    public void updateAnimation() {

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
}
