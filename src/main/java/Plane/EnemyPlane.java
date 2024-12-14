package Plane;

import Projectile.EnemyProjectile;
import ActiveActor.ActiveActorDestructible;

public class EnemyPlane extends FighterPlane {

	private static final String IMAGE_NAME = "Enemies/EnemyJet.png";
	private static final int IMAGE_HEIGHT = 150;
	private static final int HORIZONTAL_VELOCITY = -6;
	private static final double PROJECTILE_X_POSITION_OFFSET = -100.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 50.0;
	private static final int INITIAL_HEALTH = 5 ;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH );
		EnemyDifficultyIncrease(INITIAL_HEALTH);
		AdjustImage(150,150);
		if(Math.random() < 0.5) {
			ChangeEnemy();
		}
	}


	public void ChangeEnemy(){
		ChangeImage("Enemies/Enemyjet2.png");
	}


	@Override
	public void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPostion = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPostion);
		}
		return null;
	}

	@Override
	public void updateActor() {
		updatePosition();
	}

}