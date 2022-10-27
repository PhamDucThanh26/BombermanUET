package uet.oop.bomberman.entities.BuffItem;

import javafx.scene.image.Image;

import static uet.oop.bomberman.Game.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class SpeedItem extends Item {
    public SpeedItem() {
    }

    public SpeedItem(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if (collision(bomberman, this)) {
            super.update();
            bomberman.setSpeed(bomberman.getSpeed() * 2);
//            System.out.println("Your speed is: " + bomberman.getSpeed());
        }
    }
}
