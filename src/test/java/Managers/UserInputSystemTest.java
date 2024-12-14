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

public class UserInputSystemTest extends ApplicationTest {

    private UserInputSystem userInputSystem;
    private UserPlane mockUserPlane;
    private Group root;
    private Scene scene;
    private Timeline mockTimeline;
    private UserProjectileManager mockProjectileManager;
    private SoundEffects mockSoundEffects;

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

    @Test
    void testMoveUpOnWKeyPressed() {
        // Simulate W key press
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.W, false, false, false, false);
        scene.getOnKeyPressed().handle(keyEvent);

        // Verify that UserPlane's moveUp method was called
        verify(mockUserPlane, times(1)).moveUp();
        verifyNoMoreInteractions(mockUserPlane);
    }

    @Test
    void testMoveDownOnSKeyPressed() {
        // Simulate S key press
        KeyEvent keyEvent = new KeyEvent(KeyEvent.KEY_PRESSED, "", "", KeyCode.S, false, false, false, false);
        scene.getOnKeyPressed().handle(keyEvent);

        // Verify that UserPlane's moveDown method was called
        verify(mockUserPlane, times(1)).moveDown();
        verifyNoMoreInteractions(mockUserPlane);
    }

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
