package com.example.battleboggle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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

    private Stage stage;
    private Scene scene;
    private Parent root;

    String username;
    public void submit(ActionEvent event) {
        try {
            username = userTextField.getText();
            System.out.println(username);
            //will eventually save the username in case they make the leaderboard
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("gameboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

