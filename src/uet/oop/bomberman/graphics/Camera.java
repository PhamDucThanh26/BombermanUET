package uet.oop.bomberman.graphics;

import uet.oop.bomberman.entities.Entity;

import static uet.oop.bomberman.Game.bomberman;

public class Camera {
    private double cameraX;
    private double cameraY;

    private Entity focusObject = bomberman;

    public Camera() {
        this.cameraX = 0;
        this.cameraY = Sprite.SCALED_SIZE;
    }

    public void setFocusObject(Entity focusObject) {
        this.focusObject = focusObject;
    }

    public double getCameraX() {
        return cameraX;
    }

    public double getCameraY() {
        return cameraY;
    }

    public void update() {
        if(focusObject != null) {
            cameraX = focusObject.getX() - (double) Sprite.WIDTH / 2;
            cameraY = focusObject.getY() - (double) Sprite.HEIGHT / 2 + Sprite.SCALED_SIZE * 3;
            setWithinMap();
        } else {
            System.out.println("focus is null");
        }
    }

    private void setWithinMap() {
        if(cameraX < 0) {
            cameraX = 0;
        }
        if(cameraY < 0) {
            cameraY = 0;
        }

        if(cameraX + Sprite.WIDTH > Map.widthMap * Sprite.SCALED_SIZE) {
            cameraX = Map.widthMap * Sprite.SCALED_SIZE - Sprite.WIDTH;
        }

        if(cameraY + Sprite.HEIGHT > Map.heightMap * Sprite.SCALED_SIZE ) {
            cameraY = (Map.heightMap) * Sprite.SCALED_SIZE - Sprite.HEIGHT;
        }
    }
}
