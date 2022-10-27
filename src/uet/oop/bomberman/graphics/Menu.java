package uet.oop.bomberman.graphics;

import uet.oop.bomberman.sound_effect.Bgm;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import uet.oop.bomberman.level.Game;
import uet.oop.bomberman.sound_effect.Sound;

import java.io.File;
import java.util.Scanner;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.BombermanGame.root;
import static uet.oop.bomberman.graphics.Score.*;
import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;
import static uet.oop.bomberman.graphics.TaskBar.pane;
import static uet.oop.bomberman.level.Game.status;

public class Menu {

    public static Image levelUpImage;
    public static ImageView levelUp;
    public static Image authorImage;
    public static boolean hasMusic = true;
    public static Image overGame;
    public static ImageView author;
    private static final Text[] buttonText = new Text[4];
    private static final Rectangle[] rect = new Rectangle[4];

    public static Pane layoutMenu;
    public static ImageView gameOver;
    static Bgm bgm = new Bgm();

    public static void returnMenu() {
        pane.setVisible(false);
        author.setVisible(true);
        layoutMenu.setVisible(true);
    }

    public static void createMenu(Group root) {
        createHighScore();
        readHighScore();
        System.out.println(highScore);
        bgm.startBgm(Bgm.menuBgm, Bgm.DEFAULT_VOLUME);
        levelUpImage = new Image("images/levelUp.png");
        levelUp = new ImageView(levelUpImage);
        levelUp.setX(0);
        levelUp.setY(Sprite.SCALED_SIZE);
        levelUp.setFitWidth(WIDTH);
        levelUp.setFitHeight(HEIGHT);
        levelUp.setVisible(false);
        // background image

        authorImage = new Image("images/menu.jpg");
        author = new ImageView(authorImage);
        author.setFitWidth(WIDTH);
        author.setFitHeight(HEIGHT + Sprite.SCALED_SIZE);
        author.setVisible(true);

        overGame = new Image("images/gameOver.png");
        gameOver = new ImageView(overGame);
        gameOver.setFitHeight(HEIGHT + Sprite.SCALED_SIZE);
        gameOver.setFitWidth(WIDTH);
        gameOver.setX(0);
        gameOver.setY(Sprite.SCALED_SIZE);
        gameOver.setVisible(false);

        // set text
        for (int i = 0; i < 4; i++) {
            buttonText[i] = new Text();
            if (i == 0) {
                buttonText[i].setText("New Game");
                buttonText[i].setX(325);
                buttonText[i].setY(280);
            } else if (i == 1) {
                buttonText[i].setText("Sound:On");
                buttonText[i].setX(325);
                buttonText[i].setY(340);
            }
            else if(i == 2) {
                buttonText[i].setText("HighScore");
                buttonText[i].setX(320);
                buttonText[i].setY(400);
            }else {
                buttonText[i].setText("Exit");
                buttonText[i].setX(360);
                buttonText[i].setY(460);
            }
            buttonText[i].setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));
            buttonText[i].setFill(Color.WHITE);
            buttonText[i].setStroke(Color.BLACK);
        }

        // rect
        for (int i = 0; i < 4; i++) {
            rect[i] = new Rectangle();
            if (i == 0) {
                rect[i].setY(250);
            } else if (i == 1) {
                rect[i].setY(310);
            } else if(i == 2) {
                rect[i].setY(370);
            }else {
                rect[i].setY(430);
            }
            rect[i].setWidth(200);
            rect[i].setHeight(40);
            rect[i].setX((double) WIDTH / 2 - rect[i].getWidth() / 2);
            rect[i].setFill(Color.ORANGE);
            rect[i].setArcHeight(30);
            rect[i].setArcWidth(30);
            rect[i].setStroke(Color.WHITE);
            rect[i].setStrokeWidth(3);
        }


        layoutMenu = new Pane();
        layoutMenu.getChildren().addAll(rect[0], rect[1], rect[2], rect[3],
                buttonText[3], buttonText[0], buttonText[1], buttonText[2]);
        layoutMenu.setMinSize(WIDTH, HEIGHT);
        layoutMenu.setMaxSize(WIDTH, HEIGHT);

        root.getChildren().addAll(author, layoutMenu, gameOver, levelUp);
        TaskBar.createTaskBar();
        pane.setVisible(false);

        for (Text i : buttonText) {
            i.setOnMouseEntered(event -> {
                i.setFont(Font.font("BOMBERMA", FontWeight.BOLD, FontPosture.REGULAR, 25));
                i.setFill(Color.RED);
                i.setStroke(Color.YELLOW);
            });
            i.setOnMouseExited(event -> {
                i.setFont(Font.font("BOMBERMA", FontWeight.BOLD, FontPosture.REGULAR, 25));
                i.setFill(Color.WHITE);
                i.setStroke(Color.BLACK);
            });
        }

        buttonText[0].setOnMouseClicked(event -> {
            Game.game(System.getProperty("user.dir") + "\\res\\levels\\Level2.txt", sceneGame);
            author.setVisible(false);
            layoutMenu.setVisible(false);
//            bgm.stopBgm();
        });
        buttonText[3].setOnMouseClicked(event -> stage.close());
        buttonText[1].setOnMouseClicked(event -> {
            hasMusic = !hasMusic;
            if(!hasMusic) {
                buttonText[1].setText("Sound: OFF");
                bgm.stopBgm();
            }
            else {
                buttonText[1].setText("Sound: ON");
                bgm.startBgm(Bgm.menuBgm, Bgm.DEFAULT_VOLUME);
            }
        });

        buttonText[2].setOnMouseClicked(mouseEvent -> {
            System.out.println(isRHS);
                isRHS = !isRHS;
            if(isRHS) {
               layoutMenu.getChildren().add(renderHighScore);
               layoutMenu.getChildren().add(scoreText);
            }
            else {
                layoutMenu.getChildren().remove(renderHighScore);
                layoutMenu.getChildren().remove(scoreText);
            }
        });
    }
}




