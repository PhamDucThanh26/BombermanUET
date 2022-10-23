package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class FlameItem extends Item {
    public FlameItem() {
    }

    public FlameItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (collision(bomberman, this)) {
            super.update();
            bomberman.setLife(false);
        }

    }
}
