package uet.oop.bomberman.graphics;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.Game;
import static uet.oop.bomberman.BombermanGame.root;

import static uet.oop.bomberman.graphics.Menu.*;
import static uet.oop.bomberman.graphics.Score.updateHighScore;
import static uet.oop.bomberman.graphics.Score.yourScore;

import static uet.oop.bomberman.BombermanGame.sceneGame;
import static uet.oop.bomberman.Game.*;

public class TaskBar {
    public static Pane pane = new Pane();
    private static ImageView statusGame;
    public static Text level, bomb, score;

    public static void createTaskBar() {

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
        ImageView quit = new ImageView(quitGame);
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
        pane.getChildren().addAll(statusGame, level, bomb, score, quit);
        root.getChildren().add(pane);
        pane.setMinSize(200, 32);
        pane.setMaxSize(800, 32);
        pane.setStyle("-fx-background-color: #353535");

        quit.setOnMouseClicked(event -> {
            pane.setVisible(false);
            levelUp.setVisible(false);
            gameOver.setVisible(false);

            Game.reset();
            returnMenu();
            updateHighScore();
        });
        statusGame.setOnMouseClicked(event -> {
            if (bomberman.isLife()) {
                if(isPause) {
                    totalPauseDuration += System.currentTimeMillis() - startPauseTime;
                    startPauseTime = 0;
                } else {
                    startPauseTime = System.currentTimeMillis();
                }
                isPause = !isPause;
            } else {
                gameOver.setVisible(false);
                levelUp.setVisible(false);
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
