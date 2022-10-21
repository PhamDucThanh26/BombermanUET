package Level;

import javafx.scene.Group;

import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.BombermanGame.level;
public class Level1 {
    public Level1(Group root) {
        level = 1;
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt");

    }
}
