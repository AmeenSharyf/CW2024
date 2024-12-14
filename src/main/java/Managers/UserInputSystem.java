package Managers;

import Menus.EndingMenu;
import Sound.SoundEffects;
import Plane.UserPlane;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class UserInputSystem {
    private Group root;
    private Scene scene;
    private final UserPlane user;
    private static UserInputSystem userinputsystem;
    private Boolean pressed = false;
    private Boolean pressed2 = false;
    private Boolean pressed3 = true;
    private Timeline time;
    public UserInputSystem(Scene scene, Group root , Timeline timeline) {
        this.time = timeline;
        this.scene = scene;
        this.root = root;
        this.user = UserPlane.getUserPlane();


    }
    public static UserInputSystem getUserInputSystem(Scene scene, Group root , Timeline timeline) {

     if(userinputsystem == null) {

         userinputsystem = new UserInputSystem(scene, root , timeline);
     }else{
         userinputsystem.SetScene(scene);
        userinputsystem.SetRoot(root);
        userinputsystem.SetTime(timeline);


     }
     return userinputsystem;


    }
    private void SetRoot(Group root) {
        this.root = root;
    }
    private void SetScene(Scene scene) {
        this.scene = scene;
    }
    private void SetTime(Timeline timeline) {this.time = timeline;}
    public void InputSystem() {


        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.W) user.moveUp();
                if (kc == KeyCode.S) user.moveDown();
                if (kc == KeyCode.A) user.moveLeft(); // Added left movement
                if (kc == KeyCode.D) user.moveRight(); // Added right movement
                if (kc == KeyCode.SPACE && pressed == false ) {
                    UserProjectileManager.Getuserprojectilemanager(root).fireProjectile();
                    SoundEffects.UserShootSound();
                    pressed = true;
                }
                if (kc == KeyCode.ESCAPE && pressed2 == false) {
                    time.stop();
                    pressed2 = true;
                    EndingMenu.MenuEnding(root);


                }
                if(kc == KeyCode.ESCAPE && pressed3 == false) {
                    time.play();
                    EndingMenu.MenuEnding(root).Resume();
                    pressed3 = true;
                    pressed2 = false;
                }


            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.W || kc == KeyCode.S) user.Verticalstop(); // Stop vertical
                if (kc == KeyCode.A || kc == KeyCode.D) user.Horizontalstop(); // Stop horizontal
                if (kc == KeyCode.SPACE && pressed == true ) {
                    pressed = false;
                }
                if(kc == KeyCode.ESCAPE && pressed2 == true) {

                    pressed3 = false;

                }
            }
        });
    }



}
