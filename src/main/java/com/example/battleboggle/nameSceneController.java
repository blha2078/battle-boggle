package com.example.battleboggle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class nameSceneController {
    @FXML
    private Label userLabel;
    @FXML
    private TextField userTextField;
    @FXML
    private Button userButton;

    String username;
    public void submit(ActionEvent event) {
        try {
            username = userTextField.getText();
            System.out.println(username);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
