package ActiveActor;

/**
 * Represents an entity that can take damage and be destroyed.
 * <p>
 * Classes implementing this interface must define the behavior for taking damage
 * and being destroyed, typically to model destructible objects or actors in a game.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ActiveActor/Destructible.java">
 * GitHub Link</a>
 */
public interface Destructible {

	/**
	 * Applies damage to the entity.
	 * <p>
	 * This method should define how the entity reacts to damage, which may
	 * include reducing health, triggering effects, or marking the entity for destruction.
	 * </p>
	 */
	void takeDamage();

	/**
	 * Destroys the entity.
	 * <p>
	 * This method should define the behavior for when the entity is destroyed, such as
	 * removing it from the game environment, playing destruction animations, or freeing resources.
	 * </p>
	 */
	void destroy();
}