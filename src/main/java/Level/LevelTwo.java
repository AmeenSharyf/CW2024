package Level;

import Plane.Boss;
/**
 * Represents the second level of the game, featuring a boss fight.
 * Inherits from `LevelParent` and manages game logic specific to this level, including:
 * - Initialization of friendly units (the player's user unit).
 * - Checking for game over conditions (player’s destruction or boss defeat).
 * - Spawning the boss as the main enemy unit.
 * - Transitioning to the next level upon defeating the boss.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelTwo.java">
 * GitHub Link</a>
 */
public class LevelTwo extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private final Boss boss;
	private LevelView levelView;
	private static final String NEXT_LEVEL = "Level.LevelThree";
	private boolean flag = false;

	/**
	 * Constructs a new `LevelTwo` instance.
	 *
	 * @param screenHeight The height of the screen.
	 * @param screenWidth  The width of the screen.
	 */
	public LevelTwo(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new Boss();
	}

	/**
	 * Initializes the friendly units in the level by adding the user plane.
	 */
	@Override
	protected void initializeFriendlyUnits() {
		getRoot().getChildren().add(getUser());
	}

	/**
	 * Checks if the game is over by determining if the user is destroyed or the boss is destroyed.
	 * Transitions to the next level if the boss is defeated.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed() && !flag) {
			loseGame();
			flag = true;
		} else if (boss.isDestroyed() && !flag) {
			flag = true;
			getRoot().getChildren().clear();
			goToNextLevel(NEXT_LEVEL);
		}
	}

	/**
	 * Spawns enemy units in the level. In this case, it only spawns the boss.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (getCurrentNumberOfEnemies() == 0) {
			addEnemyUnit(boss);
			getRoot().getChildren().add(boss.getShieldImage());
		}
	}

	/**
	 * Creates and returns the view for the level.
	 *
	 * @return The `LevelView` instance.
	 */
	@Override
	protected LevelView instantiateLevelView() {
		levelView = new LevelView(getRoot(),getUser().getHealth());
		return levelView;
	}
}
