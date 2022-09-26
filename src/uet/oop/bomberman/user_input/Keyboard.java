package uet.oop.bomberman.user_input;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

import java.awt.*;

public class Keyboard {
    public boolean[] keys = new boolean[120];
    final private int k_up = 0;
    final private int k_down = 1;
    final private int k_left = 2;
    final private int k_right = 3;
    public void hold(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                keys[k_up] = true;
                System.out.println("up");
                break;
            case S:
            case DOWN:
                keys[k_down] = true;
                System.out.println("down");
                break;
            case A:
            case LEFT:
                keys[k_left] = true;
                System.out.println("left");
                break;
            case D:
            case RIGHT:
                keys[k_right] = true;
                System.out.println("right");
                break;
            default:
                break;
        }
    }

    public void release(KeyEvent event) {
        switch (event.getCode()) {
            case W:
            case UP:
                keys[k_up] = false;
                System.out.println("release up");
                break;
            case S:
            case DOWN:
                keys[k_down] = false;
                System.out.println("release down");
                break;
            case A:
            case LEFT:
                keys[k_left] = false;
                System.out.println("release left");
                break;
            case D:
            case RIGHT:
                keys[k_right] = false;
                System.out.println("release right");
                break;
            default:
                break;
        }
    }
}
