package com.example.battleboggle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;


public class LeaderboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    GameboardController controller = new GameboardController();

    @FXML private AnchorPane pane;
    @FXML private  GridPane boardPane;
    @FXML private GridPane usernameGrid;



    public void initialize() {

        //for (int i = 0;  i < 10; i++) {
            //System.out.println("TESTING"+Leaderboard.getLeaderboard().getUsernames()[i]);
            //System.out.println("TESTING"+Leaderboard.getLeaderboard().getScores()[i]);
            /*Text userText = new Text(Leaderboard.getLeaderboard().getUsernames()[i]);
            Text scoreText = new Text(Integer.toString(Leaderboard.getLeaderboard().getScores()[i]));

            userText.setFont(new Font(25));
            scoreText.setFont(new Font(25));
            gridPane.add(userText, 1, i);
            gridPane.add(scoreText, 2, i
             */
        //}
    }

    public void switchScene(ActionEvent event) throws IOException {
        controller.initialize();
        root = FXMLLoader.load(getClass().getResource("gameboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    //https://stackoverflow.com/questions/25037724/how-to-close-a-java-window-with-a-button-click-javafx-project used to figure out how to close out a view
    public void closeButton(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

}
