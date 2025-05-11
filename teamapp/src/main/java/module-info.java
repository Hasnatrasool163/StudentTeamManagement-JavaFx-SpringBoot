module com.teamapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;


    opens com.teamapp to javafx.fxml;
    exports com.teamapp;
    exports  com.teamapp.controller to  javafx.fxml;
    exports com.teamapp.model to com.fasterxml.jackson.databind;
    opens com.teamapp.controller to javafx.fxml;
}