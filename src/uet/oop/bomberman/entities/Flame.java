package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.BombermanGame.entities;

public class Flame extends Entity {
    public Flame() {
        x = 0;
        y = 0;
        img = null;

    }

    public Flame(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public void addFrameLeft(Bomb bomb) {

        int x = bomb.getX() / 32 - 1;
        int y = bomb.getY() / 32 ;
        this.setImg(Sprite.explosion_horizontal_left_last.getFxImage());
        System.out.println(x + " " + y);
        this.setX(x * 32);
        this.setY(y * 32);
        entities.add(this);
        if(bomb.flag == true) this.flag = true;
    }

    public void addFrameRight(Bomb bomb) {

        int x = bomb.getX() / 32 + 1;
        int y = bomb.getY() / 32 ;
        this.setImg(Sprite.explosion_horizontal_right_last.getFxImage());
        System.out.println(x + " " + y);
        this.setX(x * 32);
        this.setY(y * 32);
        entities.add(this);
        if(bomb.flag == true) this.flag = true;
    }
    public void addFrameUp(Bomb bomb) {

        int x = bomb.getX() / 32;
        int y = bomb.getY() / 32 - 1 ;
        this.setImg(Sprite.explosion_vertical_top_last.getFxImage());
        System.out.println(x + " " + y);
        this.setX(x * 32);
        this.setY(y * 32);
        entities.add(this);
        if(bomb.flag == true) this.flag = true;
    }

    public void addFrameDown(Bomb bomb) {
        int x = bomb.getX() / 32;
        int y = bomb.getY() / 32 + 1 ;
        this.setImg(Sprite.explosion_vertical_down_last.getFxImage());
        System.out.println(x + " " + y);
        this.setX(x * 32);
        this.setY(y * 32);
        entities.add(this);
        if(bomb.flag == true) this.flag = true;

    }
    @Override
    public void update() {

    }
}
