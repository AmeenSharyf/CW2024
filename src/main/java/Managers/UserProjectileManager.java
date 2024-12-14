package Managers;

import ActiveActor.ActiveActorDestructible;
import Plane.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;

public class UserProjectileManager {
    private Group root;
    public static UserProjectileManager userprojectilemanager;


    private final List<ActiveActorDestructible> userProjectiles;

    public UserProjectileManager(Group root) {
        this.root = root;
        this.userProjectiles = new ArrayList<>();

    }
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;

    }
    public void ResetUserProjectiles() {
        userProjectiles.clear();

    }

    public static UserProjectileManager Getuserprojectilemanager(Group root) {
        if (userprojectilemanager == null) {
            userprojectilemanager = new UserProjectileManager(root);

        }
        else{
                userprojectilemanager.SetRoot(root);
        }
        return userprojectilemanager;


    }
    private void SetRoot(Group root) {
        this.root = root;
    }



    public void fireProjectile() {

        ActiveActorDestructible projectile = UserPlane.getUserPlane().fireProjectile();
        root.getChildren().add(projectile);
        userProjectiles.add(projectile);

    }



}
