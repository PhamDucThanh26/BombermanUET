package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Oneal extends Creature{
    public Oneal(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    int playerX;
    int playerY;

    @Override
    public void move() {
        xVec = (x - playerX) > 0 ? -1 : 1;
        yVec = (y - playerY) > 0 ? -1 : 1;
        x += xVec;
        y += yVec;
    }

    public void getPlayerPos(Entity e) {
        playerX = e.getX();
        playerY = e.getY();
    }

    @Override
    public void updateAnimation() {

    }

    @Override
    public void update() {
        move();
    }

}
