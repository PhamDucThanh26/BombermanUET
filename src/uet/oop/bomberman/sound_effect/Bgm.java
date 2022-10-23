package uet.oop.bomberman.sound_effect;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Bgm extends Sound {
    private double bgmVolume = Sound.DEFAULT_VOLUME;
    public void startBgm(String path, double volume) {
        try {
            Media media = new Media(new File(path).toURI().toString());
            audioPlayer = new MediaPlayer(media);
            audioPlayer.setVolume(volume);
            audioPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            audioPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopBgm() {
        audioPlayer.stop();
    }

    public void pauseBgm() {
        audioPlayer.pause();
    }

    public void continueBgm() {
        audioPlayer.play();
    }
}
