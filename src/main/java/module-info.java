module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;
    requires javafx.graphics;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.mockito;

    exports Controller;
    opens Menus to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Level to javafx.fxml;
    opens Projectile to javafx.fxml;
    opens Managers to javafx.fxml;
    opens ImageClass to javafx.fxml;
    opens ActiveActor to javafx.fxml;
    opens Sound to javafx.fxml;
    opens Plane to javafx.fxml;


    exports Managers;
    exports Sound;
    exports Plane;
    exports Level;
}