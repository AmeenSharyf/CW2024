package Projectile;

public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "Projectiles/EnemyBullet.png";
	private static final int IMAGE_HEIGHT = 50;
	private static final int HORIZONTAL_VELOCITY = -10;

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		AdjustImage(50, 50);
		if(Math.random() < 0.5) {
			ChangeImage("Projectiles/EnemyBullet2.png");
		}

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
