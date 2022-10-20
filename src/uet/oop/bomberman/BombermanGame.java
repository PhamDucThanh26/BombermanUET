package uet.oop.bomberman;

import SoundEffect.Sound;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.entities.BuffItem.Item;
import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.entities.creature.Creature.creatures;
import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.graphics.Map.*;
import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;

public class BombermanGame extends Application {

    private final String[] containLevel = {
            "\\res\\levels\\Level0.txt",
            "\\res\\levels\\Level1.txt",
            "\\res\\levels\\Level2.txt",
    };
    // stage
    static int _level = 0;

    public static boolean isPause = false;
    void nextLevel() {
        level++;
        stillObjects.clear();
        backgroundTitle.clear();
        creatures.clear();
//        bomberman.reset();
        createMap(containLevel[_level]);
    }
    public static GraphicsContext gc;
    private Canvas canvas;

    // game creatures
    public static Bomber bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> backgroundTitle = new ArrayList<>();

    public static int heightMap;
    public static int widthMap;
    public static int level;

    // audio
    public Sound startStage = new Sound();
    public Sound backGround = new Sound();

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

        createMap(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt");
        startStage.playStartStage();
        backGround.playBackGround();

        // Tao scene
        Scene scene = new Scene(root);
        // Them scene vao stage
        stage.setTitle("Bomberman");
        //Passing FileInputStream object as a parameter
        Image img = new Image("file:res//icon.png");
        stage.getIcons().add(img);
        stage.setResizable(false);

        scene.setOnKeyPressed(e -> bomberman.kb.hold(e));
        scene.setOnKeyReleased(e -> bomberman.kb.release(e));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
                render();
            }
        };
        timer.start();

//        Thread there

        stage.setScene(scene);
        stage.show();
    }

    public void updateNodes(Entity entity) {
        double posX = entity.getX();
        double posY = entity.getY();
        int width = (int) entity.getBoundary().getWidth();
        int height = (int) entity.getBoundary().getHeight();
        try {
            for (int i = (int) posX; i < posX + width; i++) {
                for (int j = (int) posY; j < posY + height; j++) {
                    mapNodes[i][j] = entity.NodesNumber;
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

    public void updateNodesMap() {
        stillObjects.forEach(this::updateNodes);
        creatures.forEach(this::updateNodes);
        miscellaneous.forEach(this::updateNodes);
        updateNodes(bomberman);
    }

    public void update() {
        //keyboard
        if(!isPause) {
            bomberman.kbUpdate();

            //interaction
            stillObjects.forEach((Entity e) -> {
                if (!(e instanceof Grass || e instanceof Portal || e instanceof Item) && collision(e, bomberman)) {
                    bomberman.setCollision(true);
                }
            });

            stillObjects.forEach((Entity e) -> creatures.forEach(entity -> {
                if (collision(e, entity) && !(e instanceof Grass)) {
                    entity.setCollision(true);
                }
            }));
            bomberman.update();
            creatures.forEach(Entity::update);
            stillObjects.forEach(Entity::update);
            miscellaneous.forEach(Item::update);
            creatures.removeIf(Entity::isFlag);
            stillObjects.removeIf(Entity::isFlag);
            miscellaneous.removeIf(Entity::isFlag);
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        backgroundTitle.forEach(g -> g.render(gc));
        miscellaneous.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        creatures.forEach(g -> g.render(gc));
        bomberman.render(gc);
        gc.drawImage(Sprite.explosion_horizontal.getFxImage(), 0, 0);
    }
}
