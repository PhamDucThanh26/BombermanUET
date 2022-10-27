package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.Game.bomberman;

public class Portal extends Entity {

    public static boolean isPortal = false;

    public Portal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void update() {
        if(collision(this, bomberman)) {
            collision =  true;
        }
    }
}
