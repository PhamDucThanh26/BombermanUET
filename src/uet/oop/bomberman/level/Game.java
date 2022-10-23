package uet.oop.bomberman.level;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import uet.oop.bomberman.entities.BuffItem.Item;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Portal;
import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.entities.creature.Creature;
import uet.oop.bomberman.graphics.Camera;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.TaskBar;

import java.util.ArrayList;
import java.util.List;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.entities.Portal.isPortal;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.graphics.Map.mapNodes;
import static uet.oop.bomberman.level.NextLevel.wait;
import static uet.oop.bomberman.level.NextLevel.waitToLevelUp;
import static uet.oop.bomberman.level.NextLevel.waitingTime;

public class Game {
    // progress

    public static int yourScore = 0;
    public static boolean isPause = false;
    public static int level_ = 1;

    // game entities
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> backgroundTitle = new ArrayList<>();
    public static Bomber bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    public static List<Creature> creatures = new ArrayList<>();

    // camera lock on player's position
    public static Camera camera = new Camera();

    public static void game(String level, Scene scene) {
        TaskBar.createTaskBar(root);
        createMap(level);
        scene.setOnKeyPressed(e -> {
            if (e.getCode().toString().equals("P")) {
                isPause = !isPause;
            }
            bomberman.kb.hold(e);
        });
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

    public static void updateNodes(Entity entity) {
        int nodeX = (int) entity.getX() / Sprite.SCALED_SIZE;
        int nodeY = (int) entity.getY() / Sprite.SCALED_SIZE;
        try {
            mapNodes[nodeX][nodeY] = entity.nodeNumber;
        } catch (NullPointerException e) {
            System.out.println("map too big");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("character out of bound");
            System.exit(1);
        }
    }

    public static void updateNodeMap() {
        stillObjects.forEach(Game::updateNodes);
        creatures.forEach(Game::updateNodes);
        miscellaneous.forEach(Game::updateNodes);
        updateNodes(bomberman);
    }

    public static void update() {
        TaskBar.updateMenu();
        TaskBar.updateRender();
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
        for (int i = 0; i < creatures.size(); i++) {
            if (creatures.get(i).isFlag()) {
                yourScore += creatures.get(i).SCORE;
                creatures.remove(i);
                i--;

            }
        }
        stillObjects.removeIf(Entity::isFlag);
        miscellaneous.removeIf(Entity::isFlag);
        camera.update();
        updateNodeMap();

        if (creatures.size() == 0 && !isPortal && !wait) {
            Entity portal = new Portal(1, 1, Sprite.portal.getFxImage());
            stillObjects.add(portal);
            if (collision(bomberman, portal)) {
//                root.getChildren().remove(canvas);
                wait = true;
                waitingTime = System.currentTimeMillis();
            }
        }
        waitToLevelUp();
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
    public static void reset() {
        backgroundTitle.clear();
        miscellaneous.clear();
        stillObjects.clear();
        creatures.clear();
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
    }


}
