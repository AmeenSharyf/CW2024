package Plane;

import Menus.SettingMenu;
import ActiveActor.ActiveActorDestructible;

/**
 * Represents a fighter plane in the game.
 * This class provides common attributes and behaviors for both the player's plane and enemy planes.
 * It extends from `ActiveActorDestructible`, which is responsible for the base properties and functionalities of game actors.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Plane/FighterPlane.java">
 * GitHub Link</a>
 */
public abstract class FighterPlane extends ActiveActorDestructible {
	private int health;

	/**
	 * Constructs a FighterPlane object.
	 *
	 * @param imageName    The image file name of the plane.
	 * @param imageHeight  The height of the image representing the plane.
	 * @param initialXPos  The initial X-coordinate position of the plane.
	 * @param initialYPos  The initial Y-coordinate position of the plane.
	 * @param health       The initial health of the plane.
	 */
	public FighterPlane(String imageName, int imageHeight, double initialXPos, double initialYPos, int health) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.health = health;
	}

	/**
	 * Fires a projectile from the plane. This method must be implemented by subclasses.
	 *
	 * @return An `ActiveActorDestructible` object representing the fired projectile, or `null` if no projectile is fired.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Reduces the health of the plane by one. If health drops to zero, the plane is destroyed.
	 */
	@Override
	public void takeDamage() {
		health--;
		if (healthAtZero()) {
			this.destroy();
		}
	}

	/**
	 * Calculates the X-coordinate position for the projectile fired by the plane.
	 *
	 * @param xPositionOffset The offset to be added to the plane's current X-coordinate.
	 * @return The X-coordinate for the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the Y-coordinate position for the projectile fired by the plane.
	 *
	 * @param yPositionOffset The offset to be added to the plane's current Y-coordinate.
	 * @return The Y-coordinate for the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Checks if the health of the plane has reached zero.
	 *
	 * @return `true` if health is zero, otherwise `false`.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Sets the health of the plane.
	 *
	 * @param health The new health value.
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Adjusts the difficulty of the plane based on the game's settings menu.
	 * This is usually used to scale the health of the plane according to the game's difficulty level.
	 *
	 * @param health The base health value of the plane.
	 */
	public void EnemyDifficultyIncrease(int health) {
		this.health = health * SettingMenu.getDifficulty();
	}

	/**
	 * Gets the current health of the plane.
	 *
	 * @return The current health value.
	 */
	public int getHealth() {
		return health;
	}
}
