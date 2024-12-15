package Managers;

import Plane.FighterPlane;
import ActiveActor.ActiveActorDestructible;
import Sound.SoundEffects;
import javafx.scene.Group;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages collision detection and response between different game actors (friendly units, enemy units, projectiles).
 * Inherits from `ActorManager` to access shared actor lists.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Managers/CollisionManager.java">
 * GitHub Link</a>
 */
public class CollisionManager extends ActorManager {
    public static CollisionManager collisionManager;
    private ActorManager actorManager; // Use ActorManager's singleton

    /**
     * Constructs a new `CollisionManager`.
     *
     * @param root         The root `Group` to which all actors are added.
     * @param screenWidth  The screen width for managing actor positions.
     */
    public CollisionManager(Group root, double screenWidth) {
        super(root, screenWidth);
        this.actorManager = ActorManager.GetActorManager(root, screenWidth);
    }

    /**
     * Retrieves the singleton instance of `CollisionManager`.
     *
     * @param root         The root `Group` to which all actors are added.
     * @param screenWidth  The screen width for managing actor positions.
     * @return The `CollisionManager` instance.
     */
    public static CollisionManager getCollisionManager(Group root, double screenWidth) {
        if (collisionManager == null) {
            collisionManager = new CollisionManager(root, screenWidth);
        } else {
            collisionManager.UpdateCollisionManager(root, screenWidth);
        }
        return collisionManager;
    }

    /**
     * Updates the `CollisionManager` instance with a new root and screen width.
     *
     * @param root         The new root `Group` to which all actors are added.
     * @param screenWidth  The new screen width for managing actor positions.
     */
    protected void UpdateCollisionManager(Group root, double screenWidth) {
        this.root = root;
        this.screenWidth = screenWidth;
    }

    /**
     * Generates fire from enemy units (e.g., bullets or projectiles).
     */
    public void generateEnemyFire() {
        actorManager.enemyUnits.stream()
                .map(enemy -> ((FighterPlane) enemy).fireProjectile())
                .forEach(this::spawnEnemyProjectile);
    }

    /**
     * Spawns an enemy projectile and adds it to the game.
     *
     * @param projectile The `ActiveActorDestructible` instance representing the projectile.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            actorManager.enemyProjectiles.add(projectile);
            SoundEffects.EnemyBullets();
        }
    }

    /**
     * Handles all collisions between friendly and enemy units, projectiles, and checks for penetration through defenses.
     */
    public void handleCollisions() {
        handleCollisionsBetween(actorManager.friendlyUnits, actorManager.enemyUnits);
        handleCollisionsBetween(actorManager.userProjectileManager.getUserProjectiles(), actorManager.enemyUnits);
        handleCollisionsBetween(actorManager.enemyProjectiles, actorManager.friendlyUnits);
        handleEnemyPenetration();
    }

    /**
     * Checks for collisions between two lists of actors and responds by causing damage to both.
     *
     * @param actors1 The first list of `ActiveActorDestructible` actors.
     * @param actors2 The second list of `ActiveActorDestructible` actors.
     */
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

    /**
     * Handles the scenario where enemies penetrate the user's defenses.
     */
    private void handleEnemyPenetration() {
        List<ActiveActorDestructible> penetratedEnemies = actorManager.enemyUnits.stream()
                .filter(this::enemyHasPenetratedDefenses)
                .collect(Collectors.toList());

        penetratedEnemies.forEach(enemy -> {
            actorManager.user.takeDamage();
            enemy.destroy();
        });
    }

    /**
     * Checks if an enemy has penetrated the user's defenses based on its position.
     *
     * @param enemy The `ActiveActorDestructible` instance representing the enemy.
     * @return `true` if the enemy has penetrated, otherwise `false`.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy) {
        return Math.abs(enemy.getTranslateX()) > actorManager.screenWidth;
    }
}
