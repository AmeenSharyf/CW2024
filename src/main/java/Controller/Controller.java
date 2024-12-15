package Controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javafx.scene.Scene;
import javafx.stage.Stage;
import Level.LevelParent;
import Level.LevelObserver;

/**
 * The {@code Controller} class manages the flow of the game, including level transitions and stage setup.
 * <p>
 * This class implements the {@link LevelObserver} interface to handle level changes dynamically. It also
 * uses reflection to load levels at runtime based on their class names, enabling flexibility in managing
 * game stages.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Controller/Controller.java">
 * GitHub Link</a>
 */
public class Controller implements LevelObserver {

	/**
	 * The fully qualified class name of the first level to be launched.
	 */
	private static final String LEVEL_ONE_CLASS_NAME = "Level.LevelOne";

	/**
	 * The primary {@link Stage} for displaying the game.
	 */
	private final Stage stage;

	/**
	 * Constructs a {@code Controller} for managing the game flow on the specified stage.
	 *
	 * @param stage The primary stage where the game is displayed.
	 */
	public Controller(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Launches the game, starting with the first level.
	 * <p>
	 * This method loads the first level dynamically using reflection and initializes it on the stage.
	 * </p>
	 *
	 * @throws ClassNotFoundException         If the class for the first level cannot be found.
	 * @throws NoSuchMethodException          If the expected constructor for the level class is not found.
	 * @throws SecurityException              If a security violation occurs during reflection.
	 * @throws InstantiationException         If the level class cannot be instantiated.
	 * @throws IllegalAccessException         If the constructor for the level class is inaccessible.
	 * @throws IllegalArgumentException       If invalid arguments are passed to the level constructor.
	 * @throws InvocationTargetException      If the level constructor throws an exception.
	 */
	public void launchGame() throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		goToLevel(LEVEL_ONE_CLASS_NAME);
	}

	/**
	 * Navigates to the specified level dynamically.
	 * <p>
	 * This method uses reflection to load the level class, create an instance of it, and display it on the stage.
	 * </p>
	 *
	 * @param className The fully qualified name of the level class.
	 * @throws ClassNotFoundException         If the class for the specified level cannot be found.
	 * @throws NoSuchMethodException          If the expected constructor for the level class is not found.
	 * @throws SecurityException              If a security violation occurs during reflection.
	 * @throws InstantiationException         If the level class cannot be instantiated.
	 * @throws IllegalAccessException         If the constructor for the level class is inaccessible.
	 * @throws IllegalArgumentException       If invalid arguments are passed to the level constructor.
	 * @throws InvocationTargetException      If the level constructor throws an exception.
	 */
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

	/**
	 * Handles level changes by navigating to the specified next level.
	 * <p>
	 * This method is triggered by levels when a transition is required and dynamically loads the next level.
	 * </p>
	 *
	 * @param nextLevel The fully qualified class name of the next level.
	 */
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

