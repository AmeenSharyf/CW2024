package Projectile;
/**
 * Represents a user-controlled projectile in the game.
 * <p>
 * This class extends from `Projectile` and provides the functionality to move horizontally by a specified velocity.
 * It overrides the `updatePosition` method to define how the projectile moves. The `updateActor` method updates the
 * position each frame by calling `updatePosition`.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Projectile/UserProjectile.java">
 * GitHub Link</a>
 *
 * @see Projectile
 */
public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "Projectiles/FriendlyBullet.png";
	private static final int IMAGE_HEIGHT = 125;
	private static final int HORIZONTAL_VELOCITY = 15;

	/**
	 * Constructor to initialize a UserProjectile.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		AdjustImage(50, 50); // Adjust image size, as specified by the project requirements
	}

	/**
	 * Update the position of the projectile by moving it horizontally.
	 * The movement speed is determined by `HORIZONTAL_VELOCITY`.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Update the actor (in this case, the projectile) each frame.
	 * Calls the `updatePosition()` method to update its location.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
