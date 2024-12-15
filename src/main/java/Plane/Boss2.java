package Plane;

import Projectile.Boss2Projectile;
import ActiveActor.ActiveActorDestructible;
import ImageClass.ShieldImage;

import java.util.*;

/**
 * Represents the second Boss character in the game.
 * Inherits from FighterPlane and adds specific behaviors and attributes for a second boss character.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Plane/Boss2.java">
 * GitHub Link</a>
 */
public class Boss2 extends FighterPlane {
    private static final String IMAGE_NAME = "Enemies/eggmanBoss.png";
    private static final double INITIAL_X_POSITION = 1000.0;
    private static final double INITIAL_Y_POSITION = 400;
    private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
    private static final double BOSS_FIRE_RATE = 0.04;
    private static final double BOSS_SHIELD_PROBABILITY = 0.00;
    private static final int IMAGE_HEIGHT = 300;
    private static final int VERTICAL_VELOCITY = 8;
    private static final int HEALTH = 10;
    private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
    private static final int ZERO = 0;
    private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
    private static final int Y_POSITION_UPPER_BOUND = -100;
    private static final int Y_POSITION_LOWER_BOUND = 475;
    private static final int MAX_FRAMES_WITH_SHIELD = 100;
    private final List<Integer> movePattern;
    private boolean isShielded;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;
    private int framesWithShieldActivated;
    private ShieldImage shieldImage;

    /**
     * Constructs a Boss2 character.
     * Initializes position, health, move pattern, and shield status.
     */
    public Boss2() {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
        movePattern = new ArrayList<>();
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
        framesWithShieldActivated = 0;
        isShielded = false;
        initializeMovePattern();
        shieldImage = new ShieldImage(INITIAL_X_POSITION, INITIAL_Y_POSITION);
        shieldImage.hideShield(); // Initially hide the shield
    }

    /**
     * Updates the position of the Boss2 based on the move pattern.
     */
    @Override
    public void updatePosition() {
        double initialTranslateY = getTranslateY();
        moveVertically(getNextMove());
        double currentPosition = getLayoutY() + getTranslateY();
        if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
            setTranslateY(initialTranslateY);
        }
    }

    /**
     * Updates the state of the Boss2 actor. This includes its position, shield status, and shooting.
     */
    @Override
    public void updateActor() {
        updatePosition();
        updateShield();
        ShieldPosition(); // Synchronize shield position with boss
    }

    /**
     * Calculates the initial position for projectiles fired by the Boss2.
     *
     * @return The Y-coordinate offset for the Boss2's firing position.
     */
    private double getProjectileInitialPosition() {
        return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
    }

    /**
     * Determines if the Boss2 decides to fire a projectile in the current frame.
     *
     * @return A `Boss2Projectile` if the Boss2 fires, otherwise `null`.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        return bossFiresInCurrentFrame() ? new Boss2Projectile(getProjectileInitialPosition()) : null;
    }

    /**
     * Processes damage taken by the Boss2. If the Boss2 is not shielded, it takes damage as usual.
     */
    @Override
    public void takeDamage() {
        if (!isShielded) {
            super.takeDamage();
        }
    }

    /**
     * Initializes the movement pattern for the Boss2 character.
     * The pattern alternates vertical movements to create unpredictable movement behavior.
     */
    private void initializeMovePattern() {
        for (int i = 0; i < MOVE_FREQUENCY_PER_CYCLE; i++) {
            movePattern.add(VERTICAL_VELOCITY);
            movePattern.add(-VERTICAL_VELOCITY);
            movePattern.add(ZERO);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Gets the next movement direction in the move pattern.
     *
     * @return The next movement direction (`VERTICAL_VELOCITY`, `-VERTICAL_VELOCITY`, or `ZERO`).
     */
    private int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == MAX_FRAMES_WITH_SAME_MOVE) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }

    /**
     * Determines if the Boss2 decides to fire a projectile in the current frame.
     *
     * @return `true` if the Boss2 fires, `false` otherwise.
     */
    private boolean bossFiresInCurrentFrame() {
        return Math.random() < BOSS_FIRE_RATE;
    }

    /**
     * Determines if the shield should be activated based on probability.
     *
     * @return `true` if the shield should activate, `false` otherwise.
     */
    private boolean shieldShouldBeActivated() {
        return Math.random() < BOSS_SHIELD_PROBABILITY;
    }

    /**
     * Checks if the shield is exhausted based on frame count.
     *
     * @return `true` if the shield duration has expired, `false` otherwise.
     */
    private boolean shieldExhausted() {
        return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
    }

    /**
     * Activates the Boss2's shield, enabling invincibility for a short period.
     */
    private void activateShield() {
        isShielded = true;
        shieldImage.hideShield(); // Initially hide the shield when activated
    }

    /**
     * Deactivates the Boss2's shield, ending its invincibility.
     */
    private void deactivateShield() {
        isShielded = false;
        framesWithShieldActivated = 0;
        shieldImage.hideShield(); // Hide the shield when deactivated
    }

    /**
     * Updates the Boss2's shield status, activating or deactivating as necessary.
     */
    private void updateShield() {
        if (isShielded) framesWithShieldActivated++;
        else if (shieldShouldBeActivated()) activateShield();
        if (shieldExhausted()) deactivateShield();
    }

    /**
     * Gets the `ShieldImage` associated with this Boss2.
     * Used to synchronize and display the shield with the Boss2 in the scene graph.
     *
     * @return The `ShieldImage` instance.
     */
    public ShieldImage getShieldImage() {
        return shieldImage;
    }

    /**
     * Synchronizes the shield position with the Boss2's current position.
     */
    public void ShieldPosition() {
        shieldImage.setLayoutX(this.getLayoutX() + this.getTranslateX());
        shieldImage.setLayoutY(this.getLayoutY() + this.getTranslateY());
    }
}
