package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Wall extends Entity {

    public Wall(int x, int y, Image img) {
        super(x, y, img);
        maskNumber = 1;
    }

    @Override
    public void update() {

    }
}
