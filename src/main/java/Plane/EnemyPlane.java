package Plane;

import Projectile.EnemyProjectile;
import ActiveActor.ActiveActorDestructible;

/**
 * Represents an enemy plane character in the game.
 * Inherits from FighterPlane and adds specific behaviors and attributes for an enemy plane.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Plane/EnemyPlane.java">
 * GitHub Link</a>
 */
public class EnemyPlane extends FighterPlane {
	private static final String IMAGE_NAME = "Enemies/EnemyJet.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 5;
	private static final double FIRE_RATE = 0.01;

	/**
	 * Constructs an EnemyPlane character.
	 * Initializes position, health, image adjustments, and optionally changes the plane appearance.
	 *
	 * @param initialXPos Initial X-coordinate position of the enemy plane.
	 * @param initialYPos Initial Y-coordinate position of the enemy plane.
	 */
	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		EnemyDifficultyIncrease(INITIAL_HEALTH);
		AdjustImage(150, 150);
		if (Math.random() < 0.5) {
			ChangeEnemy();
		}
	}

	/**
	 * Changes the enemy plane to a different appearance.
	 */
	public void ChangeEnemy() {
		ChangeImage("Enemies/Enemyjet2.png");
	}

	/**
	 * Updates the position of the EnemyPlane by moving it horizontally.
	 */
	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Determines if the EnemyPlane fires a projectile in the current frame based on a random chance.
	 *
	 * @return A `EnemyProjectile` if the EnemyPlane fires, otherwise `null`.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

	/**
	 * Updates the EnemyPlane's position.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}
}
