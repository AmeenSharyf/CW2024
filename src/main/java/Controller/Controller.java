package Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import Level.LevelParent;
import Level.LevelObserver;

public class Controller implements LevelObserver {

	private static final String LEVEL_ONE_CLASS_NAME = "Level.LevelOne";
	private final Stage stage;

	public Controller(Stage stage) {
		this.stage = stage;
	}

	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	private void goToLevel(String className) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Load the class dynamically
		Class<?> myClass = Class.forName(className);

		// Assume the constructor accepts two double parameters for screen height and width
		Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
		LevelParent myLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

		// Set this controller as the observer for the level
		myLevel.setLevelObserver(this);

		// Initialize the scene and set it to the stage
		Scene scene = myLevel.initializeScene();
		stage.setScene(scene);

		// Start the level

		myLevel.startGame();
	}

	@Override
	public void onLevelChange(String nextLevel) {
		try {
			goToLevel(nextLevel);
		} catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException
				 | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
