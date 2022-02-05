package edu.wsu;


import edu.wsu.model.ModelSingleton;
import edu.wsu.model.TicTacToeGame;
import edu.wsu.model.TicTacToeImplementation;
import edu.wsu.view.TicTacToeJavaFXView;
import edu.wsu.view.Util;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.util.Duration;
import javafx.util.Pair;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        TicTacToeJavaFXView ticTacToeView = new TicTacToeJavaFXView();
        Scene scene = new Scene(ticTacToeView);

        TicTacToeGame model = ModelSingleton.getInstance();
        model.setPlayer1Name("Bertie");
        model.setPlayer2Name("Ruth");
        model.addObserver(ticTacToeView);

        stage.setScene(scene);
        stage.show();

        model.startGame();
    }

    public static void main(String[] args) {
        launch();
    }

}