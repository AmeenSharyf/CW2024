package Managers;

import ActiveActor.ActiveActorDestructible;
import Plane.UserPlane;
import javafx.scene.Group;

import java.util.ArrayList;
import java.util.List;
/**
 * Manages the user's projectiles within the game.
 * Handles creation, storage, and rendering of user-fired projectiles.
 * - Stores a list of user projectiles.
 * - Clears all projectiles from the manager.
 * - Fires projectiles from the user's plane and updates the scene.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/ActiveActor/Destructible.java">
 * GitHub Link</a></p>
 */
public class UserProjectileManager {
    private Group root;
    public static UserProjectileManager userprojectilemanager;

    private final List<ActiveActorDestructible> userProjectiles;

    /**
     * Constructs a new `UserProjectileManager`.
     *
     * @param root The `Group` root node where all projectiles are added.
     */
    public UserProjectileManager(Group root) {
        this.root = root;
        this.userProjectiles = new ArrayList<>();
    }

    /**
     * Returns the list of user-fired projectiles.
     *
     * @return A list of `ActiveActorDestructible` representing user projectiles.
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /**
     * Clears all projectiles from the manager.
     */
    public void ResetUserProjectiles() {
        userProjectiles.clear();
    }

    /**
     * Retrieves the singleton instance of `UserProjectileManager`.
     *
     * @param root The `Group` root node where all projectiles are added.
     * @return The `UserProjectileManager` instance.
     *
     * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Managers/UserProjectileManager.java">
     * GitHub Link</a>
     */
    public static UserProjectileManager Getuserprojectilemanager(Group root) {
        if (userprojectilemanager == null) {
            userprojectilemanager = new UserProjectileManager(root);
        } else {
            userprojectilemanager.SetRoot(root);
        }
        return userprojectilemanager;
    }

    /**
     * Sets the root node of the scene.
     *
     * @param root The new `Group` to which all projectiles are added.
     */
    private void SetRoot(Group root) {
        this.root = root;
    }

    /**
     * Fires a projectile from the user's plane and adds it to the scene and the list of user projectiles.
     */
    public void fireProjectile() {
        ActiveActorDestructible projectile = UserPlane.getUserPlane().fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);
    }
}
