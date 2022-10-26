package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.entities.creature.Bomber;
import uet.oop.bomberman.level.Game;

import static uet.oop.bomberman.entities.BuffItem.Item.miscellaneous;
import static uet.oop.bomberman.graphics.Score.updateHighScore;
import static uet.oop.bomberman.graphics.Score.yourScore;

import static uet.oop.bomberman.BombermanGame.sceneGame;
import static uet.oop.bomberman.BombermanGame.stage;
import static uet.oop.bomberman.graphics.Map.createMap;
import static uet.oop.bomberman.graphics.Menu.gameOver;
import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;
import static uet.oop.bomberman.level.Game.*;

public class TaskBar {
    private static Pane pane = new Pane();
    private static ImageView statusGame;
    private static ImageView quit;
    public static Text level, bomb, time, score;

    public static void createTaskBar(Group root) {
        score = new Text("Score: " + yourScore);
        score.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        score.setFill(Color.WHITE);
        score.setX(500);
        score.setY(20);
        level = new Text("Level: 1");
        level.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        level.setFill(Color.WHITE);
        level.setX(300);
        level.setY(20);

        bomb = new Text("Bombs: " + bomberman.bombNumber);
        bomb.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        bomb.setFill(Color.WHITE);
        bomb.setX(700);
        bomb.setY(20);


        Image quitGame = new Image("images/quitGame.png");

        quit =  new ImageView(quitGame);

        quit.setX(170);
        quit.setY(0);
        quit.setFitWidth(100);
        quit.setFitHeight(32);
        Image newGame = new Image("images/pauseButton.png");
        statusGame = new ImageView(newGame);
        statusGame.setX(-75);
        statusGame.setY(-10);
        statusGame.setScaleX(0.5);
        statusGame.setScaleY(0.5);
        pane = new Pane();
        pane.getChildren().addAll(statusGame, level, bomb, score, quit);
//        pane.setLayoutY(screenY);
        pane.setMinSize(200, 32);
        pane.setMaxSize(800, 32);
        pane.setStyle("-fx-background-color: #353535");
        root.getChildren().add(pane);

        quit.setOnMouseClicked(event -> {
            stage.close();
        });
        statusGame.setOnMouseClicked(event -> {
            if (bomberman.isLife()) {
                isPause = !isPause;
            } else {
                Game.reset();
                Game.game((System.getProperty("user.dir") + "\\res\\levels\\Level0.txt"), sceneGame);
                level_ = 1;
                bomberman.bombPower = 1;
                bomberman.bombNumber = 1;
                bomberman.setSpeed(1);
                yourScore = 0;
            }
            updateMenu();
        });
    }
    public static void updateMenu() {
        if(bomberman.isStillRender()) {
            gameOver.setX(-1000);
            gameOver.setY(-1000);
        }
        if(!bomberman.isStillRender()) {
            gameOver.setX(0);
            gameOver.setY(0);
        }
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
        level.setText("Level :" + Map.level);
        bomb.setText("Bombs: " + bomberman.bombNumber);
        score.setText("Score: " + yourScore);
    }
}
