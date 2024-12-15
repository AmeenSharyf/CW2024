package ActiveActor;

import javafx.scene.image.*;

/**
 * Represents an active actor in the game, which extends the {@link ImageView} class.
 * <p>
 * The ActiveActor class serves as a base class for all game entities that have an image,
 * a position, and the ability to update their state or interact with the game environment.
 * </p>
 *Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ActiveActor/ActiveActor.java">
 *GitHub Link</a>
 */
public abstract class ActiveActor extends ImageView {

	/**
	 * The base location for all image resources used by active actors.
	 */
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Constructs a new ActiveActor with the specified image, initial position, and dimensions.
	 *
	 * @param imageName     The name of the image file to use for this actor.
	 * @param imageHeight   The height of the image, in pixels.
	 * @param initialXPos   The initial X-coordinate of the actor.
	 * @param initialYPos   The initial Y-coordinate of the actor.
	 */
	public ActiveActor(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
		this.setLayoutX(initialXPos);
		this.setLayoutY(initialYPos);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Abstract method to update the position of the actor.
	 * <p>
	 * This method should be implemented by subclasses to define the specific logic
	 * for updating the actor's position.
	 * </p>
	 */
	public abstract void updatePosition();

	/**
	 * Changes the image displayed by this actor.
	 *
	 * @param imageName The name of the new image file to display.
	 */
	public void ChangeImage(String imageName) {
		this.setImage(new Image(getClass().getResource(IMAGE_LOCATION + imageName).toExternalForm()));
	}

	/**
	 * Adjusts the dimensions of the actor's image.
	 *
	 * @param height The new height of the image, in pixels.
	 * @param width  The new width of the image, in pixels.
	 */
	public void AdjustImage(int height, int width) {
		this.setFitHeight(height);
		this.setFitWidth(width);
	}

	/**
	 * Moves the actor horizontally by a specified amount.
	 *
	 * @param horizontalMove The amount to move horizontally, in pixels.
	 *                       Positive values move to the right, and negative values move to the left.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves the actor vertically by a specified amount.
	 *
	 * @param verticalMove The amount to move vertically, in pixels.
	 *                     Positive values move downward, and negative values move upward.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}
}
