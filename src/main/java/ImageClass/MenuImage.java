package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class representing a menu background image in a JavaFX application.
 * <p>
 * This class is used to display a background image in the menu screen. It initializes the image with specified position, size, and opacity.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/MenuImage.java">
 * GitHub Link</a>
 */
public class MenuImage extends ImageView {

    /**
     * Constructs a `MenuImage` with the specified parameters.
     *
     * @param PositionX  The x-coordinate position of the image on the screen.
     * @param PositionY  The y-coordinate position of the image on the screen.
     * @param IMAGE_NAME The path to the image resource.
     * @param SizeH      The height to fit the image.
     * @param SizeW      The width to fit the image.
     * @param Opacity    The opacity level for the image, where 0 is fully transparent and 1 is fully opaque.
     */
    public MenuImage(double PositionX, double PositionY, String IMAGE_NAME, double SizeH, double SizeW, double Opacity) {
        setLayoutX(PositionX);
        setLayoutY(PositionY); // Sets the position of the background image

        setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
        setVisible(true);
        setFitHeight(SizeH); // Sets the height of the image
        setFitWidth(SizeW); // Sets the width of the image
        setOpacity(Opacity); // Sets the opacity of the image

        // This class provides a standardized way to handle background images in menus, enabling consistent positioning,
        // sizing, and opacity settings across different menu screens.
    }
}