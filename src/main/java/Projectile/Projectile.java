package Projectile;

import ActiveActor.ActiveActorDestructible;
/**
 * Represents a base class for projectiles in the game.
 * <p>
 * This class extends from `ActiveActorDestructible` and provides basic functionality for projectiles including movement
 * and destruction when hit. The `takeDamage` method is overridden to destroy the projectile when it takes damage.
 * Subclasses must implement the `updatePosition` method to define specific movement patterns for the projectile.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Projectile/Projectile.java">
 * GitHub Link</a>
 *
 * @see ActiveActorDestructible
 */
public abstract class Projectile extends ActiveActorDestructible {

	/**
	 * Constructor to initialize a projectile.
	 *
	 * @param imageName The image file representing the projectile.
	 * @param imageHeight The height of the projectile's image.
	 * @param initialXPos The initial X position of the projectile.
	 * @param initialYPos The initial Y position of the projectile.
	 */
	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

	/**
	 * Destroy the projectile when it takes damage.
	 * This method overrides the `takeDamage()` method in `ActiveActorDestructible`.
	 */
	@Override
	public void takeDamage() {
		this.destroy();
	}

	/**
	 * Updates the position of the projectile.
	 * Must be implemented by subclasses to define specific movement patterns.
	 */
	@Override
	public abstract void updatePosition();
}
