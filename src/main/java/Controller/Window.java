package Controller;

import javafx.stage.Stage;

public class Window {
    private static Window instance;
    private Stage stage;

    private Window() {
        stage = new Stage();
    }

    public static Window getInstance() {
        if (instance == null) {
            instance = new Window();
        }
        return instance;
    }

    public Stage getStage() {
        return stage;
    }
}