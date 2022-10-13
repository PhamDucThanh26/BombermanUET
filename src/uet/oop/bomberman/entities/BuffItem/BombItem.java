package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class BombItem extends Entity {
    public BombItem() {
    }

    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(collision(bomberman, this)) {
            bomberman.setBombNumber(bomberman.getBombNumber() + 1);
            this.flag = true;
            System.out.println("Number of bomb : " + bomberman.getBombNumber());
        }

    }
}
