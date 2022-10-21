package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class Doll extends Creature {
    public Doll(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    protected void move() {

    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void updateAnimation() {

    }
}
