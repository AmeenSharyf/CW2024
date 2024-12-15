package Level;

import ActiveActor.ActiveActorDestructible;
import Plane.EnemyPlane;

/**
 * Represents the first level in the game, LevelOne.
 * <p>
 * This class handles the initialization of the game environment, spawning of enemies, and transition to the next level.
 * </p>
 *
 * <p>
 * <strong>Background:</strong> This level features a specific background image, enemies with a certain spawn probability,
 * and a required kill count to progress to the next level.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelOne.java">
 * GitHub Link</a>
 */
public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background1.jpg";
	private static final String NEXT_LEVEL = "Level.LevelTwo";
	private static final int TOTAL_ENEMIES = 20;
	private static final int KILLS_TO_ADVANCE = 1;
	private static final double ENEMY_SPAWN_PROBABILITY = 0.005;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private boolean flag = false;

	/**
	 * Constructs a new LevelOne instance.
	 *
	 * @param screenHeight The height of the screen.
	 * @param screenWidth  The width of the screen.
	 */
	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the game is over by determining if the user's health is zero or if the kill target has been reached.
	 * If the user is destroyed or has reached the required number of kills, the game progresses to the next level or ends.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed() && !flag) {
			flag = true;
			loseGame();
		} else if (userHasReachedKillTarget() && !flag) {
			flag = true;
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Initializes the friendly units (in this case, the player).
	 * Resets the user's position and health, and adds them to the root.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getUser().ResetPosition();
		getUser().ResetHealth();
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Spawns enemy units based on the defined probability. If the probability condition is met, a new enemy is created and added to the game.
	 */
	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = getCurrentNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new EnemyPlane(getScreenWidth(), newEnemyInitialYPosition);
				addEnemyUnit(newEnemy);
			}
		}
	}

	/**
	 * Creates and returns the view for this level.
	 *
	 * @return A LevelView object representing the visual elements of the level.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
	}

	/**
	 * Checks if the user has reached the kill target defined for this level.
	 *
	 * @return true if the user has reached the kill target; false otherwise.
	 */
	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}
}
