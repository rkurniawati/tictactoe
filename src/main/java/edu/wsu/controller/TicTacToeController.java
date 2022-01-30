package edu.wsu.controller;

import edu.wsu.model.TicTacToeGame;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TicTacToeController {
    @FXML
    public GridPane gridPane;

    @FXML
    public BorderPane borderPane;

    public void initialize() {
        gridPane.getStyleClass().add("grid");
        for(int r = 0; r < TicTacToeGame.BOARD_SIZE; r++) {
            for (int c = 0; c < TicTacToeGame.BOARD_SIZE; c++) {
                Label label = new Label();
                //label.setGraphic(new ImageView(blank));
                label.setMinSize(100, 100);
                label.getStyleClass().add("cell");
                label.setContentDisplay(ContentDisplay.CENTER);
                gridPane.add(label, c, r);
            }
        }
    }
}
