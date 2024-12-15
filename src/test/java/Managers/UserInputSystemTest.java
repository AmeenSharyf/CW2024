package Managers;

import static org.mockito.Mockito.*;

import Plane.UserPlane;
import Sound.SoundEffects;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.animation.Timeline;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Unit tests for the UserInputSystem class.
 * This test class uses TestFX to simulate user inputs and validate the behavior of the UserInputSystem.
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Managers/UserInputSystemTest.java">
 * GitHub Link</a>
 */
public class UserInputSystemTest extends ApplicationTest {

    private UserInputSystem userInputSystem;
    private UserPlane mockUserPlane;
    private Group root;
    private Scene scene;
    private Timeline mockTimeline;
    private UserProjectileManager mockProjectileManager;
    private SoundEffects mockSoundEffects;

    /**
     * Sets up the test environment before each test.
     * Mocks dependencies and initializes the UserInputSystem.
     */
    @BeforeEach
    void setUp() {
        // Mock the dependencies
        mockUserPlane = mock(UserPlane.class);
        root = new Group();
        scene = new Scene(root, 800, 600); // Create a scene for testing
        mockTimeline = mock(Timeline.class);
        mockProjectileManager = mock(UserProjectileManager.class);
        mockSoundEffects = mock(SoundEffects.class);

        // Mock UserPlane static instance
        UserPlane.setUserPlane(mockUserPlane);

        // Create the UserInputSystem
        userInputSystem = new UserInputSystem(scene, root, mockTimeline);

        // Set up the input system
        userInputSystem.InputSystem();
    }

    /**
     * Tests the behavior when the 'W' key is pressed.
     * Simulates a 'W' key press and verifies that the moveUp method of UserPlane is called.
     */
    @Test
    void testMoveUpOnWKeyPressed() {
        // Simulate W key press
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
        scene.getOnKeyPressed().handle(keyEvent);

        // Verify that UserPlane's moveUp method was called
        verify(mockUserPlane, times(1)).moveUp();
        verifyNoMoreInteractions(mockUserPlane);
    }

    /**
     * Tests the behavior when the 'S' key is pressed.
     * Simulates an 'S' key press and verifies that the moveDown method of UserPlane is called.
     */
    @Test
    void testMoveDownOnSKeyPressed() {
        // Simulate S key press
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
        scene.getOnKeyPressed().handle(keyEvent);

        // Verify that UserPlane's moveDown method was called
        verify(mockUserPlane, times(1)).moveDown();
        verifyNoMoreInteractions(mockUserPlane);
    }

    /**
     * Tests the behavior when the 'ESCAPE' key is pressed.
     * Simulates an 'ESCAPE' key press and verifies that the timeline is stopped.
     */
    @Test
    void testPauseOnEscapeKeyPressed() {
        // Simulate ESCAPE key press
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.ESCAPE, false, false, false, false);
        scene.getOnKeyPressed().handle(keyEvent);

        // Verify timeline was stopped
        verify(mockTimeline, times(1)).stop();
        verifyNoMoreInteractions(mockTimeline);
    }
}
