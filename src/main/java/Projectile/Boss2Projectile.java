package Projectile;
/**
 * Represents a projectile fired by the Boss2 in the game.
 * This class extends `Projectile` and manages the specific behavior for this projectile, including:
 * - Initialization with an image, height, initial position, and vertical velocity.
 * - Updating the position of the projectile by moving it horizontally.
 * - Updating the state of the projectile in the game.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Projectile/Boss2Projectile.java">
 * GitHub Link</a>
 */
public class Boss2Projectile extends Projectile {

    private static final String IMAGE_NAME = "Projectiles/eggmanshoot3.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int HORIZONTAL_VELOCITY = -30;
    private static final int INITIAL_X_POSITION = 950;

    /**
     * Constructor to initialize a `Boss2Projectile` object.
     *
     * @param initialYPos The initial Y position of the projectile.
     */
    public Boss2Projectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    /**
     * Updates the position of the projectile by moving it horizontally.
     */
    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    /**
     * Updates the state of the `Boss2Projectile`. Currently, this method calls `updatePosition`.
     */
    @Override
    public void updateActor() {
        updatePosition();
    }
}
