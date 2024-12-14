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


    public class SettingMenu {
        public static SettingMenu settingMenu;


        private Group root;
        private static MenuImage background;
        private static int difficulty = 1;
        private double volume;
        private StackPane stackPane;
        private static ButtonImage buttonImage;

        public SettingMenu(Group root) {
            this.root = root;


        }
        public static SettingMenu GetSettingMenu(Group root) {
            if(settingMenu == null){
                settingMenu = new SettingMenu(root);

            }
            else{

                settingMenu.updateRoot(root);
            }
            return settingMenu;
        }




         public void ResetSettingMenu() {


        }

        protected void updateRoot(Group newRoot) {
            this.root = newRoot;

        }

        public void configureSettings() {

            // Create background image
if(background == null) {
    background = new MenuImage(200, 250, "/com/example/demo/images/MenuRelated/SettingBackground.png", 300, 500, 1);
}
            // Create GridPane
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.CENTER);

            // Volume Slider
            Slider volumeSlider = createVolumeSlider();

            // Difficulty Slider
            Slider difficultySlider = createDifficultySlider();

            // Labels for sliders
            Text volumeLabel = new Text("Volume");
            Text difficultyLabel = new Text("Difficulty");

            // Back Button
            Button backButton = createBackButton();

            // Add elements to the grid
            grid.add(volumeLabel, 0, 0);
            grid.add(volumeSlider, 0, 1);
            grid.add(difficultyLabel, 0, 2);
            grid.add(difficultySlider, 0, 3);
            grid.add(backButton, 0, 4);
            grid.setGridLinesVisible(false);
            grid.setTranslateX(0);
            grid.setTranslateY(0);

            // Create StackPane
            StackPane stackPane = new StackPane();
            this.stackPane = stackPane;
            this.stackPane.getChildren().clear();
            this.stackPane.getChildren().addAll(background, grid);
            this.stackPane.setTranslateX(400);
            this.stackPane.setTranslateY(250);

            root.getChildren().add(this.stackPane);


        }

        private Slider createVolumeSlider() {
            Slider volumeSlider = new Slider(0, 100, SoundEffects.GetVolume()*100);

            volumeSlider.setShowTickMarks(true);
            volumeSlider.setShowTickLabels(true);
            volumeSlider.setMajorTickUnit(20);
            volumeSlider.getStylesheets().add(getClass().getResource("/CSS/Setting.css").toExternalForm());


            volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
                double volume = newValue.doubleValue()/100.0;
               SoundEffects.setVolume(volume);
                ;

            });

            return volumeSlider;
        }

        private Slider createDifficultySlider() {
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

        private Button createBackButton() {
            Button backButton = new Button();


                if(buttonImage == null) {
                    buttonImage = new ButtonImage("/com/example/demo/images/MenuRelated/BackButton.png", 25, 50);
                }
            backButton.setGraphic(buttonImage);

            backButton.setStyle("-fx-background-color: transparent;");
            backButton.setLayoutX(10);
            backButton.setLayoutY(10);
            backButton.setOnMouseClicked(event -> goBack());
            return backButton;
        }

        private void goBack() {
            this.stackPane.getChildren().clear();

            ResetSettingMenu();

        }
        public static int getDifficulty() {
            return difficulty;
        }

    }