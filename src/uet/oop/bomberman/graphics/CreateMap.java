package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.*;

import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateMap {
    public CreateMap() {
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
            objectStill = new int[widthMap][heightMap];
            while (sc.hasNextLine() && j < heightMap) {
                String s = sc.nextLine();
                System.out.println(s);
                for (int i = 0; i < s.length(); i++) {

                    Entity object;
                    if (s.charAt(i) == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                        objectStill[i][j] = 1;
                    } else if (s.charAt(i) == '*') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                        objectStill[i][j] = 2;
                    } else if (s.charAt(i) == 'x') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Portal(i, j, Sprite.brick.getFxImage());
                        objectStill[i][j] = 3;
                    } else if (s.charAt(i) == '1') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        objectStill[i][j] = 4;
                    } else if (s.charAt(i) == '2') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Balloom(i, j, Sprite.balloom_left1.getFxImage());
                        objectStill[i][j] = 5;
                    } else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                        objectStill[i][j] = 0;
                    }
                    if (object instanceof Grass || object instanceof Brick || object instanceof Wall)
                        stillObjects.add(object);
                    else entities.add(object);
                }
                j++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
