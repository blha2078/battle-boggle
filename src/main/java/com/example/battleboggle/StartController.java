package com.example.battleboggle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.application.Application;
import java.io.IOException;

public class StartController extends Application{
    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void start(Stage stage) throws IOException {
        //loads the start-view
        FXMLLoader fxmlLoader = new FXMLLoader(StartController.class.getResource("start-view.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        stage.setTitle("Boggle Battle");
        stage.setScene(scene1);
        stage.show();
    }

    public static void main(String[] args) {
        //moved the contents of the main.java here to try and condense
        System.out.println("Starting game");
        Gameplay game = new Gameplay();
        game.runGame();
        launch();
    }

    public void switchScene(ActionEvent event) throws IOException {
        //action that switches the scene to username-view if button is clicked
        root = FXMLLoader.load(getClass().getResource("username-view.fxml"));
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
