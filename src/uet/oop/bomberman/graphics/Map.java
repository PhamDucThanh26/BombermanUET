package uet.oop.bomberman.graphics;

import SoundEffect.Sound;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.BuffItem.BombItem;
import uet.oop.bomberman.entities.BuffItem.FlameItem;
import uet.oop.bomberman.entities.BuffItem.Item;
import uet.oop.bomberman.entities.BuffItem.SpeedItem;
import uet.oop.bomberman.entities.creature.Balloom;
import uet.oop.bomberman.entities.creature.Creature;
import uet.oop.bomberman.entities.creature.Doll;
import uet.oop.bomberman.entities.creature.Oneal;

import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    public static int[][] mapNodes = new int[Sprite.maxWorldCol][Sprite.maxWorldCol];

    public Map() {
    }

    public static void createMap(String path) {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            int j = 0;
            level = sc.nextInt();
            heightMap = sc.nextInt();
            widthMap = sc.nextInt();
            sc.nextLine();
            while (sc.hasNextLine() && j < heightMap) {
                String s = sc.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    backgroundTitle.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    Entity object;
                    if (s.charAt(i) == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    } else if (s.charAt(i) == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (s.charAt(i) == 'x') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (s.charAt(i) == '1') {
                        object = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    } else if (s.charAt(i) == '2') {
                        object = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                    } else if (s.charAt(i) == 'b') {
                        stillObjects.add(new Brick(i, j, Sprite.brick.getFxImage()));
                        object = new BombItem(i, j, Sprite.powerup_bombs.getFxImage());
                    } else if (s.charAt(i) == 'f') {
                        stillObjects.add(new Brick(i, j, Sprite.brick.getFxImage()));
                        object = new FlameItem(i, j, Sprite.powerup_flames.getFxImage());
                    } else if (s.charAt(i) == 's') {
                        stillObjects.add(new Brick(i, j, Sprite.brick.getFxImage()));
                        object = new SpeedItem(i, j, Sprite.powerup_speed.getFxImage());
                    } else if (s.charAt(i) == '3') {
                        object = new Doll(i, j, Sprite.doll_left1.getFxImage());

                    } else {
                        continue;
                    }
                    if (object instanceof Brick || object instanceof Wall) {
                        stillObjects.add(object);
                    } else if (object instanceof Item) {
                        ((Item) object).addStage();
                    } else ((Creature) object).addStage();
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("can not find file");
            e.printStackTrace();
            System.exit(1);
        }
    }


}