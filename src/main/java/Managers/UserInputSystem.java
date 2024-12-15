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

/**
 * Manages user input handling, responding to key presses for player movement and actions.
 * Integrates with the game scene and user plane to provide real-time control.
 *
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Managers/UserInputSystem.java">
 * GitHub Link</a>
 */
public class UserInputSystem {
    private Group root;
    private Scene scene;
    private final UserPlane user;
    private static UserInputSystem userinputsystem;
    private Boolean pressed = false;
    private Boolean pressed2 = false;
    private Boolean pressed3 = true;
    private Timeline time;

    /**
     * Constructs a new `UserInputSystem`.
     *
     * @param scene    The `Scene` where the game is rendered.
     * @param root     The `Group` root node where all game elements are added.
     * @param timeline The `Timeline` instance managing animations and updates.
     */
    public UserInputSystem(Scene scene, Group root, Timeline timeline) {
        this.time = timeline;
        this.scene = scene;
        this.root = root;
        this.user = UserPlane.getUserPlane();
    }

    /**
     * Retrieves the singleton instance of `UserInputSystem`.
     *
     * @param scene    The `Scene` where the game is rendered.
     * @param root     The `Group` root node where all game elements are added.
     * @param timeline The `Timeline` instance managing animations and updates.
     * @return The `UserInputSystem` instance.
     */
    public static UserInputSystem getUserInputSystem(Scene scene, Group root, Timeline timeline) {
        if (userinputsystem == null) {
            userinputsystem = new UserInputSystem(scene, root, timeline);
        } else {
            userinputsystem.SetScene(scene);
            userinputsystem.SetRoot(root);
            userinputsystem.SetTime(timeline);
        }
        return userinputsystem;
    }

    /**
     * Sets the root node of the scene.
     *
     * @param root The new root `Group` to which all actors are added.
     */
    private void SetRoot(Group root) {
        this.root = root;
    }

    /**
     * Sets the scene for handling user input.
     *
     * @param scene The new `Scene` where the game is rendered.
     */
    private void SetScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * Sets the timeline for handling animations and updates.
     *
     * @param timeline The `Timeline` instance managing animations and updates.
     */
    private void SetTime(Timeline timeline) {
        this.time = timeline;
    }

    /**
     * Initializes the input system by setting up event listeners for key presses and releases.
     * Manages user movement and actions like shooting, pausing, and resuming the game.
     */
    public void InputSystem() {
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent e) {
                KeyCode kc = e.getCode();
                if (kc == KeyCode.W) user.moveUp();
                if (kc == KeyCode.S) user.moveDown();
                if (kc == KeyCode.A) user.moveLeft(); // Added left movement
                if (kc == KeyCode.D) user.moveRight(); // Added right movement
                if (kc == KeyCode.SPACE && !pressed) {
                    UserProjectileManager.Getuserprojectilemanager(root).fireProjectile();
                    SoundEffects.UserShootSound();
                    pressed = true;
                }
                if (kc == KeyCode.ESCAPE && !pressed2) {
                    time.stop();
                    pressed2 = true;
                    EndingMenu.MenuEnding(root);
                }
                if (kc == KeyCode.ESCAPE && !pressed3) {
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
                if (kc == KeyCode.W || kc == KeyCode.S) user.Verticalstop(); // Stop vertical movement
                if (kc == KeyCode.A || kc == KeyCode.D) user.Horizontalstop(); // Stop horizontal movement
                if (kc == KeyCode.SPACE && pressed) {
                    pressed = false;
                }
                if (kc == KeyCode.ESCAPE && pressed2) {
                    pressed3 = false;
                }
            }
        });
    }
}
