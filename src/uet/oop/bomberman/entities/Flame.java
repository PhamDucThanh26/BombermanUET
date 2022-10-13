package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.IAnimation;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
//import static uet.oop.bomberman.entities.creature.Bomber.bomb;

public class Flame extends Entity implements IAnimation {



    private boolean head = false;
    public Flame() {
        x = 0;
        y = 0;
        img = null;
        this.flag = false;
    }

    public boolean isHead() {
        return head;
    }

    public void setHead(boolean head) {
        this.head = head;
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
