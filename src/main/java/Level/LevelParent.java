package Level;

import ActiveActor.ActiveActorDestructible;
import Managers.ActorManager;
import Managers.CollisionManager;
import Managers.UserInputSystem;
import Menus.EndingMenu;
import Managers.UserProjectileManager;
import Plane.UserPlane;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;

/**
 * Abstract base class for all game levels.
 * <p>
 * This class provides the foundation for implementing specific game levels. It manages the game scene, user interactions, enemy units,
 * collision handling, and transition between levels.
 * </p>
 * <p>
 * Subclasses must implement methods for initializing friendly units, checking game over conditions, spawning enemy units,
 * and creating a level view.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Level/LevelParent.java">
 * GitHub Link</a>
 */
public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;

	private int currentNumberOfEnemies;
	private LevelView levelView;

	private LevelObserver levelObserver; // Replacing Observable

	private LevelSceneInitialization levelSceneInitialization;
	private UserInputSystem userinput;
	private UserProjectileManager userProjectile;
	private ActorManager actorManager;
	private CollisionManager collisionManager;

	/**
	 * Constructs a new LevelParent instance.
	 *
	 * @param backgroundImageName The file path of the background image for the level.
	 * @param screenHeight        The height of the screen.
	 * @param screenWidth         The width of the screen.
	 * @param playerInitialHealth The initial health of the player.
	 */
	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();

		this.user = UserPlane.getUserPlane();

		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		ActorManager actorManagerInitailize = ActorManager.GetActorManager(this.root, this.screenWidth);
		actorManager = actorManagerInitailize;

		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;

		LevelSceneInitialization SceneInitalization = new LevelSceneInitialization(backgroundImageName, screenHeight, screenWidth, playerInitialHealth, this.root);
		levelSceneInitialization = SceneInitalization;
		levelSceneInitialization.InitializeTimeline(this::updateScene, MILLISECOND_DELAY, timeline);

		UserProjectileManager userProjectile = UserProjectileManager.Getuserprojectilemanager(this.root);
		this.userProjectile = userProjectile;
		CollisionManager collisionManager = CollisionManager.getCollisionManager(this.root, this.screenWidth);
		this.collisionManager = collisionManager;

		UserInputSystem userinput = UserInputSystem.getUserInputSystem(this.scene, this.root, this.timeline);
		this.userinput = userinput;
	}

	// Observer Setter
	public void setLevelObserver(LevelObserver observer) {
		this.levelObserver = observer;
	}

	/**
	 * Initializes the friendly units (e.g., the player character).
	 */
	protected abstract void initializeFriendlyUnits();

	/**
	 * Checks if the game is over by evaluating the user's health and determining if the kill target is reached.
	 * If the user is destroyed or has reached the required number of kills, the game progresses to the next level or ends.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Spawns enemy units according to the defined spawning logic.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Instantiates the view for the level, containing the visual elements specific to this level.
	 *
	 * @return A `LevelView` object representing the visual elements of the level.
	 */
	protected abstract LevelView instantiateLevelView();

	/**
	 * Initializes and returns the game scene.
	 *
	 * @return The JavaFX `Scene` object containing the game elements.
	 */
	public Scene initializeScene() {
		ResetKillCount();
		userinput.InputSystem();
		userProjectile.ResetUserProjectiles();
		levelSceneInitialization.initializeBackground();
		actorManager.ResetList();
		actorManager.FriendlyActor();
		initializeFriendlyUnits();
		levelView.showHeartDisplay();
		return scene;
	}

	/**
	 * Starts the game by initiating the timeline which updates the scene at regular intervals.
	 */
	public void startGame() {
		levelSceneInitialization.StartGame(timeline);
	}

	/**
	 * Transitions to the next level by notifying the observer.
	 *
	 * @param levelName The name of the next level.
	 */
	public void goToNextLevel(String levelName) {
		timeline.stop(); // Prevents multiple loads
		if (levelObserver != null) {
			levelObserver.onLevelChange(levelName); // Notify observer
		}
	}

	/**
	 * Updates the game scene, managing spawning, collisions, and game state checks.
	 */
	private void updateScene() {
		spawnEnemyUnits();
		actorManager.updateActors();
		actorManager.updateNumberOfEnemies();
		actorManager.removeAllDestroyedActors();
		actorManager.updateKillCount();
		collisionManager.generateEnemyFire();
		collisionManager.handleCollisions();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Updates the level view to reflect the current game state, such as removing hearts when the user loses health.
	 */
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	/**
	 * Resets the kill count for the player.
	 */
	private void ResetKillCount() {
		getUser().setNumberOfKills(0);
	}

	/**
	 * Handles the event when the player wins the game.
	 */
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		EndingMenu.MenuEnding(getRoot());
	}

	/**
	 * Handles the event when the player loses the game.
	 */
	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		EndingMenu.MenuEnding(getRoot());
	}

	/**
	 * Returns the user plane for the current level.
	 *
	 * @return The `UserPlane` object representing the player.
	 */
	protected UserPlane getUser() {
		return user.getUserPlane();
	}

	/**
	 * Returns the root group of the scene.
	 *
	 * @return The `Group` object representing the root of the scene.
	 */
	protected Group getRoot() {
		return root;
	}

	/**
	 * Returns the current number of enemy units.
	 *
	 * @return The number of current enemies.
	 */
	protected int getCurrentNumberOfEnemies() {
		return actorManager.getCurrentNumberOfEnemies();
	}

	/**
	 * Adds an enemy unit to the scene.
	 *
	 * @param enemy The `ActiveActorDestructible` object representing the enemy.
	 */
	protected void addEnemyUnit(ActiveActorDestructible enemy) {
		actorManager.addEnemyUnit(enemy);
	}

	/**
	 * Returns the maximum Y position for enemy units in this level.
	 *
	 * @return The maximum Y position.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Returns the screen width for the level.
	 *
	 * @return The screen width.
	 */
	protected double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Checks if the user plane is destroyed.
	 *
	 * @return `true` if the user is destroyed; `false` otherwise.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}
}
