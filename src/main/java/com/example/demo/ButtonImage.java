package com.example.demo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonImage extends ImageView {

    public ButtonImage(String IMAGE_NAME,double SizeH, double SizeW ) {
        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);//adjust the image size height
        setFitWidth(SizeW);//adjust the image size width
        //new image class for buttons made in case new buttons needed to be added nd can use this as a reference point
        //hence one class wont have to create everything from scratch whenever a new button is needed to be added
    }
}
