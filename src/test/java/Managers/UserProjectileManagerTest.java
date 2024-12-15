package Managers;

import ActiveActor.ActiveActorDestructible;
import Plane.UserPlane;
import javafx.application.Platform;
import javafx.scene.Group;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Unit tests for the UserProjectileManager class.
 * This class contains tests that validate the functionality of the UserProjectileManager,
 * which manages the lifecycle and collection of projectiles fired by the user.
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Managers/UserProjectileManagerTest.java">
 * GitHub Link</a>
 */
public class UserProjectileManagerTest {

    /**
     * Initializes the JavaFX platform if it hasn't been started already.
     * This method ensures that the JavaFX environment is ready for testing.
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    private Group root;
    private UserProjectileManager manager;

    /**
     * Sets up the test environment.
     * Initializes the root Group and the UserProjectileManager instance, then fires an initial projectile.
     */
    @Before
    public void setUp() {
        root = new Group();
        manager = UserProjectileManager.Getuserprojectilemanager(root);
        manager.fireProjectile();  // Ensure there is at least one projectile in the list
    }

    /**
     * Tests retrieving the list of user projectiles.
     * Fires another projectile and checks that the list of projectiles is not empty and contains the new projectile.
     */
    @Test
    public void testGetUserProjectiles() {
        // Fire a projectile
        manager.fireProjectile();

        // Add a delay to ensure the projectile is processed (not a best practice but useful for debugging)
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
     * Tests the singleton pattern implementation in UserProjectileManager.
     * Verifies that a new instance of UserProjectileManager with a different root does not create a new instance,
     * but returns the same instance as the original.
     */
    @Test
    public void testSingletonPattern() {
        Group newRoot = new Group();
        UserProjectileManager newManager = UserProjectileManager.Getuserprojectilemanager(newRoot);
        Assert.assertSame(manager, newManager);
    }

    /**
     * Tests the fireProjectile method.
     * Fires a new projectile and verifies that the projectile count increases and the new projectile is different from the initial one.
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
