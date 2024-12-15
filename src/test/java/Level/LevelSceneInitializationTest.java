package Level;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test class for `LevelSceneInitialization`.
 * <p>
 * This class provides unit tests for the {@link LevelSceneInitialization} class, including
 * tests for background initialization and timeline setup.
 * </p>
 */
public class LevelSceneInitializationTest {

    /**
     * Ensures that the JavaFX platform is initialized before any tests are run.
     * <p>
     * This is required for running JavaFX-related tests to ensure proper platform setup.
     * </p>
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * Instance of {@link LevelSceneInitialization} used for testing.
     */
    private LevelSceneInitialization levelSceneInitialization;

    /**
     * The root group used to manage scene nodes.
     */
    private Group root;

    /**
     * The height of the screen for the test.
     */
    private double screenHeight = 800.0;

    /**
     * The width of the screen for the test.
     */
    private double screenWidth = 600.0;

    /**
     * Initializes the test environment before each test.
     * <p>
     * Creates a new {@link LevelSceneInitialization} object with a sample background image,
     * screen dimensions, and root group.
     * </p>
     */
    @Before
    public void setUp() {
        root = new Group();
        levelSceneInitialization = new LevelSceneInitialization(
                "/com/example/demo/images/Backgrounds/background4.png",
                screenHeight,
                screenWidth,
                100,
                root
        );
    }

    /**
     * Tests the `initializeBackground` method of {@link LevelSceneInitialization}.
     * <p>
     * Verifies that the background image is correctly initialized with the appropriate
     * dimensions and is added to the root group.
     * </p>
     */
    @Test
    public void testInitializeBackground() {
        levelSceneInitialization.initializeBackground();
        ImageView background = levelSceneInitialization.background;

        // Verify the dimensions of the background image
        assertEquals(screenHeight, background.getFitHeight(), 0.0);
        assertEquals(screenWidth, background.getFitWidth(), 0.0);

        // Verify that the background image is part of the root group's children
        assertTrue(root.getChildren().contains(background));
    }

    /**
     * Tests the `InitializeTimeline` method of {@link LevelSceneInitialization}.
     * <p>
     * Verifies that the timeline is correctly set up with an indefinite cycle count
     * and the specified delay for each keyframe.
     * </p>
     */
    @Test
    public void testInitializeTimeline() {
        Timeline timeline = new Timeline();
        Runnable updateScene = () -> {}; // Mock update scene runnable
        int MILLISECOND_DELAY = 50;

        levelSceneInitialization.InitializeTimeline(updateScene, MILLISECOND_DELAY, timeline);

        // Verify the timeline settings
        assertEquals(Timeline.INDEFINITE, timeline.getCycleCount());
        assertEquals(MILLISECOND_DELAY, timeline.getKeyFrames().get(0).getTime().toMillis(), 0.0);
    }
}
