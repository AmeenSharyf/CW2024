package Level;

import ActiveActor.ActiveActorDestructible;
import Plane.Boss2;
import Plane.EnemyPlane;
/**
 * Represents the third level of the game, featuring a boss fight and enemy wave spawns.
 * Inherits from `LevelParent` and manages game logic specific to this level, including:
 * - Initialization of friendly units (the player's user unit).
 * - Checking for game over conditions (player's destruction or boss defeat).
 * - Spawning enemies based on a probability function.
 * - Transitioning to the next level upon defeating the boss.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelThree.java">
 * GitHub Link</a>
 */
public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background3.png";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss2 boss;
    private LevelView levelView;
    private static final String NEXT_LEVEL = "Level.LevelFour";
    private boolean flag = false;
    private static final int TOTAL_ENEMIES = 20;

    private static final double ENEMY_SPAWN_PROBABILITY = .005;

    /**
     * Constructs a new `LevelThree` instance.
     *
     * @param screenHeight The height of the screen.
     * @param screenWidth  The width of the screen.
     */
    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss2();
    }

    /**
     * Initializes the friendly units in the level.
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
            flag = true;
            loseGame();
        } else if (boss.isDestroyed()) {
            flag = true;
            getRoot().getChildren().clear();
            goToNextLevel(NEXT_LEVEL);
        }
    }

    /**
     * Spawns enemy units in the level.
     * If no enemies remain, spawns the boss and additional enemies according to the spawn probability.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            getRoot().getChildren().add(boss.getShieldImage()); // Show the boss's shield image now that it's added to the scene
        }
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
     * Creates and returns the view for the level.
     *
     * @return The `LevelView` instance.
     */
    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), getUser().getHealth());
        return levelView;
    }
}
