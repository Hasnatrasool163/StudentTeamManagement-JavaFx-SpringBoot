package com.teamapp.controller;

import com.teamapp.MainApp;
import com.teamapp.api.ApiClient;
import com.teamapp.model.TeamMember;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;
import java.util.logging.Logger;

public class MainController {
    @FXML private TextField nameField;
    @FXML private TextField idField;
    @FXML private TextField searchIdField;

    @FXML private TableView<TeamMember> tableView;
    @FXML private TableColumn<TeamMember, String> nameCol;
    @FXML private TableColumn<TeamMember, String> studentIDCol;
    @FXML private TableColumn<TeamMember, Long> idCol;


    private ObservableList<TeamMember> members = FXCollections.observableArrayList();
    private static final Logger logger = Logger.getLogger(MainApp.class.getName());

    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data -> new javafx.beans.property.SimpleLongProperty(data.getValue().getId()).asObject());
        nameCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        studentIDCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getStudentId()));
        tableView.setItems(members);

        loadMembers();
    }

    @FXML
    void loadMembers() {
        try {
            List<TeamMember> allMembers = ApiClient.getAllMembers();
            members.clear();
            members.addAll(allMembers);
            logger.info("Loaded " + allMembers.size() + " members");
            resetFields();
        } catch (Exception e) {
            showAlert("Error", "Failed to load members: " + e.getMessage());
        }
    }

    @FXML
    void addMember(ActionEvent event) {
        String name = nameField.getText().trim();
        String id = idField.getText().trim();
        if (!name.isEmpty() && !id.isEmpty()) {
            TeamMember newMember = new TeamMember(name, id);
            members.add(newMember);
            logger.info("Added member: " + name);
            resetFields();
            try{
            ApiClient.addMember(newMember);
            }catch (Exception e){
                logger.warning("Failed to add member: " + e.getMessage());
            }
        } else {
            showAlert("Missing Fields", "Please enter both Name and ID.");
        }
    }

    @FXML
    void removeMember(ActionEvent event) {
        TeamMember selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            members.remove(selected);
            logger.info("Removed member: " + selected.getName());
            resetFields();
            try{
            ApiClient.deleteMember(selected.getId());
            } catch (Exception e) {
                logger.warning("Failed to remove member: " + e.getMessage());
            }
        } else {
            showAlert("No Selection", "Please select a member to remove.");
        }
    }

    @FXML
    void updateMember(ActionEvent event) {
        TeamMember selected = tableView.getSelectionModel().getSelectedItem();
        String name = nameField.getText().trim();
        String id = idField.getText().trim();

        if (selected == null) {
            showAlert("No Selection", "Please select a member to update.");
        } else if (name.isEmpty() || id.isEmpty()) {
            showAlert("Missing Fields", "Please enter both Name and ID to update.");
        } else {
            selected.setName(name);
            selected.setStudentId(id);
            tableView.refresh();
            logger.info("Updated member: " + selected.getName());
            resetFields();
            try{
            ApiClient.updateMember(selected.getId(),selected);
            }catch (Exception e){
                logger.warning("Failed to update member: " + e.getMessage());
            }
        }
    }

    @FXML
    void openAbout(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/teamapp/about-view.fxml"));
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(loader.load()));
        aboutStage.setTitle("About");
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.show();
    }

    @FXML
    void selectMember(MouseEvent event) {
        TeamMember selected = tableView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            nameField.setText(selected.getName());
            idField.setText(selected.getStudentId());
        }
    }

    @FXML
    void searchMemberById(ActionEvent event) {
        String searchId = searchIdField.getText().trim();
        if (searchId.isEmpty()) {
            showAlert("Empty Search", "Please enter a Student ID to search.");
            return;
        }

        try {
            TeamMember member = ApiClient.getStudentById(searchId);
            if (member != null) {
                members.clear();
                members.add(member);
                nameField.setText(member.getName());
                idField.setText(member.getStudentId());
                logger.info("Found member: " + member.getName());
            } else {
                showAlert("Not Found", "No member found with Student ID: " + searchId);
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to search member: " + e.getMessage());
        }
    }

    public void exitApp(){
        System.exit(0);
    }

    private void resetFields(){
        nameField.setText("");
        idField.setText("");
    }

    private void showAlert(String title, String message) {
        logger.warning(title + ": " + message);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
