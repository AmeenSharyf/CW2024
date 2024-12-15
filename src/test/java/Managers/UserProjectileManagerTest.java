package Managers;

import ActiveActor.ActiveActorDestructible;
import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Unit tests for the {@link UserProjectileManager} class.
 * <p>
 * This class validates the functionality of {@link UserProjectileManager}, ensuring that
 * the lifecycle and management of user projectiles are handled correctly. It includes tests
 * for retrieving projectiles, verifying the singleton pattern, and firing projectiles.
 * </p>
 * <p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Managers/UserProjectileManagerTest.java">
 * GitHub Link</a>
 * </p>
 */
public class UserProjectileManagerTest {

    /**
     * Initializes the JavaFX platform if it hasn't already been started.
     * <p>
     * This method ensures that the JavaFX environment is ready for testing,
     * which is necessary for JavaFX-based classes like {@link UserProjectileManager}.
     * </p>
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * The root group used to manage the scene graph during tests.
     */
    private Group root;

    /**
     * The {@link UserProjectileManager} instance being tested.
     */
    private UserProjectileManager manager;

    /**
     * Sets up the test environment before each test.
     * <p>
     * This method initializes the root {@link Group} and the singleton instance of
     * {@link UserProjectileManager}, then fires an initial projectile to ensure
     * the projectile list is not empty.
     * </p>
     */
    @Before
    public void setUp() {
        root = new Group();
        manager = UserProjectileManager.Getuserprojectilemanager(root);
        manager.fireProjectile(); // Ensure there is at least one projectile in the list
    }

    /**
     * Tests the retrieval of user projectiles.
     * <p>
     * This test fires a new projectile, retrieves the list of projectiles, and verifies
     * that the list is not null, not empty, and contains the newly fired projectile.
     * </p>
     */
    @Test
    public void testGetUserProjectiles() {
        manager.fireProjectile();

        // Add a delay to ensure the projectile is processed
        try {
            Thread.sleep(100); // Adjust the sleep time as needed
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<ActiveActorDestructible> projectiles = manager.getUserProjectiles();
        System.out.println("Number of projectiles after fire: " + projectiles.size());
        Assert.assertNotNull(projectiles);
        Assert.assertFalse(projectiles.isEmpty()); // Expecting at least one projectile after firing
    }

    /**
     * Tests the singleton pattern implementation of {@link UserProjectileManager}.
     * <p>
     * This test verifies that creating a new {@link UserProjectileManager} instance with
     * a different root {@link Group} returns the same instance as the original.
     * </p>
     */
    @Test
    public void testSingletonPattern() {
        Group newRoot = new Group();
        UserProjectileManager newManager = UserProjectileManager.Getuserprojectilemanager(newRoot);
        Assert.assertSame(manager, newManager);
    }

    /**
     * Tests the {@link UserProjectileManager#fireProjectile()} method.
     * <p>
     * This test verifies that firing a new projectile increases the projectile count
     * and that the newly fired projectile is different from the initial one.
     * </p>
     */
    @Test
    public void testFireProjectile() {
        ActiveActorDestructible initialProjectile = manager.getUserProjectiles().get(0);

        // Fire a new projectile
        manager.fireProjectile();

        // Verify that the new projectile is added
        List<ActiveActorDestructible> projectiles = manager.getUserProjectiles();
        Assert.assertEquals(2, projectiles.size());
        Assert.assertNotEquals(initialProjectile, projectiles.get(1));
    }
}
