package Sound;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/**
 * Manages sound effects and background music for the game.
 * <p>
 * This class provides methods to play, pause, and control the volume of background music and sound effects.
 * It uses `MediaPlayer` for background music and `AudioClip` for sound effects. The volume level can be adjusted
 * globally for all sounds through the `setVolume` method.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Sound/SoundEffects.java">
 * GitHub Link</a>
 */
public class SoundEffects {

    private static String mediaPath;
    private static String audioPath;
    private static String audioPath2;
    private static MediaPlayer mediaPlayer;
    private static AudioClip audioClip;
    private static AudioClip audioClip2;
    private static double DefaultVolume = 0.5;

    /**
     * Plays the background music. If no MediaPlayer exists, one is created and started.
     */
    public static void playBackgroundMusic() {
        if (mediaPlayer == null) {
            mediaPath = SoundEffects.class.getResource("/Sound/SoundTrack.mp3").toExternalForm();
            Media media = new Media(mediaPath);
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        }
        mediaPlayer.setVolume(DefaultVolume);
        mediaPlayer.play();
    }

    /**
     * Gets the current volume level.
     *
     * @return The current volume level.
     */
    public static double GetVolume() {
        return DefaultVolume;
    }

    /**
     * Pauses the background music if it is currently playing.
     */
    public static void pauseBackgroundMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    /**
     * Plays the sound effect for the user's shooting action.
     */
    public static void UserShootSound() {
        if (audioPath == null) {
            audioPath = SoundEffects.class.getResource("/Sound/RocketSound.mp3").toExternalForm();
        }
        if (audioClip == null) {
            audioClip = new AudioClip(audioPath);
            audioClip.setVolume(DefaultVolume); // Reducing volume slightly
            audioClip.setCycleCount(1);
        } else {
            audioClip.stop();
            audioClip.play();
        }
    }

    /**
     * Plays the sound effect for enemy bullets.
     */
    public static void EnemyBullets() {
        if (audioPath2 == null) {
            audioPath2 = SoundEffects.class.getResource("/Sound/EnemyBullets.mp3").toExternalForm();
        }
        if (audioClip2 == null) {
            audioClip2 = new AudioClip(audioPath2);
            audioClip2.setVolume(DefaultVolume);
            audioClip2.setCycleCount(1);
        } else {
            audioClip2.stop();
            audioClip2.play();
        }
    }

    /**
     * Sets the volume for all sound effects and background music.
     *
     * @param volume The new volume level to set.
     */
    public static void setVolume(double volume) {
        DefaultVolume = volume;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
        if (audioClip != null) {
            audioClip.setVolume(volume);
        }
        if (audioClip2 != null) {
            audioClip2.setVolume(volume);
        }
    }
}
