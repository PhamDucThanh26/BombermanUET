package uet.oop.bomberman.entities.creature;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.path_finding.AStar;
import uet.oop.bomberman.path_finding.Node;

import java.util.*;

import static uet.oop.bomberman.entities.Interaction.collision;
import static uet.oop.bomberman.level.Game.bomberman;
import static uet.oop.bomberman.level.Game.stillObjects;

public class Oneal extends Creature {
    final Image[] leftAnimation = {
            Sprite.oneal_left1.getFxImage(),
            Sprite.oneal_left2.getFxImage(),
            Sprite.oneal_left3.getFxImage()
    };

    final Image[] rightAnimation = {
            Sprite.oneal_right1.getFxImage(),
            Sprite.oneal_right2.getFxImage(),
            Sprite.oneal_right3.getFxImage()
    };

    final Image[] deadAnimation = {
            Sprite.oneal_dead.getFxImage(),
            Sprite.oneal_dead.getFxImage(),
            Sprite.mob_dead1.getFxImage(),
            Sprite.mob_dead2.getFxImage(),
            Sprite.mob_dead3.getFxImage(),
    };
    // grid position
    private int startX = (int) (solidArea.getX()) / Sprite.SCALED_SIZE;
    private int startY = (int) (solidArea.getY()) / Sprite.SCALED_SIZE;

    private int endX = (int) bomberman.getSolidArea().getX() / Sprite.SCALED_SIZE;
    private int endY = (int) bomberman.getSolidArea().getY() / Sprite.SCALED_SIZE;
    // algorithm
    List<Node> path = new ArrayList<>();

    // background task
    Timer timer = new Timer(true);

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                getPositionOnGrid();
                AStar pathFindingAlgorithm = new AStar(startX, startY, endX, endY);

                // path finding run
                pathFindingAlgorithm.algorithmProcessing();
                pathFindingAlgorithm.printPath();
                path.clear();
                path = pathFindingAlgorithm.nodeList();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public Oneal(double xUnit, double yUnit, Image img) {
        super(xUnit, yUnit, img);
        solidArea = new Rectangle(x + 1, y + 1, width - 2, height - 2);
        SCORE = 100;
        timer.schedule(task, 0, 5000);
    }

    void getPositionOnGrid() {
        startX = (int) (solidArea.getX()) / Sprite.SCALED_SIZE;
        startY = (int) (solidArea.getY()) / Sprite.SCALED_SIZE;

        endX = (int) bomberman.getSolidArea().getX() / Sprite.SCALED_SIZE;
        endY = (int) bomberman.getSolidArea().getY() / Sprite.SCALED_SIZE;
    }

    @Override
    public void move() {
        xVec = 0;
        yVec = 0;
        if (path.isEmpty()) {
            return;
        }

        // next node's position
        double nextX = path.get(0).col * Sprite.SCALED_SIZE;
        double nextY = path.get(0).row * Sprite.SCALED_SIZE;

        // bomberman solid area position

        // check the direction they should move
        if (nextX < x) {
            xVec = -1;
        } else if (nextX > x) {
            xVec = 1;
        }
        stillObjects.forEach(e -> {
            if (collision(e, this)) {
                xVec = 0;
            }
        });

        if (nextY < y) {
            yVec = -1;
        } else if (nextY > y) {
            yVec = 1;
        }
        stillObjects.forEach(e -> {
            if (collision(e, this)) {
                yVec = 0;
            }
        });

        x += xVec;
        y += yVec;
        if (x == nextX && y == nextY) {
            path.remove(0);
        }
    }

    @Override
    public void updateAnimation() {
        if (frame % 10 == 0) {
            frameCount++;
            frameCount %= 3;
        }
        if (xVec > 0) {
            img = rightAnimation[frameCount];
        } else {
            img = leftAnimation[frameCount];
        }
    }

    @Override
    public void update() {
        // path finding
        if (isLife) {
            super.update();
            move();
            updateAnimation();
            solidArea.setX(x + 1);
            solidArea.setY(y + 1);
        } else {
            dead();
        }
    }

    @Override
    public void dead() {
        timer.cancel();
        animateDead++;
        if (animateDead % 40 == 0) {
            frameCount++;
        }

        frameCount %= 5;
        img = deadAnimation[frameCount];
        if (frameCount == 4) {
            this.flag = true;
        }
    }
}
