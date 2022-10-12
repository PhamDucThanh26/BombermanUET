package SoundEffect;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

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

    public static void play(String filePath) {

        try {
            String path = new File(filePath).toURI().toString();
            MediaPlayer player = new MediaPlayer(new Media(path));
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void playDestroy() {
        Sound.play(destroy);
    }

    public static void playBombExplosion() {
        Sound.play(bombExplosion);
    }

    public static void playStartStage() {
        Sound.play(startStage);
    }

    public static void playBomberDie() {
        Sound.play(bomberDie);
    }

    public static void playGetNewItem() {
        Sound.play(item);
    }

    public static void playLose() {
        Sound.play(lose);
    }

    public static void playMenu() {
        Sound.play(menu);
    }

    public static void playMonsterDie() {
        Sound.play(monsterDie);
    }

    public static void playPlaceNewBomb() {
        Sound.play(newBomb);

    }

    public static void playWin() {
        Sound.play(win);
    }

    public static void playBackGround() {
        Sound.play(backgroundGame);
    }
}

