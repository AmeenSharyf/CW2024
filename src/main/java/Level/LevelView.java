package Level;

import ImageClass.GameOverImage;
import ImageClass.HeartDisplay;
import ImageClass.WinImage;
import javafx.scene.Group;

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
	
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	public void showWinImage() {
		winImage.setX(WIN_IMAGE_X_POSITION);
		winImage.setY(WIN_IMAGE_Y_POSITION);

		root.getChildren().add(winImage);
		winImage.showWinImage();
	}
	
	public void showGameOverImage() {
		gameOverImage.setX(LOSS_SCREEN_X_POSITION);
		gameOverImage.setY(LOSS_SCREEN_Y_POSITION);
		root.getChildren().add(gameOverImage);
	}

	public void removeHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
