package com.example.battleboggle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private GameRound gameRound;

    @FXML
    private GridPane gameGrid;

    /*
    Used https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx to help understand Platform.runLater
    to prevent the lag that was happening with the timer updating initially
     */
    public void setRound(GameRound round) {
        this.gameRound = round;
    }

    GameBoardFactory factory = new GameBoardFactory();
    public void initialize() { //initalizes the timer that will be displayed to the view

        //makes a new round here everytime this view is run instead of being in the gameplay class
        GameRound round = new GameRound(factory);
        setRound(round);
        //resets the input_words arraylist after each round 
        gameRound.input_words.clear();
        round.play();
        //round.analyzeRound();
        //create a timeline to display each new keyframe that shows the countdown
        timeline = new Timeline(
                //makes a new keyframe for every second that is updated
                new KeyFrame(Duration.seconds(1), event -> {
                    timeSeconds--;
                    //prevents the lag on updating the view and displays the time in minutes and seconds left
                    Platform.runLater(() -> countdownLabel.setText(String.format("%02d:%02d", timeSeconds / 60, timeSeconds % 60)));
                    //tells that program that when it hits view to stop the timeline and change views
                    if (timeSeconds <= 0) {

                        round.analyzeRound();
                        timeline.stop();
                        switchView();
                    }
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }
    private void switchView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("score-view.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) countdownLabel.getScene().getWindow();
            stage.setScene(scene);
            ScoreController controller = new ScoreController();
            controller.initialize();
        } catch (IOException e) {
            System.out.println("error with switching from gameboard-view");
        }
    }

    @FXML
    private void startGameButton(){
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                //iterate through the gameboard that was created by factory
                //System.out.println(gameRound.gameboard.board[i][j]);
                //adds the letter in the gameboard object to a text box to display to the view
                Text text = new Text(Character.toString(gameRound.gameboard.board[i][j]));
                //makes the font for each text
                text.setFont(new Font(40));
                //adds the text box to the grid
                gameGrid.add(text, j, i);
            }
        }
        startButton.setVisible(false);
        timeline.play();//runs the keyframes from the initialize function

    }

    @FXML
    //function that takes in user input each time the enter key
    void EnterKey(KeyEvent event) {
        //checks if enter key is pressed
        if (event.getCode() == KeyCode.ENTER) {
            //gets the text from the text field in the view
            String text = textField.getText();
            //calls function in gameRound that adds text to the input_words arrayList
            gameRound.addToList(text);
            System.out.println(gameRound.input_words + " user input so far");
            //sets the text field to be blank again after user hits enter
            textField.setText("");
        }
    }

}
