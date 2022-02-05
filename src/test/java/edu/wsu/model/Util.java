package edu.wsu.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import org.hamcrest.CoreMatchers;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.api.FxRobotContext;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.service.finder.NodeFinder;

public class Util {
  static void checkDraw() {
    FxAssert.verifyThat(".label", LabeledMatchers.hasText(CoreMatchers.containsString("NO winner")));
  }

  static void checkWinner(String playerName) {
    FxAssert.verifyThat(".label", LabeledMatchers.hasText(CoreMatchers.containsString("winner: "+ playerName)));
  }

  static void move(FxRobot robot, TicTacToeGame model, int row, int col, String imagePath) {
    FxRobotContext context = robot.robotContext();
    NodeFinder finder = context.getNodeFinder();

    robot.interact(() -> model.setValue(row, col));
    Label cell =  finder.lookup("#cell"+row+col).query();
    ImageView cell02Image = (ImageView) cell.getGraphic();
    // check that cell row, col containsthe specified image path and that the current player is now Bertie
    assertTrue(cell02Image.getImage().getUrl().matches(imagePath));
  }
}
