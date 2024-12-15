package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class representing a "Win" image in a JavaFX application.
 * <p>
 * This class is used to display a "You Win!" image on the screen when the player achieves a win condition in the game.
 * The image is initially hidden and can be shown using the provided method.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/WinImage.java">
 * GitHub Link</a>
 */
public class WinImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/UI/HUD/youwin.png"; // Path to the "You Win!" image
	private static final int HEIGHT = 500; // Default height for the "You Win!" image
	private static final int WIDTH = 600; // Default width for the "You Win!" image

	/**
	 * Constructs a `WinImage` at the specified position with default dimensions.
	 *
	 * @param xPosition The x-coordinate position of the "You Win!" image on the screen.
	 * @param yPosition The y-coordinate position of the "You Win!" image on the screen.
	 */
	public WinImage(double xPosition, double yPosition) {
		this.setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		this.setVisible(false); // Win image is hidden by default
		this.setFitHeight(HEIGHT); // Set height of the "You Win!" image
		this.setFitWidth(WIDTH); // Set width of the "You Win!" image
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
	}

	/**
	 * Show the "You Win!" image.
	 */
	public void showWinImage() {
		this.setVisible(true);
	}

	// Additional notes:
	// This class provides a straightforward way to display a "You Win!" image in a JavaFX application.
	// It manages the visibility and positioning of the image on the screen.
}