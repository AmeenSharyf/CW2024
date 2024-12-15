
package Menus;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Group;
/**
 * The {@code SettingMenuTest} class contains unit tests for the {@link SettingMenu} class.
 * <p>
 * This class uses JUnit 5 for testing and Mockito for mocking dependencies.
 * It verifies the functionality of the singleton pattern and ensures correct
 * behavior of the {@code SettingMenu} class.
 * </p>
 * Source Code: <a href="https://github.com/AmeenSharyf/CW2024/blob/master/src/test/java/Menus/SettingMenuTest.java">
 * GitHub Link</a>
 *
 */

public class SettingMenuTest {

    /**
     * An instance of {@code SettingMenu} used in the tests.
     */
    private SettingMenu settingMenu;

    /**
     * A mock object for the {@code Group} class, used to simulate the root node.
     */
    private Group rootMock;

    /**
     * Sets up the testing environment before each test.
     * <p>
     * This method initializes a mock {@code Group} object and retrieves a
     * singleton instance of {@code SettingMenu} for testing purposes.
     * </p>
     */
    @BeforeEach
    public void setUp() {
        rootMock = mock(Group.class);
        settingMenu = SettingMenu.GetSettingMenu(rootMock);
    }

    /**
     * Tests the singleton behavior of the {@link SettingMenu} class.
     * <p>
     * This test ensures that the {@code GetSettingMenu} method always returns the same instance
     * of {@code SettingMenu}, regardless of the input {@code Group}.
     * </p>
     */
    @Test
    public void testGetSettingMenuSingleton() {
        SettingMenu newSettingMenu = SettingMenu.GetSettingMenu(mock(Group.class));
        assertSame(settingMenu, newSettingMenu, "Singleton instance should be the same");
    }
}