package com.teamapp;

import com.teamapp.util.LoggerUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class MainApp extends Application {
    public static Logger logger = Logger.getLogger(MainApp.class.getName());

    @Override
    public void start(Stage primaryStage) throws Exception {
        LoggerUtil.setup();
        logger.info("Application started");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/teamapp/main-view.fxml"));
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/com/teamapp/style.css").toExternalForm());

        primaryStage.setTitle("Team Management App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
