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

public class UserPlaneTest {

    private UserPlane userPlane;

    @Mock
    private ImageView mockImageView;
    @Mock
    private Group mockGroup;
    @BeforeAll
    static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        userPlane = UserPlane.getUserPlane();
        userPlane.ResetPosition(); // Reset position to initial state
    }


    @Test
    void testFireProjectile() {
        ActiveActorDestructible projectile = userPlane.fireProjectile();
        assertNotNull(projectile);
        Assert.assertEquals(UserProjectile.class, projectile.getClass());
    }

    @Test
    void testResetHealth() {
        userPlane.setHealth(0);
        userPlane.ResetHealth();
        Assert.assertEquals(userPlane.getHealth(), 5); // INITIALHEALTH
    }

    @Test
    void testSetAndGetNumberOfKills() {
        userPlane.setNumberOfKills(10);
        Assert.assertEquals(userPlane.getNumberOfKills(), 10);
    }

    @Test
    void testIncrementKillCount() {
        userPlane.incrementKillCount();
        Assert.assertEquals(userPlane.getNumberOfKills(), 1);
    }
}
