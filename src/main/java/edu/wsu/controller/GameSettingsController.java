package edu.wsu.controller;

import edu.wsu.model.ModelSingleton;
import edu.wsu.model.TicTacToeGame;
import edu.wsu.view.TicTacToeJavaFXView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GameSettingsController {
    @FXML
    public TextField player1;

    @FXML
    public TextField player2;

    public void startGameAction(ActionEvent actionEvent) throws Exception {

        // save the player names in the model
        TicTacToeGame model = ModelSingleton.getInstance();
        model.setPlayer1Name(player1.getText());
        model.setPlayer2Name(player2.getText());

        // set the observable-observer relationship
        TicTacToeJavaFXView ticTacToeView = new TicTacToeJavaFXView();
        model.addObserver(ticTacToeView);

        // create a scene with the tictactoe game view
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(ticTacToeView);
        stage.setScene(scene);
        stage.show();

        model.startGame();
    }
}
