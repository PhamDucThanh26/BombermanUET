package SoundEffect;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {
    //
    public static String bombExplosion = "res\\sound\\bomb_bang.wav";
    public static String startStage = "res\\sound\\startstage.wav";
    public static String bomberDie = "res\\sound\\bomber_die.wav";
    public static String item = "res\\sound\\item.wav";
    public static String lose = "res\\sound\\lose.mid";
    public static String menu = "res\\sound\\menu.wav";
    public static String monsterDie = "res\\sound\\monster_die.wav";
    public static String newBomb = "res\\sound\\newbomb.wav";
    public static String win = "res\\sound\\win.wav";
    public static String backgroundGame = "res\\sound\\2stepfromhell.wav";
    public static String playGame = "res\\sound\\playgame.mid";
    public static String destroy = "res\\sound\\destroy.wav";

    public Sound() {
    }

    public void play(String filePath, int loop) {

        try {
            String path = new File(filePath).toURI().toString();
            MediaPlayer player = new MediaPlayer(new Media(path));
            player.setCycleCount(loop);
            player.seek(Duration.ZERO);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void playDestroy() {
        this.play(destroy, 1);
    }

    public void playBombExplosion() {

        this.play(bombExplosion, 1);
    }

    public void playStartStage() {
        this.play(startStage, 1);
    }

    public void playBomberDie() {
        this.play(bomberDie, 1);
    }

    public void playGetNewItem() {
        this.play(item, 1);
    }

    public void playLose() {
        this.play(lose, 1);
    }

    public void playMenu() {
        this.play(menu, MediaPlayer.INDEFINITE);
    }

    public void playMonsterDie() {
        this.play(monsterDie, 1);
    }

    public void playPlaceNewBomb() {
        this.play(newBomb, 1);

    }

    public void playWin() {
        this.play(win, 1);
    }

    public void playBackGround() {
        this.play(backgroundGame, MediaPlayer.INDEFINITE);
    }
}

