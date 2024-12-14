package Projectile;

public class Boss2Projectile extends Projectile {

    private static final String IMAGE_NAME = "Projectiles/eggmanshoot3.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int HORIZONTAL_VELOCITY = -30;
    private static final int INITIAL_X_POSITION = 950;

    public Boss2Projectile(double initialYPos) {
        super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
    }

    @Override
    public void updatePosition() {
        moveHorizontally(HORIZONTAL_VELOCITY);
    }

    @Override
    public void updateActor() {
        updatePosition();
    }

}
