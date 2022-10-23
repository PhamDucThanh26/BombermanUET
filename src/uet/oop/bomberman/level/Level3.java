package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.canvas;
import static uet.oop.bomberman.BombermanGame.root;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.level.Game.*;

public class Level3 {
    public Level3() {
        root.getChildren().add(canvas);
        stillObjects.clear();
        miscellaneous.clear();
        creatures.clear();
        bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level1.txt");
        bomberman.setLife(true);
        bomberman.bombPower = 1;
        bomberman.bombNumber = 1;
        level_ = 3;
    }
}
