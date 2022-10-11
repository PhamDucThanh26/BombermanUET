package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Balloom extends Creature {
    private int pivot;
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        pivot = x;
        maskNumber = '1';
    }

    private double xVec = 1;

//    updateMove();
//    updateanimation();
    protected void move() {
        x += xVec;
        if( x + 80 < pivot || x > pivot + 80 || collision) {
            xVec = -xVec;
        }
    }

    @Override
    public void updateAnimation() {

    }

    @Override
    public void update() {
        move();
    }
}
