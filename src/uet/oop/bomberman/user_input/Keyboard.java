package uet.oop.bomberman.user_input;

import javafx.scene.input.KeyEvent;

import java.util.HashSet;
import java.util.Set;

public class Keyboard {
    public Set<String> keySet = new HashSet<>();

    public void hold(KeyEvent e) {
        keySet.add(e.getCode().toString());
    }

    public void release(KeyEvent e) {
        keySet.remove(e.getCode().toString());
    }

}
