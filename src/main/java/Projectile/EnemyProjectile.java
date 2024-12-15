package Projectile;
/**
 * Represents a projectile fired by an enemy in the game.
 * <p>
 * This class extends from `Projectile` and initializes with a specific image and size. It moves horizontally across
 * the game screen and provides an option to change its image randomly to add visual variety to enemy bullets.
 * The `updatePosition` method updates the position of the projectile by moving it horizontally, while the
 * `updateActor` method updates the state of the projectile each frame.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Projectile/EnemyProjectile.java">
 * GitHub Link</a>
 *
 * @see Projectile
 */
public class EnemyProjectile extends Projectile {

	private static final String IMAGE_NAME = "Projectiles/EnemyBullet.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	/**
	 * Constructor to initialize an `EnemyProjectile` object.
	 *
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		AdjustImage(50, 50); // Adjusts the image size to the specified dimensions
		if(Math.random() < 0.5) {
			ChangeImage("Projectiles/EnemyBullet2.png");
		}
	}

	/**
	 * Updates the position of the projectile by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates the state of the `EnemyProjectile`. Currently, this method calls `updatePosition`.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
