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

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;

public class Menu {
    public static Image authorImage;

    public static boolean hasMusic = true;
    public static Image overGame;
    public static ImageView author;
    private static final Text[] buttonText = new Text[3];
    private static final Rectangle[] rect = new Rectangle[3];
    public static Pane layoutMenu;
    public static ImageView gameOver;
    static Bgm bgm = new Bgm();

    public static void createMenu(Group root) {
        // bgm

        bgm.startBgm(Bgm.menuBgm, Bgm.DEFAULT_VOLUME);

        // background image
        authorImage = new Image("images/menu.png");
        author = new ImageView(authorImage);
        author.setFitWidth(WIDTH);
        author.setFitHeight(HEIGHT);
        author.setX(0);
        author.setY(0);

        overGame = new Image("images/gameOver.png");
        gameOver = new ImageView(overGame);
        gameOver.setFitHeight(HEIGHT);
        gameOver.setFitWidth(WIDTH);
        gameOver.setX(-1000);
        gameOver.setY(-1000);

        // set text
        for (int i = 0; i < 3; i++) {
            buttonText[i] = new Text();
            if (i == 0) {
                buttonText[i].setText("New Game");
                buttonText[i].setX(325);
                buttonText[i].setY(280);
            } else if (i == 1) {
                buttonText[i].setText("Sound:On");
                buttonText[i].setX(325);
                buttonText[i].setY(340);
            } else {
                buttonText[i].setText("Exit");
                buttonText[i].setX(360);
                buttonText[i].setY(400);
            }
            buttonText[i].setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));
            buttonText[i].setFill(Color.WHITE);
            buttonText[i].setStroke(Color.BLACK);
        }

        // rect
        for (int i = 0; i < 3; i++) {
            rect[i] = new Rectangle();

            if (i == 0) {
                rect[i].setY(250);
            } else if (i == 1) {
                rect[i].setY(310);
            } else {
                rect[i].setY(370);
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
        layoutMenu.getChildren().addAll(rect[0], rect[1], rect[2], buttonText[0], buttonText[1], buttonText[2]);
        layoutMenu.setMinSize(WIDTH, HEIGHT);
        layoutMenu.setMaxSize(WIDTH, HEIGHT);
        root.getChildren().addAll(author, layoutMenu, gameOver);


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
            Game.game(System.getProperty("user.dir") + "\\res\\levels\\Level3.txt", sceneGame);
            author.setX(-1000);
            author.setY(-1000);
            layoutMenu.setTranslateX(-1000);
            layoutMenu.setTranslateY(-1000);
//            bgm.stopBgm();
        });
        buttonText[2].setOnMouseClicked(event -> stage.close());
        buttonText[1].setOnMouseClicked(event -> {
            if(hasMusic) {
                buttonText[1].setText("Sound: OFF");
                bgm.stopBgm();
                hasMusic = false;
            }
            else {
                hasMusic = true;
                buttonText[1].setText("Sound: ON");
                bgm.startBgm(Bgm.menuBgm, Bgm.DEFAULT_VOLUME);
            }
        });

    }

}




