package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class Item extends Entity {
    public Item() {
    }

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }


    @Override
    public void update() {
        if(collision(bomberman, this)) {
            this.flag = true;
        }
    }
}
