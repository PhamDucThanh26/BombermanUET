package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.user_input.Keyboard;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    public static final List<Entity> block = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();

    private List<Entity> stillObjects = new ArrayList<>();
    public static char [][] idObjects;

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setTitle("Bomberman");

        //Passing FileInputStream object as a parameter
        Image img = new Image("file:res//icon.png");

        stage.getIcons().add(img);

        stage.setResizable(true);

        Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);

        //handle movement
        Keyboard keyboard = new Keyboard();

        scene.setOnKeyPressed((e) -> {
            keyboard.hold(e);
        });
        scene.setOnKeyReleased((e) -> {
            keyboard.release(e);
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                bomberman.updateMove(keyboard);
                render();
                update();
            }
        };
        timer.start();

        createMap("C:\\Code\\Java\\bomberman-starter\\BombermanUET\\res\\levels\\Level0.txt");

        stage.setScene(scene);
        stage.show();
    }

    public void createMap(String path) {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            int j = 0;
            while(sc.hasNextLine()) {
                String s = sc.nextLine();
                System.out.println(s.length());

                for(int i = 0; i < s.length(); i++) {
                    Entity object;
                    if(s.charAt(i) == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage() );
                    }
                    else if(s.charAt(i) == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    stillObjects.add(object);
                }
                j++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        /*for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Entity object;
                if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1|| i == j) {
                    object = new Wall(i, j, Sprite.wall.getFxImage());
                }

                else {
                    object = new Grass(i, j, Sprite.grass.getFxImage());
                }
                stillObjects.add(object);
            }
        }*/
    }

    public void update() {
        entities.forEach(Entity::update);
        block.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }
}
