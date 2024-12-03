package com.example.demo;


import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;



public class SoundEffects {

    public SoundEffects() {
        String MediaPath = getClass().getResource("/Music/SoundTrack.mp3").toExternalForm();
        Media media = new Media(MediaPath);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();

        }

    }



