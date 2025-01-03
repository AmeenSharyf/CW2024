module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.media;
    requires java.sql;
    requires javafx.graphics;
    requires junit;//add library junit to classpath if red


    opens Menus to javafx.fxml;
    opens Controller to javafx.fxml;
    opens Level to javafx.fxml;
    opens Projectile to javafx.fxml;
    opens Managers to javafx.fxml;
    opens ImageClass to javafx.fxml;
    opens ActiveActor to javafx.fxml;
    opens Sound to javafx.fxml;
    opens Plane to javafx.fxml;

    exports ActiveActor;
    exports Plane;
    exports Managers;
    exports ImageClass;
    exports Controller;
    exports Projectile;
    exports Sound;
    exports Level;
    exports Menus;

}
