package Controller;

import Menus.Menu;
import Sound.SoundEffects;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The main entry point for the JavaFX application.
 * <p>
 * This class initializes the game by setting up the primary stage, loading the main menu, and playing the background music.
 * It extends the {@link Application} class, which is the starting point for all JavaFX applications.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Controller/Main.java">
 * GitHub Link</a>
 */
public class Main extends Application {

	/**
	 * The game controller instance, used to manage game state and level transitions.
	 */
	protected Controller myController;

	/**
	 * The main entry point of the JavaFX application.
	 * Initializes the primary stage, sets up the main menu, and starts playing background music.
	 *
	 * @param stage The primary {@link Stage} for the application.
	 * @throws SecurityException        If a security violation occurs.
	 * @throws IllegalArgumentException If an invalid argument is passed to a method.
	 */
	@Override
	public void start(Stage stage) throws SecurityException, IllegalArgumentException {
		// Play the background music
		SoundEffects.playBackgroundMusic();

		// Get the single instance of the window
		Window win = Window.getInstance();
		stage = win.getStage();

		// Get the main menu and configure it
		Menu menu = Menu.getMenu(stage);
		menu.MenuConfig();
		menu.MenuShow();
	}

	/**
	 * The main entry point of the application. Launches the JavaFX application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		launch();
	}
}
