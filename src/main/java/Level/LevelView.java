package Level;

import ImageClass.GameOverImage;
import ImageClass.HeartDisplay;
import ImageClass.WinImage;
import javafx.scene.Group;

/**
 * Represents the view for a level in the game, managing the display of elements like health, win/lose screens.
 * - Displays health through `HeartDisplay`.
 * - Manages the win/lose screens using `WinImage` and `GameOverImage`.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelView.java">
 * GitHub Link</a>
 */
public class LevelView {

	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;
	private static final int WIN_IMAGE_X_POSITION = 275;
	private static final int WIN_IMAGE_Y_POSITION = 0;
	private static final int LOSS_SCREEN_X_POSITION = 275;
	private static final int LOSS_SCREEN_Y_POSITION = 0;
	private final Group root;
	private final WinImage winImage;
	private final GameOverImage gameOverImage;
	private final HeartDisplay heartDisplay;

	/**
	 * Constructs a new `LevelView` instance.
	 *
	 * @param root             The root group to which elements are added.
	 * @param heartsToDisplay  The number of hearts to display for the player's health.
	 */
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
		this.winImage = new WinImage(WIN_IMAGE_X_POSITION, WIN_IMAGE_Y_POSITION);
		this.gameOverImage = new GameOverImage(LOSS_SCREEN_X_POSITION, LOSS_SCREEN_Y_POSITION);
		this.gameOverImage.preserveRatioProperty().set(true);
		this.gameOverImage.setFitWidth(200); // Set width to cover the screen
		this.gameOverImage.setFitHeight(200); // Set height to cover the screen
		this.winImage.setFitWidth(200);
		this.winImage.setFitHeight(200);
	}

	/**
	 * Displays the heart display for the player's health.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Displays the win image on the screen.
	 */
	public void showWinImage() {
		winImage.setX(WIN_IMAGE_X_POSITION);
		winImage.setY(WIN_IMAGE_Y_POSITION);
		root.getChildren().add(winImage);
		winImage.showWinImage();
	}

	/**
	 * Displays the game over image on the screen.
	 */
	public void showGameOverImage() {
		gameOverImage.setX(LOSS_SCREEN_X_POSITION);
		gameOverImage.setY(LOSS_SCREEN_Y_POSITION);
		root.getChildren().add(gameOverImage);
	}

	/**
	 * Removes hearts from the display as the player's health decreases.
	 *
	 * @param heartsRemaining The number of hearts remaining after a health reduction.
	 */
	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}
}
