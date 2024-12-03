package com.example.demo;

import com.example.demo.controller.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;


import java.lang.reflect.InvocationTargetException;




public class EndingMenu  {
    private double width;
    private double height;
    private Group root;
    private Scene scene;
    private MenuImage Background;
    private ButtonImage StartGameButton,SettingGameButton;


    private static EndingMenu EndMenu;


    private EndingMenu(Group root) {
        this.root = root;
        StackPane stack = new StackPane();
        GridPane grid = new GridPane();
        Background = new MenuImage(0, 0, "/com/example/demo/images/Background0.jpg", 750, 1300);
         StartGameButton = new ButtonImage("/com/example/demo/images/StartButton.png", 250, 200);
         SettingGameButton = new ButtonImage("/com/example/demo/images/Settings.png", 300, 200);

        StartGameButton.setOnMouseClicked(event -> {
            try {
                startGame(root);
            } catch (Exception e) {
                e.printStackTrace(); // Log the error for debugging
            }
        });// enable button functionality  when clicked
        SettingGameButton.setOnMouseClicked(event -> settingGame(root));// enable button functionality  when clicked (to be changed)


        grid.setAlignment(Pos.CENTER);//centered the buttons
        grid.add(StartGameButton, 0, 0);// made them into rows
        grid.add(SettingGameButton, 0, 1);

        grid.setVgap(0);//removed any gaps in the rows
        grid.getRowConstraints().add(new RowConstraints(50));//adjusted the row boxes
        grid.getRowConstraints().add(new RowConstraints(50));
        grid.getRowConstraints().add(new RowConstraints(30));
        grid.gridLinesVisibleProperty().set(false);//used this to show visibility when adjusting


        stack.getChildren().addAll(Background, grid);// added to stack


        root.getChildren().add(stack); // Add the stack to the root group


    }

    //singleton
    public static EndingMenu MenuEnding(Group root) {
        if (EndMenu == null) {
            System.gc();
            EndMenu = new EndingMenu(root);
        }
        return EndMenu;
    }



    public void startGame(Group root) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        EndMenu = null;
        // Recreate the EndingMenu instance
        root.getChildren().clear();
        Background = null;
        StartGameButton = null;
        SettingGameButton = null;
        Menu Restart = Menu.getMenu(Window.getInstance().getStage());
        Restart.startGame();


        
    }



    public void exitGame() {
        Platform.exit();

    }

    public void settingGame(Group root) {
        EndMenu = null;
        Background = null;
        StartGameButton = null;
        SettingGameButton = null;
        root.getChildren().clear();
        Menu.MenuRestart();
       Menu RestartMenu =  Menu.getMenu(Window.getInstance().getStage());
       RestartMenu.MenuConfig();
       RestartMenu.MenuShow();



    }
}


