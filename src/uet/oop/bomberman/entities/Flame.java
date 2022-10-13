package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;

public class Flame extends Entity implements IAnimation {
    public Flame() {
        x = 0;
        y = 0;
        img = null;
        this.flag = false;
    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {

    }

    @Override
    public long getCurrentFrame() {
        return 0;
    }

    @Override
    public void updateAnimation() {

    }
}
