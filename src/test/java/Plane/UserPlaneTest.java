package Plane;

import javafx.application.Platform;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import static org.junit.Assert.*;
/**
 * Unit tests for the {@link UserPlane} class.
 * <p>
 * This test suite verifies the functionality of the {@link UserPlane} class, including movement,
 * health management, kill count tracking, and the singleton pattern implementation.
 * The tests ensure that the methods of {@link UserPlane} behave as expected.
 * </p>
 * <p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Plane/UserPlaneTest.java">
 * GitHub Link</a>
 * </p>
 */
public class UserPlaneTest {

    /**
     * Ensures that the JavaFX platform is initialized before running tests.
     * <p>
     * This is necessary to test JavaFX-related methods in the {@link UserPlane} class.
     * </p>
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * The {@link UserPlane} instance used in the tests.
     */
    private UserPlane userPlane;

    /**
     * Sets up the test environment before each test.
     * <p>
     * Initializes a new {@link UserPlane} instance with a default health value.
     * </p>
     */
    @Before
    public void setUp() {
        userPlane = new UserPlane(5);
    }

    /**
     * Tests the singleton pattern implementation in {@link UserPlane}.
     * <p>
     * Verifies that multiple calls to {@link UserPlane#getUserPlane()} return the same instance.
     * </p>
     */
    @Test
    public void testGetUserPlane() {
        UserPlane instance1 = UserPlane.getUserPlane();
        UserPlane instance2 = UserPlane.getUserPlane();
        assertSame(instance1, instance2);
    }

    /**
     * Tests the `ResetPosition` method of {@link UserPlane}.
     * <p>
     * Verifies that the position of the plane is reset to its initial values.
     * </p>
     */
    @Test
    public void testResetPosition() {
        userPlane.setTranslateX(100);
        userPlane.setTranslateY(200);
        userPlane.ResetPosition();
        assertEquals(UserPlane.INITIAL_X_POSITION, userPlane.getTranslateX(), 0.0);
        assertEquals(UserPlane.INITIAL_Y_POSITION, userPlane.getTranslateY(), 0.0);
    }

    /**
     * Tests the `ResetHealth` method of {@link UserPlane}.
     * <p>
     * Verifies that the health of the plane is reset to its initial value,
     * and the plane is not destroyed.
     * </p>
     */
    @Test
    public void testResetHealth() {
        userPlane.setHealth(0);
        userPlane.ResetHealth();
        assertEquals(UserPlane.INITIALHEALTH, userPlane.getHealth());
        assertFalse(userPlane.isDestroyed());
    }

    /**
     * Placeholder test for the `fireProjectile` method.
     * <p>
     * Verifies that a projectile is created and initialized with the correct position.
     * Currently commented out because the method is not fully implemented in the codebase.
     * </p>
     */
    @Test
    public void testFireProjectile() {
        // Uncomment when implementation is ready
        // ActiveActorDestructible projectile = userPlane.fireProjectile();
        // assertNotNull(projectile);
        // assertEquals(UserPlane.INITIAL_X_POSITION + UserPlane.PROJECTILE_X_POSITION_OFFSET, projectile.getTranslateX(), 0.0);
        // assertEquals(UserPlane.INITIAL_Y_POSITION + UserPlane.PROJECTILE_Y_POSITION_OFFSET, projectile.getTranslateY(), 0.0);
    }

    /**
     * Tests the horizontal movement of the {@link UserPlane}.
     * <p>
     * Verifies that calling `moveLeft` sets the plane to horizontal moving mode
     * and calling `Horizontalstop` stops horizontal movement.
     * </p>
     */
    @Test
    public void testHorizontalMoving() {
        userPlane.moveLeft();
        assertTrue(userPlane.HorizontalMoving());

        userPlane.Horizontalstop();
        assertFalse(userPlane.HorizontalMoving());
    }

    /**
     * Tests the vertical movement of the {@link UserPlane}.
     * <p>
     * Verifies that calling `moveUp` sets the plane to vertical moving mode
     * and calling `Verticalstop` stops vertical movement.
     * </p>
     */
    @Test
    public void testVerticalMoving() {
        userPlane.moveUp();
        assertTrue(userPlane.VerticalMoving());

        userPlane.Verticalstop();
        assertFalse(userPlane.VerticalMoving());
    }

    /**
     * Tests the `setNumberOfKills` method.
     * <p>
     * Verifies that the kill count of the {@link UserPlane} is correctly updated.
     * </p>
     */
    @Test
    public void testSetNumberOfKills() {
        userPlane.setNumberOfKills(10);
        assertEquals(10, userPlane.getNumberOfKills());
    }

    /**
     * Tests the `incrementKillCount` method.
     * <p>
     * Verifies that the kill count of the {@link UserPlane} is correctly incremented.
     * </p>
     */
    @Test
    public void testIncrementKillCount() {
        userPlane.incrementKillCount();
        assertEquals(1, userPlane.getNumberOfKills());

        userPlane.incrementKillCount();
        assertEquals(2, userPlane.getNumberOfKills());
    }
}
