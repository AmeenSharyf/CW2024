package ImageClass;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class for displaying hearts as indicators of life or health.
 * <p>
 * This class creates an HBox container that holds ImageViews of heart images to visually represent the number of lives or health points the player has. The hearts are displayed based on the provided number of hearts to display.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ImageClass/HeartDisplay.java">
 * GitHub Link</a>
 */
public class HeartDisplay {

	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/UI/HUD/heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;
	private HBox container;
	private double containerXPosition;
	private double containerYPosition;
	private int numberOfHeartsToDisplay;

	/**
	 * Constructs a `HeartDisplay` with the specified position and number of hearts to display.
	 *
	 * @param xPosition       The x-coordinate for the heart display container.
	 * @param yPosition       The y-coordinate for the heart display container.
	 * @param heartsToDisplay The number of hearts to display in the container.
	 */
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the HBox container that holds the hearts.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);
	}

	/**
	 * Initializes the hearts in the container based on the specified number of hearts to display.
	 * Each heart is represented by an `ImageView` object, which is added to the HBox.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(getClass().getResource(HEART_IMAGE_NAME).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes one heart from the display.
	 * If there are hearts in the container, the first heart is removed.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Gets the HBox container that holds the heart images.
	 *
	 * @return The HBox container.
	 */
	public HBox getContainer() {
		return container;
	}
}

