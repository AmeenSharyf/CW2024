package com.example.demo;

import com.example.demo.controller.Controller;
import com.example.demo.controller.Main;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import java.lang.reflect.InvocationTargetException;

public class Menu extends Main {
    private static final int SCREEN_WIDTH = 1300;
    private static final int SCREEN_HEIGHT = 750;
    private static final String TITLE = "Sky Battle";
    private Group root;
    private Stage stage;
    private Scene scene;



    public Menu (Stage stage){
        Image icon = new Image(getClass().getResource("/com/example/demo/images/Icon.jpg").toExternalForm());//icon image
        stage.getIcons().add(icon);//created icon
        this.stage = stage;
        stage.setTitle(TITLE);
        stage.setResizable(false);
        stage.setHeight(SCREEN_HEIGHT);
        stage.setWidth(SCREEN_WIDTH);
        root= new Group();
        scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT);
        stage.setScene(scene);


    }

public void MenuConfig(){
        StackPane Stack = new StackPane();//using stackpane and gridpane to center buttons more easily
        GridPane grid= new GridPane();

      MenuImage Background= new MenuImage(0,0,"/com/example/demo/images/Background0.jpg",750,1300 );// menu background image

      ButtonImage StartGameButton = new ButtonImage("/com/example/demo/images/StartButton.png",250,200);
      ButtonImage SettingGameButton = new ButtonImage("/com/example/demo/images/Settings.png",300,200);
      ButtonImage ExitGameButton = new ButtonImage("/com/example/demo/images/Exit.png",300,200);//button images
    StartGameButton.setOnMouseClicked(event -> {
        try {
            startGame();
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
        }
    });// enable button functionality  when clicked
      SettingGameButton.setOnMouseClicked(event -> settingGame());// enable button functionality  when clicked (to be changed)
      ExitGameButton.setOnMouseClicked(event -> exitGame());// enable button functionality  when clicked to exit

      grid.setAlignment(Pos.CENTER);//centered the buttons
      grid.add(StartGameButton, 0, 0);// made them into rows
      grid.add(SettingGameButton, 0, 1);
      grid.add(ExitGameButton, 0, 2);
      grid.setVgap(0);//removed any gaps in the rows
      grid.getRowConstraints().add(new RowConstraints(50));//adjusted the row boxes
      grid.getRowConstraints().add(new RowConstraints(50));
      grid.getRowConstraints().add(new RowConstraints(30));
      grid.gridLinesVisibleProperty().set(false);//used this to show visibility when adjusting



      Stack.getChildren().addAll(Background,grid);// added to stack



      root.getChildren().add(Stack); // Add the stack to the root group


}
    private void startGame() throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Starting Game");
        myController = new Controller(stage);
        myController.launchGame();

    }
private void settingGame(){//to be implemented
        System.out.println("Setting game");

}
    private void exitGame(){
        System.out.println("exit game");
        Platform.exit();//shuts down in the standard way closing open windows and cleaning resources


    }
    public void MenuShow(){
        stage.show();//shows the MainMenu

    }

}
//ISSUE to be fixed Ram consumption from high quality images