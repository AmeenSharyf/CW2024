package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class representing a shield image in a JavaFX application.
 * <p>
 * This class is used to display a shield image on the screen. It initializes the shield with a specified position,
 * size, and visibility status. The shield can be shown or hidden using provided methods.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/ShieldImage.java">
 * GitHub Link</a>
 */
public class ShieldImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/Enemies/shield.png"; // Path to the shield image
	private static final int SHIELD_SIZE = 200; // Default size for the shield image

	/**
	 * Constructs a `ShieldImage` at the specified position with the default shield size.
	 *
	 * @param xPosition The x-coordinate position of the shield on the screen.
	 * @param yPosition The y-coordinate position of the shield on the screen.
	 */
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false); // Shield is hidden by default
		this.setFitHeight(SHIELD_SIZE); // Set height of the shield image
		this.setFitWidth(SHIELD_SIZE); // Set width of the shield image
	}

	/**
	 * Display the shield.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hide the shield.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

	// Additional notes:
	// This class provides an encapsulated way to manage the visibility and appearance of a shield image in a JavaFX application.
	// It is positioned on the screen based on the provided coordinates and has a standard size which can be adjusted if necessary.
}