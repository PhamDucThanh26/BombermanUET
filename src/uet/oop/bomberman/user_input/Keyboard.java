package uet.oop.bomberman.user_input;
import static uet.oop.bomberman.BombermanGame.isPause;
import javafx.scene.input.KeyEvent;

public class Keyboard {
    public boolean up, down, left, right, plant_bomb;
    public void hold(KeyEvent event) {
        switch (event.getCode()) {
            case P:
                isPause = !isPause;
                break;
            case W:
            case UP:
                up = true;
                break;
            case S:
            case DOWN:
                down = true;
                break;
            case A:
            case LEFT:
                left = true;
                break;
            case D:
            case RIGHT:
                right = true;
                break;
            case SPACE:
            case F:
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
                break;
            case S:
            case DOWN:
                down = false;
                break;
            case A:
            case LEFT:
                left = false;
                break;
            case D:
            case RIGHT:
                right = false;
                break;
            case SPACE:
                plant_bomb = false;
            default:
                break;
        }
    }
}
