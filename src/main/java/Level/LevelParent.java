package Level;

import ActiveActor.ActiveActorDestructible;
import Managers.ActorManager;
import Managers.CollisionManager;
import Managers.UserInputSystem;
import Menus.EndingMenu;
import Managers.UserProjectileManager;
import Plane.UserPlane;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;

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


	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();


		this.user = UserPlane.getUserPlane();


		this.screenWidth = screenWidth;
		ActorManager actorManagerInitailize = ActorManager.GetActorManager(this.root,this.screenWidth);
        actorManager = actorManagerInitailize;

		this.screenHeight = screenHeight;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;

		LevelSceneInitialization SceneInitalization = new LevelSceneInitialization(backgroundImageName,screenHeight,screenWidth,playerInitialHealth,this.root);

		levelSceneInitialization = SceneInitalization;
		levelSceneInitialization.InitializeTimeline(this::updateScene,MILLISECOND_DELAY,timeline);



		UserProjectileManager userProjectile = UserProjectileManager.Getuserprojectilemanager(this.root);
        this.userProjectile = userProjectile;
		CollisionManager collisionManager = CollisionManager.getCollisionManager(this.root,this.screenWidth);
		this.collisionManager = collisionManager;

		UserInputSystem userinput = UserInputSystem.getUserInputSystem(this.scene,this.root,this.timeline);
		this.userinput = userinput;

	}

	// Observer Setter
	public void setLevelObserver(LevelObserver observer) {
		this.levelObserver = observer;
	}

	protected abstract void initializeFriendlyUnits();

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

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
	public void startGame() {
    levelSceneInitialization.StartGame(timeline);
	}

	public void goToNextLevel(String levelName) {
		timeline.stop(); // Prevents multiple loads
		if (levelObserver != null) {
			levelObserver.onLevelChange(levelName); // Notify observer
		}
	}
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
	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void ResetKillCount() {
		getUser().setNumberOfKills(0);
	}
	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
		EndingMenu.MenuEnding(getRoot());
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
		EndingMenu.MenuEnding(getRoot());
	}

	protected UserPlane getUser() {
		return user.getUserPlane();
	}

	protected Group getRoot() {
		return root;
	}

	protected int getCurrentNumberOfEnemies() {
        return actorManager.getCurrentNumberOfEnemies();
	}

	protected void addEnemyUnit(ActiveActorDestructible enemy) {
	  actorManager.addEnemyUnit(enemy);
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}




}
