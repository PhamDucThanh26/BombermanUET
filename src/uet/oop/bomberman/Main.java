package uet.oop.bomberman;

import javafx.application.Application;

public class Main {
    //Bai tap lon UET.
    public static void main(String[] args) {
        //must be set before launching the Main class
        System.setProperty("quantum.multithreaded", "false");

        Application.launch(BombermanGame.class);
    }
}
