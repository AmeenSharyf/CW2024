package Level;

import Plane.Boss;
import Plane.Boss2;

/**
 * The LevelFour class represents the fourth level of the game.
 * <p>
 * This level is characterized by a specific background image, a set number of kills required to advance, and specific enemy units (Boss and Boss2).
 * The game logic in this level includes checking if the game is over and spawning enemies when necessary.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelFour.java">
 * GitHub Link</a>
 */
public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background4.png"; // Background image for Level Four
    private static final int KILLS_TO_ADVANCE = 1; // Number of kills required to advance to the next level
    private static final int PLAYER_INITIAL_HEALTH = 5; // Initial health of the player in this level
    private boolean flag = false; // Flag to track game state transitions
    private final Boss boss; // The main Boss enemy in this level
    private final Boss2 boss2; // The second Boss enemy in this level

    /**
     * Constructs a LevelFour object.
     *
     * @param screenHeight The height of the screen for this level.
     * @param screenWidth  The width of the screen for this level.
     */
    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss(); // Initialize the Boss enemy
        boss2 = new Boss2(); // Initialize the second Boss enemy
    }

    /**
     * Check if the game is over based on the player's status and enemy statuses.
     * Sets the game to a loss if the player is destroyed, and to a win if both bosses are destroyed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed() && !flag) {
            flag = true;
            loseGame(); // Player is destroyed, trigger game over
        } else if (boss.isDestroyed() && boss2.isDestroyed() && !flag) {
            flag = true;
            winGame(); // Both bosses are destroyed, trigger win
        }
    }

    /**
     * Initialize the friendly units (player) for this level.
     * Adds the player unit to the root of the level.
     */
    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    /**
     * Spawn the enemy units for this level.
     * Adds the Boss and Boss2 units to the level when no enemies are currently present.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss); // Add the Boss enemy to the level
            addEnemyUnit(boss2); // Add the second Boss enemy to the level
            getRoot().getChildren().add(boss.getShieldImage()); // Display the Boss shield
            getRoot().getChildren().add(boss2.getShieldImage()); // Display the second Boss shield
        }
    }

    /**
     * Instantiate the view for the level.
     * Sets up the visual representation of the level, including player health and game elements.
     *
     * @return A new instance of `LevelView` for this level.
     */
    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), getUser().getHealth());
    }

}
