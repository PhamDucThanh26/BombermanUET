package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.level.Game.*;

public class Level2 {
    public Level2() {
        Game.reset();
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt");
        bomberman.setLife(true);
        bomberman.bombNumber = 1;
        bomberman.bombPower = 1;
        level_ = 2;
    }
}
