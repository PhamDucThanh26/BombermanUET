package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.level.Game;

import static uet.oop.bomberman.BombermanGame.sceneGame;

import static uet.oop.bomberman.BombermanGame.yourScore;
import static uet.oop.bomberman.entities.creature.Bomber.bombNumber;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.graphics.Menu.game;
import static uet.oop.bomberman.level.Game.*;

public class TaskBar {

    private static int screenY = 512;
    private static Pane pane;
    private static ImageView statusGame;
    public static Text level, bomb, time, score;

    public static void createTaskBar(Group root) {
        score = new Text("Score: 0");
        score.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        score.setFill(Color.WHITE);
        score.setX(450);
        score.setY(20);
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(200);
        level.setY(20);

        bomb = new Text("Bombs: " + bombNumber);
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(700);
        bomb.setY(20);


        Image newGame = new Image("images/pauseButton.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);

        pane = new Pane();
        pane.getChildren().addAll(statusGame, level, bomb, score);

        pane.setLayoutY(screenY);
        pane.setMinSize(800, 480);
        pane.setMaxSize(800, 480);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);

        statusGame.setOnMouseClicked(event -> {
            if (bomberman.isLife()) {
                isPause = !isPause;
            } else {
                game = new Game();
                createMap(System.getProperty("user.dir") + "\\res\\levels\\Level0.txt");
            }
            updateMenu();
        });
    }
    public static void updateMenu() {
        if(bomberman.isLife()) {
            if (!isPause) {
                Image pauseGame = new Image("images/pauseButton.png");
                statusGame.setImage(pauseGame);
            } else {
                Image playGame = new Image("images/startButton.png");
                statusGame.setImage(playGame);
            }
        }
        else {
            Image newGame = new Image("images/newGame.png");
            statusGame.setImage(newGame);

        }
    }
    public static void updateRender() {
        pane.setLayoutY(screenY);
//        if(bomberman.getY() >= 224) {
//            System.out.println(bomberman.getY());
//            pane.setLayoutY(736 - bomberman.getY());
//            System.out.println(pane.getLayoutY());
//        }
        level.setText("Level :" + level_);
        bomb.setText("Bombs: " + bombNumber);
        score.setText("Score: " + yourScore);
    }
}
