package uet.oop.bomberman.user_input;

import javafx.scene.input.KeyEvent;

import java.awt.*;

public class Keyboard {
    public boolean up, down, left, right, plant_bomb, speed_up;
    public void hold(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                up = true;
                System.out.println("up");
                break;
            case S:
            case DOWN:
                down = true;
                System.out.println("down");
                break;
            case A:
            case LEFT:
                left = true;
                System.out.println("left");
                break;
            case D:
            case RIGHT:
                right = true;
                System.out.println("right");
                break;
            case SPACE:
                plant_bomb = true;
            default:
                break;
        }
    }

    public void release(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                up = false;
                System.out.println("release up");
                break;
            case S:
            case DOWN:
                down = false;
                System.out.println("release down");
                break;
            case A:
            case LEFT:
                left = false;
                System.out.println("release left");
                break;
            case D:
            case RIGHT:
                right = false;
                System.out.println("release right");
                break;
            case SPACE:
                plant_bomb = false;
                System.out.println("bomb has been planted");
            default:
                break;
        }
    }
}
