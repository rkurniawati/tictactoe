package edu.wsu.view;

import edu.wsu.App;
import edu.wsu.controller.TicTacToeController;
import edu.wsu.controller.TicTacToeController.CellClickHandler;
import edu.wsu.model.TicTacToeGame;
import edu.wsu.model.TicTacToeImplementation;
import java.io.IOException;
import java.util.Objects;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import java.util.Observable;
import java.util.Observer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static edu.wsu.model.TicTacToeGame.BOARD_SIZE;
import static edu.wsu.model.TicTacToeGame.PLAYER_O;
import static edu.wsu.model.TicTacToeGame.PLAYER_X;
import static edu.wsu.model.TicTacToeImplementation.*;

public class TicTacToeJavaFXView extends BorderPane implements Observer  {

  public static final String EDU_WSU_X_PNG = "/edu/wsu/x.png";
  public static final String EDU_WSU_O_PNG = "/edu/wsu/o.png";
  public static final String EDU_WSU_BOARD_STYLE_CSS = "/edu/wsu/board-style.css";
  private final Label[][] boardLabels;
  private final static Logger logger = LogManager.getLogger(App.class);
  private final Image imageX, imageO;

  public TicTacToeJavaFXView() throws Exception {
    FXMLLoader fxmlLoader = new FXMLLoader(Util.class.getResource("/edu/wsu/" + "TicTacToeBoard" + ".fxml"));
    fxmlLoader.setRoot(this);
    TicTacToeController controller = new TicTacToeController();
    fxmlLoader.setController(controller);
    fxmlLoader.load();
    this.boardLabels = new Label[BOARD_SIZE][BOARD_SIZE];
    imageX = new Image(Objects.requireNonNull(getClass().getResource(EDU_WSU_X_PNG)).toString());
    imageO = new Image(Objects.requireNonNull(getClass().getResource(EDU_WSU_O_PNG)).toString());

    this.getStylesheets().add(getClass().getResource(Objects.requireNonNull(EDU_WSU_BOARD_STYLE_CSS)).toString());

    GridPane gridPane = this.getBoard();
    gridPane.getStyleClass().add("grid");
    for(int r = 0; r < TicTacToeGame.BOARD_SIZE; r++) {
      for (int c = 0; c < TicTacToeGame.BOARD_SIZE; c++) {
        Label label = new Label();
        //label.setGraphic(new ImageView(blank));
        label.setMinSize(100, 100);
        label.getStyleClass().add("cell");
        label.setContentDisplay(ContentDisplay.CENTER);
        label.setOnMouseClicked(new CellClickHandler(r, c));
        gridPane.add(label, c, r);

        // label each cell with cellrc, eg cell00, cell01
        String id = "cell"+r+c;
        label.setId(id);
      }
    }

    initializeBoard();
  }

  private GridPane getBoard() {
    return (GridPane) this.getCenter();
  }

  private Label getHUD() {
    StackPane sp = (StackPane) this.getTop();
    assert sp.getChildren().size() == 1;
    return (Label) sp.getChildren().get(0);
  }

  public void initializeBoard() {
    for(Node n: this.getBoard().getChildren()) {
      int c = GridPane.getColumnIndex(n);
      int r = GridPane.getRowIndex(n);
      assert n instanceof Label;
      boardLabels[r][c] = (Label) n;
    }
  }

  @Override
  public void update(Observable o, Object arg) {
    assert o instanceof TicTacToeImplementation;
    assert arg instanceof TicTacToeGame;

    TicTacToeGame gameInfo = (TicTacToeGame) arg;
    Label hud = getHUD();
    if (gameInfo.getState() == GameState.COMPLETED) {
      hud.setText("Game over, winner: " + gameInfo.winnerName());
    } else if (gameInfo.getState() == GameState.COMPLETED_DRAW) {
      hud.setText("Game over, NO winner");
    } else {
      hud.setText("Current player: " +  gameInfo.getCurrentPlayerName() + " (" + gameInfo.getCurrentPlayer() + ")");
    }

    for(int r = 0; r < BOARD_SIZE; r++) {
      for (int c = 0; c < BOARD_SIZE; c++) {
        if (gameInfo.getValue(r, c) != 0 && boardLabels[r][c].getGraphic() == null)
          if (gameInfo.getValue(r, c) == PLAYER_X) {
            boardLabels[r][c].setGraphic(new ImageView(imageX));
          } else if (gameInfo.getValue(r, c) == PLAYER_O) {
            boardLabels[r][c].setGraphic(new ImageView(imageO));
          } else {
            logger.error("Invalid player code "  + gameInfo.getValue(r, c));
          }
      }
    }
  }
}
