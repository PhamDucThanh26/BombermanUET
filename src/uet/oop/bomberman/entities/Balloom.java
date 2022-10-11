package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

public class Balloom extends Entity{
    private int pivot;
    public Balloom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        pivot = x;
    }

    private double xVec = 1;

//    updateMove();
//
//    updateanimation();
    void move() {
//        xVec = rand
        x += xVec;
        if( x + 80 < pivot || x > pivot + 80) {
            xVec = -xVec;
        }
    }

    @Override
    public void update() {
        move();
    }
}
