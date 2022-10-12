package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

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
            bombPower += 1;
            this.flag = true;
        }

    }
}
