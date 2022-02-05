package edu.wsu;


import static edu.wsu.view.Util.loadFXML;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Scene scene = new Scene(loadFXML("GameSettings"));
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}