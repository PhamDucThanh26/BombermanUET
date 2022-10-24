package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.graphics.Map.heightMap;
import static uet.oop.bomberman.graphics.Map.widthMap;
import static uet.oop.bomberman.level.Game.bomberman;

public class Camera {
    private double cameraX;
    private double cameraY;

    public static double MAP_HEIGHT;
    public static double MAP_WIDTH;
    private final double cameraW = Sprite.WIDTH ;

    private final double cameraH =  Sprite.HEIGHT;

    private Entity focusObject = bomberman;

    public Camera() {
        this.cameraX = 0;
        this.cameraY = 0;
    }

    public void setFocusObject(Entity focusObject) {
        this.focusObject = focusObject;
    }

    public double getCameraX() {
        return cameraX;
    }

    public void setCameraX(double cameraX) {
        this.cameraX = cameraX;
    }

    public double getCameraY() {
        return cameraY;
    }

    public void setCameraY(double cameraY) {
        this.cameraY = cameraY;
    }

    public void update() {
        System.out.println(MAP_HEIGHT + " " + MAP_WIDTH);
        if(focusObject != null) {
            cameraX = focusObject.getX() - cameraW / 2;
//            cameraY = focusObject.getY() - cameraH / 2 + Sprite.SCALED_SIZE * 2;
            cameraY = focusObject.getY() - cameraH / 2 + Sprite.SCALED_SIZE * 2;;
            setWithinMap();
        }
    }

    private void setWithinMap() {
        if(cameraX < 0) {
            cameraX = 0;
        }
        if(cameraY < 0) {
            cameraY = 0;
        }
        if(cameraX > MAP_WIDTH - cameraW) {
            cameraX = MAP_WIDTH - cameraW;
            System.out.println(focusObject.getX() + " " + focusObject.getY());
            System.out.println("update width");
        }
        if(cameraY > MAP_HEIGHT - cameraH) {
            cameraY = (double) Sprite.HEIGHT - cameraH + 32;
            System.out.println("update height");
        }
    }
}
