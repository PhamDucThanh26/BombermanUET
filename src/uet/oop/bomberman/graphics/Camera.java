package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.level.Game.bomberman;

public class Camera {
    private double cameraX;
    private double cameraY;

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
        if(focusObject != null) {
            cameraX = focusObject.getX() - (double) Sprite.WIDTH / 2;
            cameraY = focusObject.getY() - (double) Sprite.HEIGHT / 2 + Sprite.SCALED_SIZE * 2;
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
    }
}
