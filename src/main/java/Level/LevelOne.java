package Level;

import ActiveActor.ActiveActorDestructible;
import Plane.EnemyPlane;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background1.jpg";
	private static final String NEXT_LEVEL = "Level.LevelTwo";
	private static final int TOTAL_ENEMIES = 20;
	private static final int KILLS_TO_ADVANCE = 1;
	private static final double ENEMY_SPAWN_PROBABILITY = .005;
	private static final int PLAYER_INITIAL_HEALTH = 5;
	private boolean flag = false;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH );


	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed() && flag == false) {
			flag = true;
			loseGame();
		}
		else if (userHasReachedKillTarget() && flag == false) {
			flag = true;
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void initializeFriendlyUnits() {

       getUser().ResetPosition();
       getUser().ResetHealth();
	   getRoot().getChildren().add(getUser());
	}

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

	@Override
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(),5);
	}

	private boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
	}

}
