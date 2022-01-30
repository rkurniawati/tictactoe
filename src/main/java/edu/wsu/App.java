package edu.wsu;


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

    private static Scene scene;
    private int idx = 0;

    @Override
    public void start(Stage stage) throws IOException {
        TicTacToeJavaFXView ticTacToeView = new TicTacToeJavaFXView();
        scene = new Scene(ticTacToeView);

        TicTacToeGame model = new TicTacToeImplementation();
        model.setPlayer1Name("Bertie");
        model.setPlayer2Name("Ruth");
        model.addObserver(ticTacToeView);

        stage.setScene(scene);
        stage.show();

        model.startGame();

        List<Pair<Integer, Integer>> moves = List.of(
            new Pair<>(0, 0),
            new Pair<>(0, 2),
            new Pair<>(2, 2),
            new Pair<>(2, 0),
            new Pair<>(1, 1)
        );

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            if (idx < moves.size()) {

                Pair<Integer, Integer> currentMove = moves.get(idx++);
                model.setValue(currentMove.getKey(), currentMove.getValue());
            }

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    private void delay(int numSeconds)  {
        try {
            TimeUnit.SECONDS.sleep(numSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}