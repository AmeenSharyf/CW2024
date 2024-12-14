package Managers;

import ActiveActor.ActiveActorDestructible;
import Plane.UserPlane;
import javafx.scene.Group;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static ActorManager GetActorManager(Group root, double screenWidth) {
        if (actorManager == null) {
            actorManager = new ActorManager(root, screenWidth);
        }
        else{
            actorManager.UpdateActorManager(root, screenWidth);

        }

        return actorManager;
    }
    private void UpdateActorManager(Group root, double screenWidth) {
        this.root = root;
        this.screenWidth = screenWidth;
    }
    public void ResetList(){
        friendlyUnits.clear();
        enemyUnits.clear();
        enemyProjectiles.clear();
    }
    public void FriendlyActor(){
        friendlyUnits.add(user);
    }


   public void updateActors() {
    friendlyUnits.forEach(ActiveActorDestructible::updateActor);
    enemyUnits.forEach(ActiveActorDestructible::updateActor);
     userProjectileManager.getUserProjectiles().forEach(ActiveActorDestructible::updateActor);
     enemyProjectiles.forEach(ActiveActorDestructible::updateActor);
    }

    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectileManager.getUserProjectiles());
        removeDestroyedActors(enemyProjectiles);
    }

    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream()
                .filter(ActiveActorDestructible::isDestroyed)
                .collect(Collectors.toList());

        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }
    public void updateKillCount() {
       for (int i = 0; i < currentNumberOfEnemies - enemyUnits.size(); i++) {
           user.incrementKillCount();
        }
    }

    public void addEnemyUnit(ActiveActorDestructible enemy) {
       enemyUnits.add(enemy);
       root.getChildren().add(enemy);
    }

    public int getCurrentNumberOfEnemies() {
       return enemyUnits.size();
   }
   public void updateNumberOfEnemies() {
      currentNumberOfEnemies = enemyUnits.size();
  }

}
