package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MenuImage extends ImageView {

    public MenuImage(double PositionX, double PositionY,String IMAGE_NAME,double SizeH, double SizeW,double Opacity ) {
        setLayoutX(PositionX);
        setLayoutY(PositionY);//sets the position of the background image

        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH);//sets the height of the image
        setFitWidth(SizeW);//sets the width of the image
        setOpacity(Opacity);

        //new image class for buttons made in case new buttons needed to be added nd can use this as a reference point
        //hence one class wont have to create everything from scratch whenever a new background is needed to be added
    }
}