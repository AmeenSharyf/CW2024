package Level;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
/**
 * Handles the initialization of the game scene, including setting up the background image and game timeline.
 * - Sets the background image using a specified file path.
 * - Initializes the game timeline for updates at a specified interval.
 * - Starts the game by playing the timeline.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelSceneInitialization.java">
 * GitHub Link</a>
 */
public class LevelSceneInitialization {

    private final Group root;
    final ImageView background;
    private final double screenHeight;
    private final double screenWidth;

    /**
     * Constructs a new `LevelSceneInitialization` instance.
     *
     * @param backgroundImageName The file path of the background image.
     * @param screenHeight        The height of the screen.
     * @param screenWidth         The width of the screen.
     * @param playerInitialHealth The initial health of the player.
     * @param root                The root group of the scene.
     */
    public LevelSceneInitialization(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth, Group root) {
        this.root = root;
        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    /**
     * Initializes the background image for the game scene.
     */
    public void initializeBackground() {
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        root.getChildren().add(background);
    }

    /**
     * Starts the game by focusing on the root group and playing the timeline.
     *
     * @param timeline The timeline that controls the game's updates.
     */
    public void StartGame(Timeline timeline) {
        root.requestFocus();
        timeline.play();
    }

    /**
     * Initializes the game timeline with a specified update interval.
     *
     * @param updateScene       The runnable to update the game scene.
     * @param MILLISECOND_DELAY The interval (in milliseconds) between each update.
     * @param timeline          The timeline to which the key frames will be added.
     */
    public void InitializeTimeline(Runnable updateScene, int MILLISECOND_DELAY, Timeline timeline) {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);
    }
}
