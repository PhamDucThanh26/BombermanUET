package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class BombItem extends Item {
    public BombItem() {
    }

    public BombItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(collision(bomberman, this)) {
            bomberman.bombNumber++;
            this.flag = true;
        }
        System.out.println(bomberman.bombNumber);
    }
}
