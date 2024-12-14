package Level;

import Plane.Boss;
import Plane.Boss2;

public class LevelFour extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/Backgrounds/background4.png";
    private static final int KILLS_TO_ADVANCE = 1;
    private static final int PLAYER_INITIAL_HEALTH = 5;
    private boolean flag = false;
    private final Boss boss;
    private final Boss2 boss2;



    public LevelFour(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        boss = new Boss();
        boss2 = new Boss2();

    }

    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed() && flag == false) {
            flag = true;
            loseGame();
        }
        else if (boss.isDestroyed() && boss2.isDestroyed() && flag == false) {
            flag = true;
            winGame();

        }
    }

    @Override
    protected void initializeFriendlyUnits() {
        getRoot().getChildren().add(getUser());
    }

    @Override
    protected void spawnEnemyUnits() {
        if (getCurrentNumberOfEnemies() == 0) {
            addEnemyUnit(boss);
            addEnemyUnit(boss2);
            getRoot().getChildren().add(boss.getShieldImage());
            getRoot().getChildren().add(boss2.getShieldImage());
        }

    }

    @Override
    protected LevelView instantiateLevelView() {
        return new LevelView(getRoot(), PLAYER_INITIAL_HEALTH);
    }

    private boolean userHasReachedKillTarget() {
        return getUser().getNumberOfKills() >= KILLS_TO_ADVANCE;
    }

}
