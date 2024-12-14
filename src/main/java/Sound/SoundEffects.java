package Sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;

public class SoundEffects {

    private static String mediaPath;
    private static String audioPath;
    private static String audioPath2;
    private static MediaPlayer mediaPlayer;
    private  static AudioClip audioClip;
    private static AudioClip audioClip2;
    private static double DefaultVolume = 0.5 ;





    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            mediaPath = SoundEffects.class.getResource("/Sound/SoundTrack.mp3").toExternalForm();
            Media media = new Media(mediaPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play();
    }
    public static double GetVolume() {
        return DefaultVolume =0.5;
    }

    public static void pauseBackgroundMusic() {
    mediaPlayer.pause();
    }


    public static void UserShootSound() {


        if (audioPath == null) {
            audioPath = SoundEffects.class.getResource("/Sound/RocketSound.mp3").toExternalForm();
        }
        if(audioClip == null ) {
       audioClip = new AudioClip(audioPath);
            audioClip.setVolume(DefaultVolume-0.3);
            audioClip.setCycleCount(1);
        }
        else{

            audioClip.stop();
            audioClip.play();
        }



    }
    public static void EnemyBullets() {


        if (audioPath2 == null) {
            audioPath2 = SoundEffects.class.getResource("/Sound/EnemyBullets.mp3").toExternalForm();
        }
        if(audioClip2 == null) {
            audioClip2 = new AudioClip(audioPath2);
            audioClip2.setVolume(DefaultVolume);
            audioClip2.setCycleCount(1);
        }
        else{

            audioClip2.stop();
            audioClip2.play();
        }


    }
    public static void setVolume(double volume) {
        DefaultVolume = volume;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
        if (audioClip != null) {
            audioClip.setVolume(volume );
        }
        if (audioClip2 != null) {
            audioClip2.setVolume(volume );
        }

    }
    }

