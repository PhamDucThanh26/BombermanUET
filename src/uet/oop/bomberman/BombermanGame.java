package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import javafx.stage.Stage;
import uet.oop.bomberman.audio.Sound;
import uet.oop.bomberman.level.Game;

import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;

public class BombermanGame extends Application {
    //Update menu 17/10/2022

    public static GraphicsContext gc;
    public static Canvas canvas;

    public static Stage stage;

    // audio
    public Sound startStage = new Sound();
    public Sound backGround = new Sound();

    public static Group root;

    @Override
    public void start(Stage primaryStage) {
        stage = new Stage();
        canvas = new Canvas(WIDTH, HEIGHT);

        gc = canvas.getGraphicsContext2D();
        // Tao root container
        root = new Group();

        root.getChildren().add(canvas);
        startStage.playStartStage();
        backGround.playBackGround();

        // Tao scene
        Scene sceneGame = new Scene(root);
        // Them scene vao stage
        stage.setTitle("Bomberman");
        //Passing FileInputStream object as a parameter
        Image img = new Image("file:res//icon.png");
        stage.getIcons().add(img);
        stage.setResizable(false);

        Game.game(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt", sceneGame);

        stage.setScene(sceneGame);
        stage.show();
    }

}