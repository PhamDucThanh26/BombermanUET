package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class BombItem extends Item {
    public BombItem() {
    }

    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (collision(bomberman, this)) {
            bomberman.setBombNumber(bomberman.getBombNumber() + 1);
            super.update();
//            System.out.println("Number of bomb : " + bomberman.getBombNumber());
        }
    }
}
