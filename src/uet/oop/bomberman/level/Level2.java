package uet.oop.bomberman.level;

import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.TaskBar;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.entities.creature.Creature.creatures;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.graphics.Menu.game;
import static uet.oop.bomberman.entities.creature.Bomber.bombNumber;
import static uet.oop.bomberman.entities.creature.Bomber.bombPower;
import static uet.oop.bomberman.level.Game.*;

public class Level2 {
    public Level2() {

        root.getChildren().add(canvas);
        TaskBar.createTaskBar(root);
        stillObjects.clear();
        miscellaneous.clear();
        creatures.clear();
        game = new Game();
        bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt");
        bomberman.setLife(true);
        bombNumber = 1;
        bombPower = 1;
        level_ = 2;
    }
}
