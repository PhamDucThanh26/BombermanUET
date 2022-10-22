
package uet.oop.bomberman.SoundEffect;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Sound {
    //
    public final String bombExplosion = System.getProperty("user.dir") + "\\res\\sound\\bomb_bang.wav";
    public final String startStage = System.getProperty("user.dir") + "\\res\\sound\\startstage.wav";
    public final String bomberDie = System.getProperty("user.dir") + "\\res\\sound\\bomber_die.wav";

    public final String item = System.getProperty("user.dir") + "\\res\\sound\\item.wav";
    public final String lose = System.getProperty("user.dir") + "\\res\\sound\\lose.mid";
    public final String menu = System.getProperty("user.dir") + "\\res\\sound\\menu.wav";
    public final String monsterDie = System.getProperty("user.dir") + "\\res\\sound\\monster_die.wav";
    public final String newBomb = System.getProperty("user.dir") + "\\res\\sound\\newbomb.wav";
    public final String win = System.getProperty("user.dir") + "\\res\\sound\\win.wav";
    public final String backgroundGame = System.getProperty("user.dir") + "\\res\\sound\\bg_sound.wav";
    public final String playGame = System.getProperty("user.dir") + "\\res\\sound\\playgame.mid";
    public final String destroy = System.getProperty("user.dir") + "\\res\\sound\\destroy.wav";

    public Sound() {
    }
    static final double VOLUME = 1;

    public void play(String filePath, int loop, double volume ) {

        try {
            String path = new File(filePath).toURI().toString();
            Media pathFile = new Media(path);
            MediaPlayer player = new MediaPlayer(pathFile);
            player.setVolume((double) volume);
            player.setCycleCount(loop);
            player.seek(Duration.ZERO);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void playDestroy() {
        this.play(destroy, 1, VOLUME);
    }

    public void playBombExplosion() {

        this.play(bombExplosion, 1, VOLUME);
    }

    public void playStartStage() {
        this.play(startStage, 1, 0.15);
    }

    public void playBomberDie() {
        this.play(bomberDie, 1, 0.5);
    }

    public void playGetNewItem() {
        this.play(item, 1, 0.125);
    }

    public void playLose() {
        this.play(lose, 1, VOLUME);
    }

    public void playMenu() {
        this.play(menu, MediaPlayer.INDEFINITE, VOLUME);
    }

    public void playMonsterDie() {
        this.play(monsterDie, 1, VOLUME);
    }

    public void playPlaceNewBomb() {
        this.play(newBomb, 1, VOLUME);

    }

    public void playWin() {
        this.play(win, 1, VOLUME);
    }

    public void playBackGround() {
        this.play(backgroundGame, 1000, 0.15);
    }
}

