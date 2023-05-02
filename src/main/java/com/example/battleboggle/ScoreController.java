package com.example.battleboggle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class ScoreController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<String> scoretableView = new TableView<>();

    @FXML
    private TableColumn<String, String> wordColumn = new TableColumn<>("Words");

    @FXML
    private Label score;


    public void initialize(){
        //note: will later need to change valueOf(???) based on the type of gameboard instead of hardcoding it
        score.setText(String.valueOf(GameRound.scores[0]));
        //make an observablelist for the valid words arraylist
        ObservableList<String> observableList = FXCollections.observableList(GameRound.valid_words);
        //set the table view's items to the list above
        scoretableView.setItems(observableList);
        //sets cellvaluefactory for word column
        //https://stackoverflow.com/questions/50612023/javafx-setcellvaluefactory used this to help setup
        wordColumn.setCellValueFactory(cellData -> {
            return new SimpleStringProperty(cellData.getValue());
        });
        //add columns to table view
        scoretableView.getColumns().add(wordColumn);
        //add table veiw to the anchor pane
        pane.getChildren().add(scoretableView);

        //layout for tableview and wordcolumn
        scoretableView.prefHeight(435);
        scoretableView.prefWidth(435);
        scoretableView.minWidth(scoretableView.getPrefWidth());
        wordColumn.setPrefWidth(435);
        scoretableView.setLayoutX(83);
        scoretableView.setLayoutY(83);
        wordColumn.setStyle("-fx-alignment: CENTER;");

        /*for(int i = 0; i < GameRound.valid_words.size(); i++){
            System.out.println(GameRound.valid_words.get(i));
        }
         */
    }

    public void switchScene(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("leaderboard-view.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
