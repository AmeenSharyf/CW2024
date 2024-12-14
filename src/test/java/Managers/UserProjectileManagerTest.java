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

public class UserProjectileManagerTest {

    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    private Group root;
    private UserProjectileManager manager;

    @Before
    public void setUp() {
        root = new Group();
        manager = UserProjectileManager.Getuserprojectilemanager(root);
        manager.fireProjectile();  // Ensure there is at least one projectile in the list
    }

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


    @Test
    public void testSingletonPattern() {
        Group newRoot = new Group();
        UserProjectileManager newManager = UserProjectileManager.Getuserprojectilemanager(newRoot);
        Assert.assertSame(manager, newManager);
    }

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
