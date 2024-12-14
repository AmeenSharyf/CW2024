package Level;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;




public class LevelSceneInitialization {

    private final Group root;
    private final ImageView background;
    private final double screenHeight;
    private final double screenWidth;




    public LevelSceneInitialization(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth,Group root) {
        this.root = root;

        this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;


    }

    public void initializeBackground() {
        background.setFitHeight(screenHeight);
        background.setFitWidth(screenWidth);
        root.getChildren().add(background);
    }

    public void StartGame(Timeline timeline) {
        root.requestFocus();
        timeline.play();
    }
    public void InitializeTimeline(Runnable updateScene,int MILLISECOND_DELAY, Timeline timeline){
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene.run());
        timeline.getKeyFrames().add(gameLoop);

    }


}
