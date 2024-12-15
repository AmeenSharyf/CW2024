package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * A class representing an image displayed when the game is over.
 * <p>
 * This class extends {@link ImageView} to display a specific "game over" image at a specified position on the screen.
 * It allows setting the image from a given path and positioning it based on coordinates.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/GameOverImage.java>
 * GitHub Link</a>
 */
public class GameOverImage extends ImageView {

	private static final String IMAGE_NAME = "/com/example/demo/images/UI/HUD/gameover.png";

	/**
	 * Constructs a `GameOverImage` at the specified position.
	 *
	 * @param xPosition The x-coordinate for the image placement.
	 * @param yPosition The y-coordinate for the image placement.
	 */
	public GameOverImage(double xPosition, double yPosition) {
		setImage(new Image(getClass().getResource(IMAGE_NAME).toExternalForm()));
		setLayoutX(xPosition);
		setLayoutY(yPosition);
	}
}
