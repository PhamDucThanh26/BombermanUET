package uet.oop.bomberman.entities.BuffItem;

import uet.oop.bomberman.sound_effect.SFX;
import uet.oop.bomberman.sound_effect.Sound;
import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.IGameEntity;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.entities.Interaction.collision;

public class Item extends Entity implements IGameEntity {

    protected SFX itemSFX = new SFX();
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
    public void update() {
        if (collision(bomberman, this)) {
            this.flag = true;
            itemSFX.playSFX(Sound.item);
        }
    }
}
