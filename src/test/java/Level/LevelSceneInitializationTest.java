package Level;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Unit tests for the LevelSceneInitialization class.
 * This test class uses JUnit and mocks to simulate the behavior of the LevelSceneInitialization class.
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Level/LevelSceneInitializationTest.java">
 * GitHub Link</a>
*/
public class LevelSceneInitializationTest {

    private LevelSceneInitialization levelSceneInitialization;
    private Group mockRoot;

    private Timeline mockTimeline;

    /**
     * Sets up the JavaFX environment before any tests are run.
     * This method initializes the JavaFX platform if it is not already started.
     */
    @BeforeAll
    static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * Sets up the test environment before each test.
     * Initializes-mocks and creates an instance of LevelSceneInitialization with mock dependencies.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRoot = mock(Group.class);
        mockTimeline = mock(Timeline.class);

        levelSceneInitialization = new LevelSceneInitialization("/com/example/demo/images/Backgrounds/background1.jpg", 800, 600, 100, mockRoot);
    }

    /**
     * Tests the StartGame method of LevelSceneInitialization.
     * Simulates starting the game and verifies that the timeline's play method is called
     * and that the focus is requested from the root group.
     */
    @Test
    void testStartGame() {
        // Call StartGame method
        levelSceneInitialization.StartGame(mockTimeline);

        // Verify if the timeline's play method is called
        verify(mockTimeline, times(1)).play();
        verify(mockRoot, times(1)).requestFocus();
    }

}
