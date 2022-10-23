package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.level.Game.*;

public class Level1 {

    public static final String[] Level = {
            "\\res\\levels\\Level0.txt",
            "\\res\\levels\\Level1.txt",
            "\\res\\levels\\Level2.txt",
    };
    public Level1() {
        root.getChildren().add(canvas);
        stillObjects.clear();
        miscellaneous.clear();
        creatures.clear();
        bomberman = new Bomber(2,2 , Sprite.player_right.getFxImage());
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level0.txt");
        bomberman.setLife(true);
        bomberman.bombNumber = 1;
        bomberman.bombPower = 1;
        level_ = 1;

    }
}
