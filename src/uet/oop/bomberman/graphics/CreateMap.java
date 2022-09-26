package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;

import static uet.oop.bomberman.BombermanGame.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CreateMap {
    public CreateMap(){}
    public void createMap (String level) {
        System.out.print(System.getProperty("user.dir"));
        final File fileName = new File(level);
        try(FileReader inputFile = new FileReader(fileName)) {
            Scanner sc = new Scanner(inputFile);
            String line = sc.nextLine();
            StringTokenizer tokens = new StringTokenizer(line);
            //_level = Integer.parseInt(tokens.nextToken());
            //_heightMap = Integer.parseInt(tokens.nextToken());
            //_widthMap = Integer.parseInt(tokens.nextToken());
            while(sc.hasNextLine()) {
                idObjects = new char[WIDTH][HEIGHT];
                //listkill = new int[_widthMap][_heightMap]
                for(int i = 0; i < HEIGHT; i++) {
                    String lineTile = sc.nextLine();
                    StringTokenizer tokenLine = new StringTokenizer(lineTile);

                    for(int j = 0; j < WIDTH; j++) {
                        char c = tokenLine.nextToken().charAt(0);
                        Entity entity = null;
                        switch (c) {
                            case '#':
                                entity = new Wall(j, i, Sprite.wall.getFxImage());
                                break;
                            case '*':
                                entity = new Brick(j, i, Sprite.brick.getFxImage());
                                break;
                            case 'x':
                                //entity = new Portal(j, i, Sprite.portal.getFxImage())
                                break;
                            default:
                                entity = new Grass(j, i, Sprite.grass.getFxImage());

                        }
                        idObjects[j][i] = c;
                        block.add(entity);
                    }
                }
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
