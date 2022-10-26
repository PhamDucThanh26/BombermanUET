package uet.oop.bomberman.graphics;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import static uet.oop.bomberman.graphics.Sprite.WIDTH;

public class Score {

    public static int yourScore = 0;
    public static int highScore;
    public static boolean isRHS = false;
    public static final Rectangle renderHighScore = new Rectangle();
    public static Text scoreText = new Text();

    public static final String path = System.getProperty("user.dir") + "\\res\\levels\\HighScore.txt";
    public static void readHighScore() {
        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);
            String s = sc.nextLine();
            highScore = Integer.valueOf(s);
            scoreText.setText("Your Highest Score is: " + highScore);
            scoreText.setX(230);
            scoreText.setY(160);
            scoreText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 25));
            scoreText.setFill(Color.WHITE);
            scoreText.setStroke(Color.BLACK);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createHighScore() {
        renderHighScore.setY(100);
        renderHighScore.setWidth(350);
        renderHighScore.setHeight(100);
        renderHighScore.setX((double) WIDTH / 2 - renderHighScore.getWidth() / 2);
        renderHighScore.setFill(Color.ORANGE);
        renderHighScore.setArcHeight(30);
        renderHighScore.setArcWidth(30);
        renderHighScore.setStroke(Color.WHITE);
        renderHighScore.setStrokeWidth(3);
    }

    public static void updateHighScore() {
        System.out.println(yourScore + " " + highScore);
        if(yourScore > highScore) {
            highScore = yourScore;
        }
            try {
                System.out.println("YES");
                FileWriter file = new FileWriter(path);
                BufferedWriter bf = new BufferedWriter(file);
                String s = Integer.toString(highScore);
                bf.write(s);
                bf.close();
                file.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
