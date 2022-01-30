package edu.wsu;

import edu.wsu.view.TicTacToeJavaFXView;
import edu.wsu.view.Util;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        TicTacToeJavaFXView ticTacToeView = new TicTacToeJavaFXView();
        scene = new Scene(ticTacToeView);

        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }

}