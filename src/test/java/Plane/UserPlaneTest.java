package Plane;

import ActiveActor.ActiveActorDestructible;
import Projectile.UserProjectile;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the UserPlane class.
 * These tests validate the functionality of the UserPlane class, which represents the player's character in the game.
 * It includes tests for firing projectiles, resetting health, and managing the kill count.
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Plane/UserPlaneTest.java">
 * GitHub Link</a>
 */
public class UserPlaneTest {

    private UserPlane userPlane;

    @Mock
    private ImageView mockImageView;
    @Mock
    private Group mockGroup;

    /**
     * Sets up the JavaFX environment if it hasn't been started already.
     * This method initializes the JavaFX platform to allow interaction with JavaFX components in tests.
     * It is invoked before any tests are run to ensure that JavaFX components can be tested.
     */
    @BeforeAll
    static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * Initializes the UserPlane instance before each test.
     * This sets up the UserPlane singleton and resets its position to the initial state.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userPlane = UserPlane.getUserPlane();
        userPlane.ResetPosition(); // Reset position to initial state
    }

    /**
     * Tests the fireProjectile method of UserPlane.
     * Verifies that firing a projectile returns an instance of UserProjectile.
     */
    @Test
    void testFireProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        assertNotNull(projectile);
        Assert.assertEquals(UserProjectile.class, projectile.getClass());
    }

    /**
     * Tests the ResetHealth method of UserPlane.
     * Sets the health to zero, then resets it and verifies it returns to the initial health value.
     */
    @Test
    void testResetHealth() {
        userPlane.setHealth(0);
        userPlane.ResetHealth();
        Assert.assertEquals(userPlane.getHealth(), 5); // INITIALHEALTH
    }

    /**
     * Tests the set and get methods for the number of kills.
     * Sets a specific number of kills, then retrieves it and verifies the value.
     */
    @Test
    void testSetAndGetNumberOfKills() {
        userPlane.setNumberOfKills(10);
        Assert.assertEquals(userPlane.getNumberOfKills(), 10);
    }

    /**
     * Tests the increment of the kill count.
     * Increments the kill count once and verifies it increments from zero to one.
     */
    @Test
    void testIncrementKillCount() {
        userPlane.incrementKillCount();
        Assert.assertEquals(userPlane.getNumberOfKills(), 1);
    }
}
