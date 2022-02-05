package edu.wsu.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.wsu.view.TicTacToeJavaFXView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotContext;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.finder.NodeFinder;

@ExtendWith(ApplicationExtension.class)
public class TicTacToeClickTest {

  private static final String X_IMAGE_PATH = ".+\\/x.png$";
  private static final String O_IMAGE_PATH = ".+\\/o.png$";
  private static final String PLAYER1_NAME = "Bertie";
  private static final String PLAYER2_NAME = "Ruth";
  TicTacToeJavaFXView ticTacToeView;

  /**
   * Will be called with {@code @Before} semantics, i. e. before each test method.
   *
   * @param stage - Will be injected by the test runner.
   */
  @Start
  private void start(Stage stage) throws Exception {
    ticTacToeView = new TicTacToeJavaFXView();
    Scene scene = new Scene(ticTacToeView);

    TicTacToeGame model = ModelSingleton.getInstance();

    model.setPlayer1Name("Bertie");
    model.setPlayer2Name("Ruth");
    model.addObserver(ticTacToeView);

    stage.setScene(scene);
    stage.show();

    model.startGame();
  }

  /**
   * Check that game starts with player1.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void should_start_with_player1(FxRobot robot) {
    checkCurrentPlayer(PLAYER1_NAME);
  }

  private void checkCurrentPlayer(String playerName) {
    FxAssert.verifyThat(".label", LabeledMatchers.hasText(CoreMatchers.containsString(playerName)));
  }

  /**
   * Test the moves where Bertie wins with moves along the diagonal.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void bertieWinsWADiagonalMove(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 0, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 2, X_IMAGE_PATH);

    checkWinner(PLAYER1_NAME);
  }

  /**
   * Test the moves where Bertie wins with moves along row 0.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void bertieWinsWARow0Move(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 2, X_IMAGE_PATH);

    checkWinner(PLAYER1_NAME);
  }

  /**
   * Test the moves where Bertie wins with moves along column 0.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void bertieWinsWAColum0Move(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 0, X_IMAGE_PATH);

    checkWinner(PLAYER1_NAME);
  }

  /**
   * Test the moves where Ruth wins with moves along the TL-BR diagonal.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void ruthWinsWADiagonalMove(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 0, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 1, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 0, O_IMAGE_PATH);

    checkWinner(PLAYER2_NAME);
  }

  /**
   * Test the moves where Ruth wins with moves along the TL-BR diagonal.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void fullBoardBertieWins(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 0, 1, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 2, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 0, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 0, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 2, X_IMAGE_PATH);

    checkWinner(PLAYER1_NAME);
  }

  /**
   * Test the moves where Ruth wins with moves along the TL-BR diagonal.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void fullBoardDraw(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 0, 1, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 2, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 0, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 0, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 2, X_IMAGE_PATH);

    checkDraw();
  }

  /**
   * Test the moves where Ruth wins with moves along row 1.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void ruthWinsWARow1Move(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 0, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 2, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 1, O_IMAGE_PATH);

    checkWinner(PLAYER2_NAME);
  }

  /**
   * Test the moves where Ruth wins with moves along column 2.
   * @param robot - Will be injected by the test runner.
   */
  @Test
  void ruthWinsWAColum2Move(FxRobot robot) {

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 0, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 1, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 2, 0, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 2, 2, O_IMAGE_PATH);

    checkCurrentPlayer(PLAYER1_NAME);
    move(robot, 1, 1, X_IMAGE_PATH);

    checkCurrentPlayer(PLAYER2_NAME);
    move(robot, 0, 2, O_IMAGE_PATH);

    checkWinner(PLAYER2_NAME);
  }

  private void checkDraw() {
    FxAssert.verifyThat(".label", LabeledMatchers.hasText(CoreMatchers.containsString("NO winner")));
  }

  private void checkWinner(String playerName) {
    FxAssert.verifyThat(".label", LabeledMatchers.hasText(CoreMatchers.containsString("winner: "+ playerName)));
  }

  private void move(FxRobot robot, int row, int col, String imagePath) {

    FxRobotContext context = robot.robotContext();
    NodeFinder finder = context.getNodeFinder();

    Label cell =  finder.lookup("#cell"+row+col).query();
    robot.clickOn(cell);
    ImageView cell02Image = (ImageView) cell.getGraphic();
    // check that cell row, col containsthe specified image path and that the current player is now Bertie
    assertTrue(cell02Image.getImage().getUrl().matches(imagePath));
  }

}
