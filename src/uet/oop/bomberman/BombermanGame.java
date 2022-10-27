package uet.oop.bomberman;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.TaskBar;
import uet.oop.bomberman.sound_effect.Sound;
import uet.oop.bomberman.graphics.Menu;

import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;
import static uet.oop.bomberman.graphics.TaskBar.pane;

public class BombermanGame extends Application {
    //Update menu 17/10/2022
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static Stage stage;
    public static Scene sceneGame;
    public static Group root;

    public static ImageView authorView;

    @Override
    public void start(Stage primaryStage) {
        stage = new Stage();
        canvas = new Canvas(WIDTH, HEIGHT);
        canvas.setLayoutY(32);
        gc = canvas.getGraphicsContext2D();
        // Tao root container
        root = new Group();
        root.getChildren().add(canvas);
        Menu.createMenu(root);
        sceneGame = new Scene(root);
        stage.setMaxWidth(WIDTH + Sprite.SCALED_SIZE / 2);
        stage.setMaxHeight(HEIGHT);
        stage.setTitle("Bomberman");
        //Passing FileInputStream object as a parameter
        Image img = new Image("file:res//icon.png");
        stage.getIcons().add(img);
        stage.setResizable(true);
        stage.setScene(sceneGame);
//        TaskBar.createTaskBar(root);
//        pane.setVisible(false);
        stage.show();
    }

}