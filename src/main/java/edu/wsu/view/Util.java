package edu.wsu.view;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Util {
  public static void setRoot(Scene scene, String fxml) throws IOException {
    scene.setRoot(loadFXML(fxml));
  }

  public static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(Util.class.getResource("/edu/wsu/" + fxml + ".fxml"));
    return fxmlLoader.load();
  }


}
