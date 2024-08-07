module com.example.watch {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.watch to javafx.fxml;
    exports com.example.watch;
}