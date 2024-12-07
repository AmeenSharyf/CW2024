package com.example.demo.controller;

import com.example.demo.Menu;
import com.example.demo.Window;
import javafx.application.Application;

import javafx.stage.Stage;


public class Main extends Application {

	protected Controller myController;

	@Override
	public void start(Stage stage) throws  SecurityException, IllegalArgumentException {
		Window win = Window.getInstance();
		stage = win.getStage();
     Menu menu = Menu.getMenu(stage);
	 menu.MenuConfig();
	 menu.MenuShow();



	}

	public static void main(String[] args) {
		launch();
	}
}