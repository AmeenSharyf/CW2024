package Managers;

import Plane.FighterPlane;
import ActiveActor.ActiveActorDestructible;
import Sound.SoundEffects;
import javafx.scene.Group;

import java.util.List;
import java.util.stream.Collectors;

public class CollisionManager extends ActorManager {
    public static CollisionManager collisionManager;
    private ActorManager actorManager; // Use ActorManager's singleton


    public CollisionManager(Group root,double screenWidth) {
        super(root, screenWidth);
        this.actorManager= ActorManager.GetActorManager(root, screenWidth);

    }
    public static CollisionManager getCollisionManager(Group root, double screenWidth) {
        if (collisionManager == null) {
            return collisionManager = new CollisionManager(root, screenWidth);
        }
        else{
            collisionManager.UpdateCollisionManager(root, screenWidth);
        }
        return collisionManager;
    }
protected void UpdateCollisionManager(Group root, double screenWidth) {
        this.root= root;
        this.screenWidth = screenWidth;
}
      public void generateEnemyFire() {
        actorManager.enemyUnits.stream()
                .map(enemy -> ((FighterPlane) enemy).fireProjectile())
                .forEach(this::spawnEnemyProjectile);

}
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            actorManager.enemyProjectiles.add(projectile);
            SoundEffects.EnemyBullets();
        }
    }

    public void handleCollisions() {
        handleCollisionsBetween(actorManager.friendlyUnits,actorManager.enemyUnits);
        handleCollisionsBetween(actorManager.userProjectileManager.getUserProjectiles(), actorManager.enemyUnits);
        handleCollisionsBetween(actorManager.enemyProjectiles, actorManager.friendlyUnits);
        handleEnemyPenetration();
    }

    private void handleCollisionsBetween(List<ActiveActorDestructible> actors1, List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor1 : actors1) {
            for (ActiveActorDestructible actor2 : actors2) {
                if (actor1.getBoundsInParent().intersects(actor2.getBoundsInParent())) {
                    actor1.takeDamage();
                    actor2.takeDamage();
                }
            }
        }
    }

    private void handleEnemyPenetration() {
        List<ActiveActorDestructible> penetratedEnemies = actorManager.enemyUnits.stream()
                .filter(this::enemyHasPenetratedDefenses)
                .collect(Collectors.toList());

        penetratedEnemies.forEach(enemy -> {
            actorManager.user.takeDamage();
            enemy.destroy();
        });
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > actorManager.screenWidth;
    }
}
