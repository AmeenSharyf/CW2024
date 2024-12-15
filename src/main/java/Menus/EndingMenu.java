
package Menus;

import ImageClass.ButtonImage;
import ImageClass.MenuImage;
import Controller.Window;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
/**
 * The {@code EndingMenu} class represents the end-game menu of the application.
 * This menu provides options for the user to restart the game, return to the main menu, or exit the game.
 *
 * <p>It is implemented as a singleton to ensure only one instance of the ending menu is active at a time.
 * This class dynamically updates its root group to manage UI transitions effectively.</p>
 *
 *  Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Menus/EndingMenu.java">
 *  GitHub Link</a>
 *
 */


public class EndingMenu {

    /**
     * Root node for the scene graph.
     */
    private Group root;

    /**
     * StackPane containing the menu layout.
     */
    private StackPane stack;

    /**
     * Background image of the ending menu.
     */
    private static MenuImage Background;

    /**
     * Button to return to the main menu.
     */
    private static ButtonImage BacktoMainMenuButton;

    /**
     * Button to restart the game.
     */
    private static ButtonImage RestartGamebutton;

    /**
     * Button to exit the game.
     */
    private static ButtonImage ExitGamebutton;

    /**
     * Singleton instance of the {@code EndingMenu} class.
     */
    private static EndingMenu EndMenu;

    /**
     * Private constructor for {@code EndingMenu} to enforce the singleton pattern.
     *
     * @param root the root node of the current scene graph.
     */
    private EndingMenu(Group root) {
        this.root = root;
        initializeEndingMenu(root);
    }

    /**
     * Returns the singleton instance of {@code EndingMenu}, creating it if necessary.
     *
     * @param root the root node of the current scene graph.
     * @return the singleton instance of {@code EndingMenu}.
     */
    public static EndingMenu MenuEnding(Group root) {
        if (EndMenu == null) {
            EndMenu = new EndingMenu(root);
        } else {
            EndMenu.updateRoot(root);
        }
        return EndMenu;
    }

    /**
     * Initializes the ending menu by setting up the background, buttons, and layout.
     *
     * @param root the root node of the current scene graph.
     */
    private void initializeEndingMenu(Group root) {
        this.root = root;
        StackPane stack = createStackPane();
        GridPane grid = createGridPane();
        this.stack = stack;

        if (Background == null) {
            Background = createBackgroundImage();
        }

        if (RestartGamebutton == null) {
            RestartGamebutton = createButton("/com/example/demo/images/MenuRelated/RestartButton.png", 250, 200, () -> {
                try {
                    RestartGame();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        if (BacktoMainMenuButton == null) {
            BacktoMainMenuButton = createButton("/com/example/demo/images/MenuRelated/Menubutton.png", 300, 200, this::BacktoMainMenu);
        }

        if (ExitGamebutton == null) {
            ExitGamebutton = createButton("/com/example/demo/images/MenuRelated/Exit.png", 300, 200, this::exitGame);
        }

        setupGridPane(grid);
        stack.getChildren().addAll(Background, grid);
        root.getChildren().add(stack);
    }

    /**
     * Updates the root node and reinitializes the ending menu UI.
     *
     * @param root the new root node.
     */
    private void updateRoot(Group root) {
        this.root = root;
        initializeEndingMenu(root);
    }

    /**
     * Clears the current stack contents.
     */
    public void Resume() {
        stack.getChildren().clear();
    }

    /**
     * Restarts the game by clearing the root node and starting the game from the main menu.
     *
     * @throws Exception if an error occurs during the game start process.
     */
    public void RestartGame() throws Exception {
        this.root.getChildren().clear();
        Menu.getMenu(Window.getInstance().getStage()).startGame();
    }

    /**
     * Exits the game application gracefully.
     */
    public void exitGame() {
        Platform.exit();
    }

    /**
     * Returns the user to the main menu by updating the root node.
     */
    public void BacktoMainMenu() {
        this.root.getChildren().clear();
        Menu.getMenu(Window.getInstance().getStage()).UpdateRoot(root);
        Menu.getMenu(Window.getInstance().getStage()).MenuConfig();
    }

    /**
     * Creates a new {@code StackPane} for the menu layout.
     *
     * @return a new {@code StackPane}.
     */
    private StackPane createStackPane() {
        return new StackPane();
    }

    /**
     * Creates a new {@code GridPane} for organizing menu buttons.
     *
     * @return a new {@code GridPane}.
     */
    private GridPane createGridPane() {
        return new GridPane();
    }

    /**
     * Creates the background image for the ending menu.
     *
     * @return a {@code MenuImage} representing the background.
     */
    private MenuImage createBackgroundImage() {
        return new MenuImage(0, 0, "/com/example/demo/images/Backgrounds/Background0.jpg", 750, 1300, 0.5);
    }

    /**
     * Creates a button with the specified image, dimensions, and click action.
     *
     * @param imagePath    the path to the button image.
     * @param width        the width of the button.
     * @param height       the height of the button.
     * @param onClickAction the action to perform when the button is clicked.
     * @return a {@code ButtonImage} object.
     */
    private ButtonImage createButton(String imagePath, int width, int height, Runnable onClickAction) {
        ButtonImage button = new ButtonImage(imagePath, width, height);
        button.setOnMouseClicked(event -> {
            try {
                onClickAction.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return button;
    }

    /**
     * Configures the grid pane layout for menu buttons.
     *
     * @param grid the {@code GridPane} to configure.
     */
    private void setupGridPane(GridPane grid) {
        grid.add(RestartGamebutton, 0, 0);
        grid.add(BacktoMainMenuButton, 0, 1);
        grid.add(ExitGamebutton, 0, 2);

        grid.setAlignment(Pos.CENTER);
        grid.setVgap(1);
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(30));

        grid.gridLinesVisibleProperty().set(false);
    }
}
