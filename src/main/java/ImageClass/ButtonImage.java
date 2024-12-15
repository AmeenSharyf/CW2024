package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class representing an image used as a button in the application.
 * <p>
 * This class extends {@link ImageView} and is used to create buttons with images. It provides the functionality to
 * resize the image to a specific height and width, ensuring that the image fits appropriately for buttons in the UI.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/ButtonImage.java">
 * GitHub Link</a>
 */
public class ButtonImage extends ImageView {

    /**
     * Constructs a `ButtonImage` with the specified image name and dimensions.
     *
     * @param IMAGE_NAME The path to the image file used as the button.
     * @param SizeH      The height to which the image should be resized.
     * @param SizeW      The width to which the image should be resized.
     */
    public ButtonImage(String IMAGE_NAME, double SizeH, double SizeW) {
        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH); // Adjust the image size height
        setFitWidth(SizeW);  // Adjust the image size width
        // New image class for buttons made in case new buttons needed to be added and can use this as a reference point.
        // Hence, one class won't have to create everything from scratch whenever a new button is needed to be added.
    }
}
