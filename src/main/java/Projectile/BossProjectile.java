package Projectile;
/**
 * Represents a projectile fired by the Boss in the game.
 * This class extends `Projectile` and manages the specific behavior for this projectile, including:
 * - Initialization with an image, height, initial position, and horizontal velocity.
 * - Updating the position of the projectile by moving it horizontally.
 * - Updating the state of the projectile in the game.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Projectile/BossProjectile.java">
 * GitHub Link</a>
 */

public class BossProjectile extends Projectile {

	private static final String IMAGE_NAME = "Projectiles/fireball.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int HORIZONTAL_VELOCITY = -15;
	private static final int INITIAL_X_POSITION = 950;

	/**
	 * Constructor to initialize a `BossProjectile` object.
	 *
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public BossProjectile(double initialYPos) {
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
	 * Updates the state of the `BossProjectile`. Currently, this method calls `updatePosition`.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
