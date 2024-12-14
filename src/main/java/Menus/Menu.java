
package Menus;

import ImageClass.ButtonImage;
import ImageClass.MenuImage;
import Controller.Controller;
import Controller.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class Menu extends Main {
    private static Menu menu;
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";
    private Group root;
    private Stage stage;
    private Scene scene;

    private static MenuImage background;
    private static ButtonImage StartGameButton, SettingGameButton, ExitGameButton;


    private Menu(Stage stage) {
        Image icon = new Image(getClass().getResource("/com/example/demo/images/MenuRelated/Icon.jpg").toExternalForm());
        stage.getIcons().add(icon);
        this.stage = stage;
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        root = new Group();
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);

        stage.setOnCloseRequest(event -> {
            System.out.println("Window is closing");
            Platform.exit(); // Stop the JavaFX Application thread
            System.exit(0);  // Ensure the JVM exits
        });
    }


    public static Menu getMenu(Stage stage) {
        if (menu == null) {
            menu = new Menu(stage);
        }
        return menu;
    }


    public void UpdateRoot(Group root) {
        this.root = root;
    }


    public void MenuConfig() {
        StackPane stack = createStackPane();
        GridPane grid = createButtonGrid();

        // Add background and buttons to the stack
        stack.getChildren().addAll(background, grid);

        // Add stack to the root
        root.getChildren().add(stack);
    }


    private void initializeUIComponents() {
        if (background == null) {
            background = new MenuImage(0, 0, "/com/example/demo/images/MenuRelated/mainmenu.png", 750, 1300, 1);
        }
        if (StartGameButton == null) {
            StartGameButton = createButton("/com/example/demo/images/MenuRelated/StartButton.png", 250, 200, () -> {
                try {
                    startGame();
                } catch (Exception e) {
                    e.printStackTrace(); // Log the error for debugging
                }
            });
        }
        if (SettingGameButton == null) {
            SettingGameButton = createButton("/com/example/demo/images/MenuRelated/Settings.png", 300, 200, this::settingGame);
        }
        if (ExitGameButton == null) {
            ExitGameButton = createButton("/com/example/demo/images/MenuRelated/Exit.png", 300, 200, this::exitGame);
        }
    }


    private ButtonImage createButton(String imagePath, int width, int height, Runnable onClickAction) {
        ButtonImage button = new ButtonImage(imagePath, width, height);
        button.setOnMouseClicked(event -> {
            try {
                onClickAction.run();
            } catch (Exception e) {
                e.printStackTrace(); // Log the error for debugging
            }
        });

        return button;
    }


    private GridPane createButtonGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(0);
        grid.add(StartGameButton, 0, 0);
        grid.add(SettingGameButton, 0, 1);
        grid.add(ExitGameButton, 0, 2);

        // Set row constraints
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(30));
        grid.gridLinesVisibleProperty().set(false);

        return grid;
    }


    private StackPane createStackPane() {
        initializeUIComponents(); // Ensure components are initialized
        return new StackPane();
    }


    public void startGame() throws Exception {
        myController = new Controller(stage);
        myController.launchGame();
    }


    public void settingGame() {
        System.out.println("SettingGame " + root);
        SettingMenu.GetSettingMenu(root);
        SettingMenu.settingMenu.configureSettings();
    }


    public void exitGame() {
        System.out.println("exit game");
        Platform.exit(); // Shuts down in the standard way, closing open windows and cleaning resources
    }

 
    public void MenuShow() {
        stage.show(); // Shows the MainMenu
    }
}

