package Menus;


import ImageClass.MenuImage;
import Sound.SoundEffects;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Unit tests for the {@link SettingMenu} class.
 * <p>
 * This class verifies the functionality of the {@link SettingMenu}, including singleton behavior,
 * UI component creation, and configuration of settings such as volume and difficulty.
 * </p>
 * <p>
 * The tests ensure that the `SettingMenu` behaves as expected and validates the integration of its
 * components, such as sliders, buttons, and settings configuration.
 * </p>
 * <p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Menus/SettingMenuTest.java">
 * GitHub Link</a>
 * </p>
 */
public class SettingMenuTest {

    /**
     * Ensures that the JavaFX platform is initialized before running tests.
     * <p>
     * This is necessary for testing JavaFX components in the {@link SettingMenu}.
     * </p>
     */
    @BeforeClass
    public static void setupJavaFX() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {}); // Initialize JavaFX platform if not already initialized
        }
    }

    /**
     * The {@link SettingMenu} instance being tested.
     */
    private SettingMenu settingMenu;

    /**
     * The root {@link Group} used as the parent node for the `SettingMenu`.
     */
    private Group root;

    /**
     * Sets up the test environment before each test.
     * <p>
     * Initializes the root {@link Group} and the singleton instance of {@link SettingMenu}.
     * </p>
     */
    @Before
    public void setUp() {
        root = new Group();
        settingMenu = SettingMenu.GetSettingMenu(root);
    }

    /**
     * Verifies the singleton pattern for the {@link SettingMenu}.
     * <p>
     * Ensures that multiple calls to {@link SettingMenu#GetSettingMenu(Group)} return the same instance.
     * </p>
     */
    @Test
    public void testGetSettingMenu() {
        SettingMenu firstInstance = SettingMenu.GetSettingMenu(root);
        SettingMenu secondInstance = SettingMenu.GetSettingMenu(root);
        assertSame(firstInstance, secondInstance);
    }

    /**
     * Tests the `configureSettings` method.
     * <p>
     * Verifies that the settings UI is properly configured and the stack pane contains
     * the expected components: a {@link MenuImage} and a {@link GridPane}.
     * </p>
     */
    @Test
    public void testConfigureSettings() {
        settingMenu.configureSettings();
        assertFalse(settingMenu.stackPane.getChildren().isEmpty());
        assertTrue(settingMenu.stackPane.getChildren().get(0) instanceof MenuImage);
        assertTrue(settingMenu.stackPane.getChildren().get(1) instanceof GridPane);
    }

    /**
     * Tests the creation of the volume slider.
     * <p>
     * Verifies that the slider is initialized with the correct range and default value.
     * </p>
     */
    @Test
    public void testCreateVolumeSlider() {
        Slider volumeSlider = settingMenu.createVolumeSlider();
        assertEquals(0, volumeSlider.getMin(), 0.0);
        assertEquals(100, volumeSlider.getMax(), 0.0);
        assertEquals(SoundEffects.GetVolume() * 100, volumeSlider.getValue(), 0.0);
    }

    /**
     * Tests the creation of the difficulty slider.
     * <p>
     * Verifies that the slider is initialized with the correct range and default value.
     * </p>
     */
    @Test
    public void testCreateDifficultySlider() {
        Slider difficultySlider = settingMenu.createDifficultySlider();
        assertEquals(1, difficultySlider.getMin(), 0.0);
        assertEquals(3, difficultySlider.getMax(), 0.0);
        assertEquals(SettingMenu.getDifficulty(), difficultySlider.getValue(), 0.0);
    }

    /**
     * Tests the creation of the back button.
     * <p>
     * Verifies that the button is properly styled and has a graphic assigned.
     * </p>
     */
    @Test
    public void testCreateBackButton() {
        Button backButton = settingMenu.createBackButton();
        assertNotNull(backButton.getGraphic());
        assertEquals("-fx-background-color: transparent;", backButton.getStyle());
    }

    /**
     * Tests the `goBack` method.
     * <p>
     * Verifies that the stack pane is cleared when the user navigates back.
     * </p>
     */
    @Test
    public void testGoBack() {
        settingMenu.stackPane.getChildren().add(new Button()); // Simulate content added to the StackPane
        settingMenu.goBack();
        assertTrue(settingMenu.stackPane.getChildren().isEmpty());
    }

    /**
     * Tests setting the difficulty via the difficulty slider.
     * <p>
     * Verifies that changing the slider value updates the difficulty setting in the {@link SettingMenu}.
     * </p>
     */
    @Test
    public void testSetDifficulty() {
        int newDifficulty = 2;
        Slider difficultySlider = settingMenu.createDifficultySlider();
        difficultySlider.setValue(newDifficulty);
        assertEquals(newDifficulty, SettingMenu.getDifficulty());
    }

    /**
     * Tests setting the volume via the volume slider.
     * <p>
     * Verifies that changing the slider value updates the volume in {@link SoundEffects}.
     * </p>
     */
    @Test
    public void testSetVolume() {
        Slider volumeSlider = settingMenu.createVolumeSlider();
        double newVolume = 0.75; // 75%
        volumeSlider.setValue(newVolume * 100);
        assertEquals(newVolume, SoundEffects.GetVolume(), 0.01);
    }
}
