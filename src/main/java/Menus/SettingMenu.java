    package Menus;

    import ImageClass.ButtonImage;
    import ImageClass.MenuImage;
    import Sound.SoundEffects;
    import javafx.geometry.Pos;
    import javafx.scene.Group;
    import javafx.scene.control.Button;
    import javafx.scene.control.Slider;
    import javafx.scene.layout.GridPane;
    import javafx.scene.layout.StackPane;
    import javafx.scene.text.Text;
    /**
     * The {@code SettingMenu} class is responsible for managing the settings menu of the application.
     * It provides functionality for adjusting game settings, such as volume and difficulty,
     * and allows users to navigate back to the main menu.
     * <p>
     * This class implements a singleton pattern to ensure a single instance is used throughout the application.
     * </p>
     *
     *  Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/main/java/Menus/SettingMenu.java">
     *  GitHub Link</a>
     */
    public class SettingMenu {

        /**
         * The singleton instance of the {@code SettingMenu}.
         */
        public static SettingMenu settingMenu;

        /**
         * The root {@code Group} for displaying UI components.
         */
        private Group root;

        /**
         * The background image for the settings menu.
         */
        private static MenuImage background;

        /**
         * The current game difficulty level.
         */
        private static int difficulty = 1;

        /**
         * The volume level, represented as a double.
         */
        private double volume;

        /**
         * The main layout for the settings menu.
         */
        StackPane stackPane;

        /**
         * The button image for navigating back to the main menu.
         */
        private static ButtonImage buttonImage;

        /**
         * Private constructor for {@code SettingMenu} to support the singleton pattern.
         *
         * @param root the {@code Group} to display UI components
         */
        private SettingMenu(Group root) {
            this.root = root;
        }

        /**
         * Returns the singleton instance of {@code SettingMenu}, creating it if it does not exist.
         *
         * @param root the {@code Group} to display UI components
         * @return the singleton instance of {@code SettingMenu}
         */
        public static SettingMenu GetSettingMenu(Group root) {
            if (settingMenu == null) {
                settingMenu = new SettingMenu(root);
            } else {
                settingMenu.updateRoot(root);
            }
            return settingMenu;
        }

        /**
         * Resets the settings menu to its initial state.
         */
        public void ResetSettingMenu() {
            // Implementation for resetting the settings menu
        }

        /**
         * Updates the root {@code Group} with a new {@code Group}.
         *
         * @param newRoot the new {@code Group} to update
         */
        protected void updateRoot(Group newRoot) {
            this.root = newRoot;
        }

        /**
         * Configures the settings menu by initializing and displaying UI components.
         */
        public void configureSettings() {
            if (background == null) {
                background = new MenuImage(200, 250, "/com/example/demo/images/MenuRelated/SettingBackground.png", 300, 500, 1);
            }

            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);

            Slider volumeSlider = createVolumeSlider();
            Slider difficultySlider = createDifficultySlider();
            Text volumeLabel = new Text("Volume");
            Text difficultyLabel = new Text("Difficulty");
            Button backButton = createBackButton();

            grid.add(volumeLabel, 0, 0);
            grid.add(volumeSlider, 0, 1);
            grid.add(difficultyLabel, 0, 2);
            grid.add(difficultySlider, 0, 3);
            grid.add(backButton, 0, 4);

            grid.setGridLinesVisible(false);

            StackPane stackPane = new StackPane();
            this.stackPane = stackPane;
            this.stackPane.getChildren().clear();
            this.stackPane.getChildren().addAll(background, grid);
            this.stackPane.setTranslateX(400);
            this.stackPane.setTranslateY(250);

            root.getChildren().add(this.stackPane);
        }

        /**
         * Creates a volume slider to adjust sound effects volume.
         *
         * @return the {@code Slider} for volume adjustment
         */
        Slider createVolumeSlider() {
            Slider volumeSlider = new Slider(0, 100, SoundEffects.GetVolume() * 100);
            volumeSlider.setShowTickMarks(true);
            volumeSlider.setShowTickLabels(true);
            volumeSlider.setMajorTickUnit(20);
            volumeSlider.getStylesheets().add(getClass().getResource("/CSS/Setting.css").toExternalForm());

            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.doubleValue() / 100.0;
                SoundEffects.setVolume(volume);
            });

            return volumeSlider;
        }

        /**
         * Creates a difficulty slider to adjust game difficulty.
         *
         * @return the {@code Slider} for difficulty adjustment
         */
        Slider createDifficultySlider() {
            Slider difficultySlider = new Slider(1, 3, difficulty);
            difficultySlider.setShowTickMarks(true);
            difficultySlider.setShowTickLabels(true);
            difficultySlider.setMajorTickUnit(1);
            difficultySlider.setMinorTickCount(2);
            difficultySlider.getStylesheets().add(getClass().getResource("/CSS/Setting.css").toExternalForm());

            difficultySlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                difficulty = newValue.intValue();
            });

            return difficultySlider;
        }

        /**
         * Creates a button to navigate back to the main menu.
         *
         * @return the {@code Button} for navigating back
         */
        Button createBackButton() {
            Button backButton = new Button();

            if (buttonImage == null) {
                buttonImage = new ButtonImage("/com/example/demo/images/MenuRelated/BackButton.png", 25, 50);
            }

            backButton.setGraphic(buttonImage);
            backButton.setStyle("-fx-background-color: transparent;");
            backButton.setOnMouseClicked(event -> goBack());
            return backButton;
        }

        /**
         * Handles the logic for navigating back to the main menu.
         */
        void goBack() {
            this.stackPane.getChildren().clear();
            ResetSettingMenu();
        }

        /**
         * Returns the current game difficulty level.
         *
         * @return the difficulty level
         */
        public static int getDifficulty() {
            return difficulty;
        }
    }
