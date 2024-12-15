package Plane;

import ActiveActor.ActiveActorDestructible;
import Projectile.UserProjectile;

/**
 * Represents the user's plane in the game.
 * Inherits from `FighterPlane` and implements functionality specific to the player's plane.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Plane/UserPlane.java">
 * GitHub Link</a>
 */
public class UserPlane extends FighterPlane {

	private static UserPlane userPlane;
	private static final String IMAGE_NAME = "User/UserJet.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double X_UPPER_BOUND = 500.0; // Added upper bound for horizontal movement
	private static final double X_LOWER_BOUND = 0.0;   // Added lower bound for horizontal movement
	protected static final double INITIAL_X_POSITION = 5.0;
	protected static final double INITIAL_Y_POSITION = 150.0;
	private static final int IMAGE_HEIGHT = 150;
	protected static final int VERTICAL_VELOCITY = 8;
	protected static final int HORIZONTAL_VELOCITY = 8;
	protected static final int INITIALHEALTH = 5;
	protected static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	protected static final int PROJECTILE_X_POSITION_OFFSET = 20;
	private int HorizontalvelocityMultiplier; // Horizontal velocity multiplier
	private int VerticalvelocityMultiplier; // Vertical velocity multiplier
	private int numberOfKills;

	/**
	 * Constructor to initialize a `UserPlane` object.
	 *
	 * @param initialHealth Initial health of the user plane.
	 */
	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		HorizontalvelocityMultiplier = 0;
		VerticalvelocityMultiplier = 0;
		AdjustImage(150, 150);
	}

	/**
	 * Retrieves the singleton instance of `UserPlane`. If it does not exist, a new instance is created.
	 *
	 * @return The singleton instance of `UserPlane`.
	 */
	public static UserPlane getUserPlane() {
		if (userPlane == null) {
			userPlane = new UserPlane(INITIALHEALTH);
		}
		return userPlane;
	}

	/**
	 * Resets the position of the plane to its initial coordinates.
	 */
	public void ResetPosition() {
		this.setTranslateX(INITIAL_X_POSITION);
		this.setTranslateY(INITIAL_Y_POSITION);
	}

	/**
	 * Resets the health of the plane to its initial value and clears any destroyed state.
	 */
	public void ResetHealth() {
		setHealth(INITIALHEALTH);
		setDestroyed(false);
	}

	/**
	 * Updates the position of the plane based on its current movement multipliers.
	 */
	@Override
	public void updatePosition() {
		// Vertical movement update
		if (VerticalMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * VerticalvelocityMultiplier);
			double newPositionY = getLayoutY() + getTranslateY();
			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}

		// Horizontal movement update
		if (HorizontalMoving()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * HorizontalvelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if (newPositionX > X_UPPER_BOUND || newPositionX < X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}
		}
	}

	/**
	 * Updates the state of the `UserPlane`. Currently, this method calls `updatePosition`.
	 */
	@Override
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Fires a projectile from the user's plane.
	 *
	 * @return A new `UserProjectile` object.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	/**
	 * Checks if the plane is currently moving horizontally.
	 *
	 * @return `true` if the horizontal multiplier is not zero, otherwise `false`.
	 */
    boolean HorizontalMoving() {
		return HorizontalvelocityMultiplier != 0;
	}

	/**
	 * Checks if the plane is currently moving vertically.
	 *
	 * @return `true` if the vertical multiplier is not zero, otherwise `false`.
	 */
    boolean VerticalMoving() {
		return VerticalvelocityMultiplier != 0;
	}

	/**
	 * Sets the plane's vertical velocity multiplier to move up.
	 */
	public void moveUp() {
		VerticalvelocityMultiplier = -1;
	}

	/**
	 * Sets the plane's vertical velocity multiplier to move down.
	 */
	public void moveDown() {
		VerticalvelocityMultiplier = 1;
	}

	/**
	 * Sets the plane's horizontal velocity multiplier to move left.
	 */
	public void moveLeft() {
		HorizontalvelocityMultiplier = -1;
	}

	/**
	 * Sets the plane's horizontal velocity multiplier to move right.
	 */
	public void moveRight() {
		HorizontalvelocityMultiplier = 1;
	}

	/**
	 * Stops the plane from moving horizontally.
	 */
	public void Horizontalstop() {
		HorizontalvelocityMultiplier = 0;
	}

	/**
	 * Stops the plane from moving vertically.
	 */
	public void Verticalstop() {
		VerticalvelocityMultiplier = 0;
	}

	/**
	 * Gets the number of kills made by the user plane.
	 *
	 * @return The number of kills.
	 */
	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Sets the number of kills made by the user plane.
	 *
	 * @param numberOfKills The new number of kills.
	 */
	public void setNumberOfKills(int numberOfKills) {
		this.numberOfKills = numberOfKills;
	}

	/**
	 * Increments the count of kills made by the user plane by one.
	 */
	public void incrementKillCount() {
		numberOfKills++;
	}

	/**
	 * Sets the `UserPlane` instance for testing purposes.
	 *
	 * @param userPlane The `UserPlane` instance to be set.
	 */
	public static void setUserPlane(UserPlane userPlane) {
		UserPlane.userPlane = userPlane;
	}
}
