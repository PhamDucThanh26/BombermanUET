package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.user_input.Keyboard;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;

    public static final List<Entity> block = new ArrayList<>();

    private GraphicsContext gc;
    private Canvas canvas;
    public static List<Entity> entities = new ArrayList<>();
    Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    private List<Entity> stillObjects = new ArrayList<>();
    public static char [][] idObjects;

    //handle movement
    Keyboard keyboard = new Keyboard();
    @Override
    public void start(Stage stage) {
        music();
        // Tao Canvas
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);
        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level1.txt");
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setTitle("Bomberman");

        //Passing FileInputStream object as a parameter
        Image img = new Image("file:res//icon.png");

        stage.getIcons().add(img);

        stage.setResizable(true);

        scene.setOnKeyPressed(e -> keyboard.hold(e));
        scene.setOnKeyReleased(e -> keyboard.release(e));

        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                render();
            }
        };
        timer.start();

        stage.setScene(scene);
        stage.show();
    }

    public void createMap(String path) {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            int j = 0;

            sc.nextLine();

            while(sc.hasNextLine() && j < 13) {
                String s = sc.nextLine();

                for(int i = 0; i < s.length(); i++) {
                    Entity object;
                    if(s.charAt(i) == '#') {
                        object = new Wall(i, j, Sprite.wall.getFxImage() );
                    }
                    else if(s.charAt(i) == '*') {
                        object = new Brick(i, j, Sprite.brick.getFxImage());
                    }
                    else if(s.charAt(i) == 'x') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Portal(i, j, Sprite.portal.getFxImage());
                    }
                    else if(s.charAt(i) == '1') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                    }
                    else if(s.charAt(i) == '2') {
                        stillObjects.add(new Grass(i, j, Sprite.grass.getFxImage()));
                        object = new Ballon(i, j, Sprite.balloom_left1.getFxImage());
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
            System.exit(1);
        }
    }

    public void update() {
        bomberman.update(keyboard);
        entities.forEach(Entity::update);
        stillObjects.forEach( (Entity e) -> {
            if ( !(e instanceof Grass) && bomberman.intersects(e)) {
                bomberman.setCollision(true);
                System.out.println("bomberman hit");
            }
        });

        bomberman.update();
        stillObjects.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        bomberman.render(gc);
    }

    MediaPlayer mediaPlayer;
    public void music() {
        File path = new File(System.getProperty("user.dir") + "\\res\\audio\\Nokia-ringtone-arabic.mp3");
        Media h = new Media(path.toURI().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.play();

    }
}
