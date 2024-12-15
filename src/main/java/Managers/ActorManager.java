package Managers;

import ActiveActor.ActiveActorDestructible;
import Plane.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages all active actors (friendly units, enemy units, projectiles) in the game, handling updates and removal of destroyed actors.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Managers/ActorManager.java">
 * GitHub Link</a>
 */
public class ActorManager {
    protected final UserPlane user;
    protected final UserProjectileManager userProjectileManager;
    protected Group root;
    protected final List<ActiveActorDestructible> friendlyUnits;
    protected final List<ActiveActorDestructible> enemyUnits;
    protected final List<ActiveActorDestructible> enemyProjectiles;
    protected double screenWidth;
    private int currentNumberOfEnemies;
    private static ActorManager actorManager;

    /**
     * Constructs a new `ActorManager`.
     *
     * @param root         The root `Group` to which all actors are added.
     * @param screenWidth  The screen width for managing actors' positions.
     */
    public ActorManager(Group root, double screenWidth) {
        this.screenWidth = screenWidth;
        this.root = root;
        this.user = UserPlane.getUserPlane();
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
        this.userProjectileManager = UserProjectileManager.Getuserprojectilemanager(root);

        this.currentNumberOfEnemies = 0;
        friendlyUnits.add(user);
    }

    /**
     * Retrieves the singleton instance of `ActorManager`.
     *
     * @param root         The root `Group` to which all actors are added.
     * @param screenWidth  The screen width for managing actors' positions.
     * @return The `ActorManager` instance.
     */
    public static ActorManager GetActorManager(Group root, double screenWidth) {
        if (actorManager == null) {
            actorManager = new ActorManager(root, screenWidth);
        } else {
            actorManager.UpdateActorManager(root, screenWidth);
        }

        return actorManager;
    }

    /**
     * Updates the `ActorManager` instance with a new root and screen width.
     *
     * @param root         The new root `Group` to which all actors are added.
     * @param screenWidth  The new screen width for managing actors' positions.
     */
    private void UpdateActorManager(Group root, double screenWidth) {
        this.root = root;
        this.screenWidth = screenWidth;
    }

    /**
     * Clears all lists of actors, resetting the state of the `ActorManager`.
     */
    public void ResetList() {
        friendlyUnits.clear();
        enemyUnits.clear();
        enemyProjectiles.clear();
    }

    /**
     * Adds the user plane to the list of friendly units.
     */
    public void FriendlyActor() {
        friendlyUnits.add(user);
    }

    /**
     * Updates all actors in the game.
     */
    public void updateActors() {
        friendlyUnits.forEach(ActiveActorDestructible::updateActor);
        enemyUnits.forEach(ActiveActorDestructible::updateActor);
        userProjectileManager.getUserProjectiles().forEach(ActiveActorDestructible::updateActor);
        enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    /**
     * Removes all destroyed actors from the game.
     */
    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectileManager.getUserProjectiles());
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Removes destroyed actors from a list and updates the root `Group`.
     *
     * @param actors  The list of actors to check and remove.
     */
    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());

        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Updates the kill count based on the number of destroyed enemies.
     */
    public void updateKillCount() {
        for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
            user.incrementKillCount();
        }
    }

    /**
     * Adds an enemy unit to the game.
     *
     * @param enemy  The `ActiveActorDestructible` instance to add.
     */
    public void addEnemyUnit(ActiveActorDestructible enemy) {
        enemyUnits.add(enemy);
        root.getChildren().add(enemy);
    }

    /**
     * Gets the current number of enemies.
     *
     * @return The size of the `enemyUnits` list.
     */
    public int getCurrentNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Updates the number of enemies managed.
     */
    public void updateNumberOfEnemies() {
        currentNumberOfEnemies = enemyUnits.size();
    }
}
