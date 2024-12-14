package Level;


import ActiveActor.ActiveActorDestructible;
import Plane.Boss2;
import Plane.EnemyPlane;

public class LevelThree extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background3.png";
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private final Boss2 boss;
    private LevelView levelView;
    private static final String NEXT_LEVEL = "Level.LevelFour";
    private boolean flag = false;
    private static final int TOTAL_ENEMIES = 20;

    private static final double ENEMY_SPAWN_PROBABILITY = .005;

    public LevelThree(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss2();
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed() && flag == false) {
            flag = true;
            loseGame();

        }
        else if (boss.isDestroyed()) {
            flag = true;
            getRoot().getChildren().clear();
            goToNextLevel(NEXT_LEVEL);

        }
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            getRoot().getChildren().add(boss.getShieldImage());//will show the image now since its added to the scene
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

    @Override
    protected LevelView instantiateLevelView() {
        levelView = new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
        return levelView;
    }

}