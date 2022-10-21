package uet.oop.bomberman.graphics;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static uet.oop.bomberman.BombermanGame.*;
import static uet.oop.bomberman.graphics.Sprite.HEIGHT;
import static uet.oop.bomberman.graphics.Sprite.WIDTH;

public class Menu {
    public static Image authorImage;
    public static ImageView author;
    private static Text newGameText, exitText, optionsText;

    private static final Button[] menuButton = new Button[3];
    private static final Text[] item = new Text[3];
    private static final Rectangle[] rect = new Rectangle[3];
    public static Pane layoutMenu;


    public static void createMenu(Group root) {
        for (int i = 0; i < 3; i++) {
            menuButton[i] = new Button();
        }
        authorImage = new Image("images/menu.png");
        author = new ImageView(authorImage);
        author.setFitWidth(WIDTH);
        author.setFitHeight(HEIGHT);
        author.setX(0);
        author.setY(0);

//        for(int i = 0;i < 3; i++) {
//            item[i] = new Text();
//            if(i == 0) {
//
//
//                item[i].setText("New Game");
//                item[i].setX(510);
//                item[i].setY(280);
//            }
//            else if(i == 1) {
//                item[i].setText("Options");
//                item[i].setX(520);
//                item[i].setY(340);
//            }
//            else {
//                item[i].setText("Exit");
//                item[i].setX(540);
//                item[i].setY(400);
//            }
//
//            item[i].setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR,25));
//            item[i].setFill(Color.WHITE);
//            item[i].setStroke(Color.BLACK);
//        }

        for (int i = 0; i < 3; i++) {
//            rect[i] = new Rectangle();
            if (i == 0) {
                menuButton[i] = new Button();
                menuButton[i].setText("NewGame");
                menuButton[i].setLayoutY(250);
//                rect[i].setY(250);
            } else if (i == 1) {
                menuButton[i] = new Button();
                menuButton[i].setText("Option");
                menuButton[i].setLayoutY(310);
//                rect[i].setY(310);
            } else {
                menuButton[i] = new Button();
                menuButton[i].setText("Exit");
                menuButton[i].setLayoutY(370);
//                rect[i].setY(370);
            }
            menuButton[i].setMinWidth(200);
//            rect[i].setWidth(200);
//            rect[i].setHeight(40);
            menuButton[i].setLayoutX((double) WIDTH / 2 - menuButton[i].getMinWidth() / 2);
//            rect[i].setX(WIDTH/2-rect[i].getWidth()/2);
//            rect[i].setFill(Color.ORANGE);
//            rect[i].setArcHeight(30);
//            rect[i].setArcWidth(30);
//            rect[i].setStroke(Color.WHITE);
//            rect[i].setStrokeWidth(3);
        }

        layoutMenu = new Pane();
//        layoutMenu.getChildren().addAll(rect[0], rect[1],rect[2],item[0],item[1],item[2]);
        layoutMenu.getChildren().addAll(menuButton[0], menuButton[1], menuButton[2]);
        layoutMenu.setMinSize(600, 600);
        layoutMenu.setMaxSize(600, 600);
        root.getChildren().addAll(author, layoutMenu);


        menuButton[0].setOnMouseClicked(event -> {
            author.setX(-1000);
            author.setY(-1000);
            layoutMenu.setTranslateX(-1000);
            layoutMenu.setTranslateY(-1000);
            System.out.println("play");
        });

        menuButton[0].setOnKeyPressed(event -> {
            author.setX(-1000);
            author.setY(-1000);
            layoutMenu.setTranslateX(-1000);
            layoutMenu.setTranslateY(-1000);
            System.out.println("not play");
        });

        menuButton[2].setOnMouseClicked(event -> stage.close());

//        for(Rectangle Rect:rect) {
//            Rect.setOnMouseEntered(event->{
//                Rect.setFill(Color.rgb(127,255,0));
//            });
//        }
//        for(Rectangle Rect:rect) {
//            Rect.setOnMouseExited(event->{
//                Rect.setFill(Color.ORANGE);
//            });
//        }
    }

}




