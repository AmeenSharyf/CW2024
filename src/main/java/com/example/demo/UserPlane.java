package com.example.demo;

public class UserPlane extends FighterPlane {

	private static final String IMAGE_NAME = "userplane.png";
	private static final double Y_UPPER_BOUND = -40;
	private static final double Y_LOWER_BOUND = 600.0;
	private static final double X_UPPER_BOUND = 500.0;//added upper bound for horizontal movement
	private static final double X_LOWER_BOUND = 000.0;//added lower bound for horizontal movement
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HORIZONTAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION = 110;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 20;
	private static final int PROJECTILE_X_POSITION_OFFSET = 20;
	private int HorizontalvelocityMultiplier;//added horizontal multiplier variable
	private int VerticalvelocityMultiplier;//renamed velocity variable
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		HorizontalvelocityMultiplier = 0;
		VerticalvelocityMultiplier = 0;
	}

	@Override
	public void updatePosition() {//updated the movement system in which added a horizontal movement as well and set its bound
		if (VerticalMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * VerticalvelocityMultiplier);
			double newPositionY = getLayoutY() + getTranslateY();

			if (newPositionY < Y_UPPER_BOUND || newPositionY > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}

		}
		if(HorizontalMoving()) {
			double initialTranslateX = getTranslateX();
			this.moveHorizontally(HORIZONTAL_VELOCITY * HorizontalvelocityMultiplier);
			double newPositionX = getLayoutX() + getTranslateX();
			if(newPositionX > X_UPPER_BOUND || newPositionX < X_LOWER_BOUND) {
				this.setTranslateX(initialTranslateX);
			}

		}

	}

	@Override
	public void updateActor() {
		updatePosition();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET) ,getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean HorizontalMoving() {//new horizontal notstationary
		return HorizontalvelocityMultiplier != 0;
	}
	private boolean VerticalMoving() {//new vertical notstationary
		return VerticalvelocityMultiplier != 0;
	}

	public void moveUp() {
		VerticalvelocityMultiplier = -1;//vertical movement multiplier
	}

	public void moveDown() {
		VerticalvelocityMultiplier = 1;//vertical movement multiplier
	}
	public void moveLeft() {
		HorizontalvelocityMultiplier = -1;//for horizontal movement multiplier
	}
	public void moveRight() {
		HorizontalvelocityMultiplier = 1;//for horizontal movement multiplier
	}

	public void Horizontalstop() {//new horizontal stationary
		HorizontalvelocityMultiplier = 0;
	}
	public void Verticalstop() {//new vertical stationary
		VerticalvelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount() {
		numberOfKills++;
	}

}
