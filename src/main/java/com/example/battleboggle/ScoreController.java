package com.example.battleboggle;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void initialize(){
        for(int i = 0; i < GameRound.valid_words.size(); i++){
            System.out.println(GameRound.valid_words.get(i));
        }
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("leaderboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
