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


public class EndingMenu {

    private Group root;
    private StackPane stack;

    private static MenuImage Background;
    private static ButtonImage BacktoMainMenuButton,RestartGamebutton,ExitGamebutton;

    private static EndingMenu EndMenu;


    private EndingMenu(Group root) {
        this.root = root;
        initializeEndingMenu(root);
    }

    // Singleton
    public static EndingMenu MenuEnding(Group root) {
        if (EndMenu == null) {
            EndMenu = new EndingMenu(root);
        } else {
            EndMenu.updateRoot(root);
        }

        return EndMenu;
    }

    private void initializeEndingMenu(Group root) {
        this.root = root;
        StackPane stack = createStackPane(); // Create the main stack pane
        GridPane grid = createGridPane();   // Create and setup the grid pane
        this.stack = stack;

        if (Background == null) {
            Background = createBackgroundImage();
        }

        if (RestartGamebutton == null) {
            RestartGamebutton = createButton("/com/example/demo/images/MenuRelated/RestartButton.png", 250, 200,  () -> {
                try {
                    RestartGame();
                } catch (Exception e) {
                    e.printStackTrace(); // Log the error for debugging
                }
            });
        }

        if (BacktoMainMenuButton == null) {
            BacktoMainMenuButton = createButton("/com/example/demo/images/MenuRelated/Menubutton.png", 300, 200, this::BacktoMainMenu);
        }
        if(ExitGamebutton == null) {
            ExitGamebutton = createButton("/com/example/demo/images/MenuRelated/Exit.png", 300, 200, this::exitGame);
        }

        setupGridPane(grid);
        stack.getChildren().addAll(Background, grid);
        root.getChildren().add(stack);
    }


    private void updateRoot(Group root) {
        this.root = root;
        initializeEndingMenu(root); // Reinitialize the UI with the same images
    }
    public  void Resume(){
      stack.getChildren().clear();

    }


    public void RestartGame() throws Exception {
        this.root.getChildren().clear();
       Menu.getMenu(Window.getInstance().getStage()).startGame();

    }

    public void exitGame() {
        Platform.exit();
    }

    public void BacktoMainMenu() {
        this.root.getChildren().clear();

        Menu.getMenu(Window.getInstance().getStage()).UpdateRoot(root);
        Menu.getMenu(Window.getInstance().getStage()).MenuConfig();

    }

    private StackPane createStackPane() {
        return new StackPane();
    }

    private GridPane createGridPane() {
        return new GridPane();
    }

    private MenuImage createBackgroundImage() {
        return new MenuImage(0, 0, "/com/example/demo/images/Backgrounds/Background0.jpg", 750, 1300, 0.5);
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
