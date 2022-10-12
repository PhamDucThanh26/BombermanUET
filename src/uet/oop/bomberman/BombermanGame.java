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
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.graphics.Map.*;

public class BombermanGame extends Application {
    //window size
    public static final int WIDTH = 1080;
    public static final int HEIGHT = 720;

    // stage
    public static GraphicsContext gc;
    private Canvas canvas;

    // map masking


    // game entities
    public static List<Entity> entities = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static List<Entity> stillObjects = new ArrayList<>();

    public static int heightMap;
    public static int widthMap;
    public static int level;

    @Override
    public void start(Stage stage) {
        // bgm
//         music();

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

        scene.setOnKeyPressed(e -> bomberman.kb.hold(e));
        scene.setOnKeyReleased(e -> bomberman.kb.release(e));

//        mediaPlayer.setOnEndOfMedia(new Runnable() {
//            public void run() {
//                mediaPlayer.seek(Duration.ZERO);
//            }
//        });
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

    public void updateMask(Entity entity) {
        int posX = entity.getX();
        int posY = entity.getY();
        int width = (int) entity.getBoundary().getWidth();
        int height = (int) entity.getBoundary().getHeight();
        try {
            for (int i = posX; i < posX + width; i++) {
                for (int j = posY; j < posY + height; j++) {
                    mapMask[i][j] = entity.maskNumber;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("map too big");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("character out of bound");
            System.exit(1);
        }
    }

    public void updateMaskMap() {
        stillObjects.forEach(e -> updateMask(e));
        entities.forEach(e ->  updateMask(e));
        updateMask(bomberman);
    }

    public void update() {
        //keyboard
        bomberman.kbUpdate();

        //interaction
        stillObjects.forEach( (Entity e) -> {
            if(!(e instanceof Grass) && collision(e, bomberman)) {
                bomberman.setCollision(true);
                System.out.println("collided");
            }
        });

        stillObjects.forEach( (Entity e) -> {
            entities.forEach(entity -> {
                if(collision(e, entity) && !(e instanceof Grass)) {
                    entity.setCollision(true);
                }
            });
        });

        bomberman.update();
        entities.forEach(Entity::update);
        stillObjects.forEach(Entity::update);

        for(int i = 0; i < entities.size(); i++) {
            if(entities.get(i) instanceof Oneal) {
                ((Oneal) entities.get(i)).getPlayerPos(bomberman);
            }
            if(entities.get(i).isFlag()) {
                entities.remove(entities.get(i));
                i--;
            }
        }
        updateMaskMap();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach( g -> {
//            if(g instanceof Bomb) {
//                ((Bomb) g).getLeftFlame().forEach(flame -> flame.render(gc));
//            }
            g.render(gc);
        });
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
