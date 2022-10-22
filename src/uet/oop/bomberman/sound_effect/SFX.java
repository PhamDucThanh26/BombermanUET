package uet.oop.bomberman.sound_effect;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class SFX extends Sound {
    private double sfxVolume = Sound.DEFAULT_VOLUME;
    public void playSFX(String path) {
        try {
            Media media = new Media(new File(path).toURI().toString());
            audioPlayer = new MediaPlayer(media);
            audioPlayer.setVolume(DEFAULT_VOLUME);
            audioPlayer.setCycleCount(1);
            audioPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
