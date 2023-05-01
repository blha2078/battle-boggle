package com.example.battleboggle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.application.Platform;
import java.io.IOException;

public class GameboardController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private int timeSeconds = 180;//sets the countdown to 3 minutes
    private Timeline timeline;
    @FXML
    private TextField textField;

    @FXML
    private Button startButton;

    @FXML
    private Label countdownLabel;




    /*
    Used https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx to help understand Platform.runLater
    to prevent the lag that was happening with the timer updating initially

     */
    public void initialize() { //initalizes the timer that will be displayed to the view

        //create a timeline to display each new keyframe that shows the countdwon
        timeline = new Timeline(
                //makes a new keyframe for every second that is updated
                new KeyFrame(Duration.seconds(1), event -> {
                    timeSeconds--;
                    //prevents the lag on updating the view and displays the time in minutes and seconds left
                    Platform.runLater(() -> countdownLabel.setText(String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60)));
                    //tells that program that when it hits view to stop the timeline and change views
                    if (timeSeconds <= 0) {
                        timeline.stop();
                        switchView();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    private void switchView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("leaderboard-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) countdownLabel.getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            System.out.println("error with switching from gameboard view");
        }
    }

    @FXML
    private void startGameButton(){
        startButton.setVisible(false);
        timeline.play();//runs the keyframes from the initialize function

    }

    GameRound gameRound = new GameRound(new GameBoardFactory());

    @FXML
    void EnterKey(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String text = textField.getText();
            gameRound.addToList(text);
            System.out.println(gameRound.input_words + " user input so far");
            //sets the text field to be blank again after user hits enter
            /*for(int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.println(gameRound.gameboard.board[i][j]);
                }
            }

             */
            textField.setText("");
        }
    }




}
