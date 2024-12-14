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

public class LevelSceneInitializationTest {

    private LevelSceneInitialization levelSceneInitialization;
    private Group mockRoot;

    private Timeline mockTimeline;



    @BeforeAll
    static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockRoot = mock(Group.class);
        mockTimeline = mock(Timeline.class);


        levelSceneInitialization = new LevelSceneInitialization("/com/example/demo/images/Backgrounds/background1.jpg", 800, 600, 100, mockRoot);
    }

    @Test
    void testStartGame() {
        // Call StartGame method
        levelSceneInitialization.StartGame(mockTimeline);

        // Verify if the timeline's play method is called
        verify(mockTimeline, times(1)).play();
        verify(mockRoot, times(1)).requestFocus();
    }

}
