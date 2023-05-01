package com.example.battleboggle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;

public class usernameController {
    @FXML
    private Label userLabel;
    @FXML
    private TextField userTextField;
    @FXML
    private Button userButton;


    public void submit(ActionEvent event) {
        try {
            //switches view once username is submitted
            switchView();
            //store text from textField
            String username = userTextField.getText();
            //set the gameplay username to text from the textField
            Gameplay.username = username;
            System.out.println("Username: " + Gameplay.username);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void switchView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("gameboard-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) userLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("error with switching from username-view");
        }
    }

}

