package Menus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.Group;


public class SettingMenuTest {
    private SettingMenu settingMenu;
    private Group rootMock;

    @BeforeEach
    public void setUp() {
        rootMock = mock(Group.class);
        settingMenu = SettingMenu.GetSettingMenu(rootMock);
    }

    @Test
    public void testGetSettingMenuSingleton() {
        SettingMenu newSettingMenu = SettingMenu.GetSettingMenu(mock(Group.class));
        assertSame(settingMenu, newSettingMenu, "Singleton instance should be the same");
    }
}