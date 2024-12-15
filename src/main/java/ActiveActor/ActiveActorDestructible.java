package ActiveActor;

/**
 * Represents a destructible active actor in the game, extending the {@link ActiveActor} class
 * and implementing the {@link Destructible} interface.
 * <p>
 * This class provides the base implementation for game entities that can be damaged
 * and destroyed. Subclasses must implement specific behavior for updating their state
 * and reacting to damage.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ActiveActor/ActiveActorDestructible.java">
 * GitHub Link</a>
 *
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * Indicates whether the actor is currently destroyed.
	 */
	private boolean isDestroyed;

	/**
	 * Constructs a new destructible active actor with the specified image, initial position, and dimensions.
	 *
	 * @param imageName     The name of the image file to use for this actor.
	 * @param imageHeight   The height of the image, in pixels.
	 * @param initialXPos   The initial X-coordinate of the actor.
	 * @param initialYPos   The initial Y-coordinate of the actor.
	 */
	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
		this.isDestroyed = false;
	}

	/**
	 * Updates the position of the actor.
	 * <p>
	 * This method must be implemented by subclasses to define the specific logic
	 * for updating the actor's position in the game.
	 * </p>
	 */
	@Override
	public abstract void updatePosition();

	/**
	 * Updates the state or behavior of the actor.
	 * <p>
	 * Subclasses must implement this method to define how the actor interacts
	 * with the game environment or updates its internal state.
	 * </p>
	 */
	public abstract void updateActor();

	/**
	 * Applies damage to the actor.
	 * <p>
	 * Subclasses must implement this method to define how the actor reacts to
	 * taking damage and whether it should be destroyed as a result.
	 * </p>
	 */
	@Override
	public abstract void takeDamage();

	/**
	 * Destroys the actor, marking it as destroyed.
	 */
	@Override
	public void destroy() {
		setDestroyed(true);
	}

	/**
	 * Sets the destroyed state of the actor.
	 *
	 * @param isDestroyed {@code true} to mark the actor as destroyed; {@code false} otherwise.
	 */
	protected void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	/**
	 * Checks whether the actor is currently destroyed.
	 *
	 * @return {@code true} if the actor is destroyed; {@code false} otherwise.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
}
