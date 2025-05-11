package com.teamapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class AboutController {

    @FXML
    private VBox teamList;

    public void initialize() {
        teamList.getChildren().clear();
        teamList.getChildren().add(new Label("Nasser Al Rashaid - 221110975"));
        teamList.getChildren().add(new Label("Muhammed Alhowaish - 221111246"));
        teamList.getChildren().add(new Label("Mohammed Bejad Alharbi - 220211209"));
        teamList.getChildren().add(new Label("Abdulrahman Bin Mushayt - 220111040"));
    }

    @FXML
    private void closeWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}