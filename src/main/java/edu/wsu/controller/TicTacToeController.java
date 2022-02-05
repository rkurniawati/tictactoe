package edu.wsu.controller;

import edu.wsu.model.ModelSingleton;
import edu.wsu.model.TicTacToeGame;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class TicTacToeController {
    @FXML
    public GridPane gridPane;

    @FXML
    public BorderPane borderPane;

    public static class CellClickHandler implements
        EventHandler<MouseEvent> {
        private final int row;
        private final int col;

        public CellClickHandler(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void handle(MouseEvent event) {
            TicTacToeGame game = ModelSingleton.getInstance();
            game.setValue(row, col);
        }
    }
}
