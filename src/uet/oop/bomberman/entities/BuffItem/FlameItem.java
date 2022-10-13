package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.BuffItem.Item;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Bomb.bombPower;
import static uet.oop.bomberman.entities.Interaction.collision;

public class FlameItem extends Item {
    public FlameItem() {
    }

    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(collision(bomberman, this)) {
            bombPower++;
            System.out.println("Bomb Power is " + bombPower);
            this.flag = true;
        }

    }
}
