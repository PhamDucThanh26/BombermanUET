package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
//import static uet.oop.bomberman.entities.Bomber.bomb;

public class Flame extends Creature {

    public Flame() {
        x = 0;
        y = 0;
        img = null;
        this.flag = false;
    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public boolean collide (Entity e) {
        if(e instanceof Brick && ((Brick)e).getBoundary().intersects(this.getBoundary())) {
            ((Brick) e).destroy();
            return true;
        }

        return false;
    }

    @Override
    public void update() {
        for(int i = 0; i < stillObjects.size(); i++) {
            collide(stillObjects.get(i));
        }
    }

    @Override
    public void updateAnimation() {

    }
}
