package com.example.demo.controller;

import java.lang.reflect.InvocationTargetException;

import com.example.demo.Menu;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

	protected Controller myController;

	@Override
	public void start(Stage stage) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

       Menu menu = new Menu(stage);
	   menu.MenuConfig();//creates the mainmenu
	   menu.MenuShow();//shows the stage

	}

	public static void main(String[] args) {
		launch();
	}
}