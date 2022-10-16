package uet.oop.bomberman.entities.BuffItem;

import SoundEffect.Sound;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.IGameEntity;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class Item extends Entity implements IGameEntity {

    protected Sound itemSound = new Sound();
    public static List<Item> miscellaneous = new ArrayList<>();

    public Item() {
    }

    public Item(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public void addStage() {
        miscellaneous.add(this);
    }

    @Override
    public void clearStage() {
        miscellaneous.clear();
    }

    @Override
    public void update() {
        if (collision(bomberman, this)) {
            this.flag = true;
            itemSound.playGetNewItem();
        }
    }
}
