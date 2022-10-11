package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;

public class Flame extends Entity {

    public static boolean isActive = false;

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Flame() {
        x = 0;
        y = 0;
        img = null;
        this.flag = false;
    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }
    public void checkFrame(Bomb bomb) {
        if(bomb.flag == true) this.flag = true;
    }








    @Override
    public void update() {


    }
}
