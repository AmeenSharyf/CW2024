package ImageClass;

import javafx.application.Platform;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Test class for `ShieldImage`.
 * <p>
 * This class provides unit tests for the {@link ShieldImage} class, including its initialization
 * and the behavior of showing or hiding the shield.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/ImageClass/ShieldImageTest.java">
 * GitHub Link</a>
 */
public class ShieldImageTest {
    /**
     * An instance of {@link ShieldImage} used for testing.
     */
    private ShieldImage shieldImage;

    /**
     * Initial X-coordinate position for the shield image.
     */
    private static final double INITIAL_X_POSITION = 100;

    /**
     * Initial Y-coordinate position for the shield image.
     */
    private static final double INITIAL_Y_POSITION = 200;

    /**
     * Path to the shield image resource file.
     */
    private static final String IMAGE_NAME = "/com/example/demo/images/Enemies/shield.png";

    /**
     * The size of the shield image in both width and height.
     */
    private static final int SHIELD_SIZE = 200;

    /**
     * Sets up the JavaFX environment before any tests are run.
     * <p>
     * This ensures that the JavaFX platform is initialized if not already active.
     * </p>
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * Initializes the test environment before each test.
     * <p>
     * Creates a new {@link ShieldImage} object with initial X and Y positions.
     * </p>
     */
    @Before
    public void setUp() {
        shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
    }

    /**
     * Tests the initialization of the {@link ShieldImage}.
     * <p>
     * Validates the initial position, visibility, and size of the shield image.
     * </p>
     */
    @Test
    public void testInitialization() {
        assertEquals(INITIAL_X_POSITION, shieldImage.getLayoutX(), 0.001);
        assertEquals(INITIAL_Y_POSITION, shieldImage.getLayoutY(), 0.001);
        assertFalse(shieldImage.isVisible());
        assertEquals(SHIELD_SIZE, shieldImage.getFitHeight(), 0.001);
        assertEquals(SHIELD_SIZE, shieldImage.getFitWidth(), 0.001);
    }

    /**
     * Tests the `showShield` method of the {@link ShieldImage}.
     * <p>
     * Verifies that the shield becomes visible when the method is invoked.
     * </p>
     */
    @Test
    public void testShowShield() {
        shieldImage.showShield();
        assertTrue(shieldImage.isVisible());
    }

    /**
     * Tests the `hideShield` method of the {@link ShieldImage}.
     * <p>
     * Verifies that the shield becomes hidden when the method is invoked.
     * </p>
     */
    @Test
    public void testHideShield() {
        shieldImage.hideShield();
        assertFalse(shieldImage.isVisible());
    }
}
