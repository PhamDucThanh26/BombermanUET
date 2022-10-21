package uet.oop.bomberman.level;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import uet.oop.bomberman.entities.BuffItem.Item;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.graphics.Camera;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.canvas;
import static uet.oop.bomberman.BombermanGame.gc;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.entities.creature.Creature.creatures;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.graphics.Map.mapNodes;

public class Game {
    private final String[] containLevel = {
            "\\res\\levels\\Level0.txt",
            "\\res\\levels\\Level1.txt",
            "\\res\\levels\\Level2.txt",
    };
    public static boolean isPause = false;
    // game creatures
    public static Bomber bomberman = new Bomber(2, 2, Sprite.player_right.getFxImage());
    public static Camera camera = new Camera();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> backgroundTitle = new ArrayList<>();
    public static void game(String level, Scene scene) {
        createMap(level);
        scene.setOnKeyPressed(e -> bomberman.kb.hold(e));
        scene.setOnKeyReleased(e -> bomberman.kb.release(e));
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (!isPause) {
                    update();
                }
                render();
            }
        };
        timer.start();
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

    public static void update() {
        //keyboard
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
        camera.update();
    }

    public static void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        backgroundTitle.forEach(g -> g.render(gc));
        miscellaneous.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        creatures.forEach(g -> g.render(gc));
        if (bomberman.isStillRender()) {
            bomberman.render(gc);
        }
    }
}
