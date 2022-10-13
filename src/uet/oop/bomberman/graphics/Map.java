package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.*;

import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    public static int[][] mapMask = new int[WIDTH][HEIGHT];
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
            while (sc.hasNextLine() && j < heightMap ) {
                String s = sc.nextLine();
                for (int i = 0; i < s.length(); i++) {
                    stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                    Entity object;
                    if (s.charAt(i) == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    } else if (s.charAt(i) == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (s.charAt(i) == 'x') {
                        stillObjects.add(new Portal(i,j,Sprite.portal.getFxImage()));
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else if (s.charAt(i) == '1') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    } else if (s.charAt(i) == '2') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                    }
                    else if (s.charAt(i) == 'b') {
                        stillObjects.add(new BombItem(i, j, Sprite.powerup_bombs.getFxImage()));
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    }
                    else if (s.charAt(i) == 'f') {
                        stillObjects.add(new FlameItem(i, j, Sprite.powerup_flames.getFxImage()));
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    } else {
                        continue;
                    }
                    if ( object instanceof Brick || object instanceof Wall)
                        stillObjects.add(object);
                    else entities.add(object);
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