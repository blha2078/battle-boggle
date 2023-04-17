module com.example.battleboggle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.battleboggle to javafx.fxml;
    exports com.example.battleboggle;
}