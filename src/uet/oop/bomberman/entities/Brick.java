package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.block;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
    }

    private void checkHidden() {
        for(Entity entity: block) {
        }
    }

    @Override
    public void update() {
        checkHidden();
    }
}

