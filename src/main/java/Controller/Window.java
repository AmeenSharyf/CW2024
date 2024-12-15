package Controller;

import javafx.stage.Stage;

/**
 * A singleton class for managing the main application window.
 * <p>
 * The `Window` class ensures that there is a single instance of the main application window (`Stage`). It provides
 * access to the stage used for displaying the JavaFX application.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Controller/Window.java">
 * GitHub Link</a>
 */
public class Window {
    /**
     * The single instance of the `Window` class.
     */
    private static Window instance;

    /**
     * The main stage of the JavaFX application.
     */
    private Stage stage;

    /**
     * Private constructor to initialize the window.
     * Creates a new instance of the main application stage.
     */
    private Window() {
        stage = new Stage();
    }

    /**
     * Returns the singleton instance of the `Window`.
     * <p>
     * If the instance does not exist, it is created. Otherwise, the existing instance is returned.
     * </p>
     *
     * @return The singleton instance of the `Window` class.
     */
    public static Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }

    /**
     * Gets the main stage of the application.
     *
     * @return The main {@link Stage} used by the application.
     */
    public Stage getStage() {
        return stage;
    }
}