package uet.oop.bomberman.sound_effect;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {
    // bgm
    public static final String menuBgm = System.getProperty("user.dir") + "\\res\\sound\\menu.wav";
    public static final String gameBgm = System.getProperty("user.dir") + "\\res\\sound\\bg_sound.wav";

    // game sfx
    public static final String bombExplosion = System.getProperty("user.dir") + "\\res\\sound\\bomb_bang.wav";
    public static final String startStage = System.getProperty("user.dir") + "\\res\\sound\\startstage.wav";
    public static final String bomberDie = System.getProperty("user.dir") + "\\res\\sound\\bomber_die.wav";

    public static final String item = System.getProperty("user.dir") + "\\res\\sound\\item.wav";
    public static final String lose = System.getProperty("user.dir") + "\\res\\sound\\lose.mid";
    public static final String monsterDie = System.getProperty("user.dir") + "\\res\\sound\\monster_die.wav";
    public static final String newBomb = System.getProperty("user.dir") + "\\res\\sound\\newbomb.wav";
    public static final String win = System.getProperty("user.dir") + "\\res\\sound\\win.wav";
    public static final String playGame = System.getProperty("user.dir") + "\\res\\sound\\playgame.mid";

    public final String destroy = System.getProperty("user.dir") + "\\res\\sound\\destroy.wav";
    public static final double DEFAULT_VOLUME = 0.25;
    MediaPlayer audioPlayer;

    public Sound() {
    }

    static final double VOLUME = 1;

    public void play(String filePath, int loop, double volume) {
        try {
            String path = new File(filePath).toURI().toString();
            Media pathFile = new Media(path);
            audioPlayer = new MediaPlayer(pathFile);
            audioPlayer.setVolume(volume);
            audioPlayer.setCycleCount(loop);
            audioPlayer.seek(Duration.ZERO);
            audioPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

