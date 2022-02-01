package edu.wsu.model;

import static edu.wsu.model.TicTacToeGame.PLAYER_O;
import static edu.wsu.model.TicTacToeGame.PLAYER_X;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

class TicTacToeGameTest {


  private TicTacToeGame game;

  TicTacToeGame createGame() {
    return new TicTacToeImplementation();
  }

  @org.junit.jupiter.api.BeforeEach
  void setUp() {

    game = createGame();
  }

  @Test
  void createGameTest() {
    // right after creation the game is not started
    assertEquals(TicTacToeGame.GameState.NOT_STARTED, game.getState());
  }

  @Test
  void startGame() {
    assertEquals(game.getState(), TicTacToeGame.GameState.NOT_STARTED);

    // after you start the game the game state is running and the
    // current player is X
    game.startGame();
    assertEquals(game.getState(), TicTacToeGame.GameState.RUNNING);
    assertEquals(game.getCurrentPlayer(), PLAYER_X);
  }

  @Test
  void setPlayers() {
    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");

    game.startGame();
    assertEquals(TicTacToeGame.GameState.RUNNING, game.getState());
    assertEquals(PLAYER_X, game.getCurrentPlayer());
    assertEquals("Bertie", game.getCurrentPlayerName() );

  }

  @Test
  void setGetValue() {
    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");
    game.startGame();

    assertEquals(PLAYER_X, game.getCurrentPlayer());
    assertEquals("Bertie", game.getCurrentPlayerName());

    game.setValue(0,0);

    assertEquals(PLAYER_X, game.getValue(0,0));
    assertEquals(PLAYER_O, game.getCurrentPlayer());
    assertEquals("Ruth", game.getCurrentPlayerName());

    game.setValue(0, 1);
    assertEquals(PLAYER_O, game.getValue(0,1));
    assertEquals(PLAYER_X, game.getCurrentPlayer());
    assertEquals( "Bertie", game.getCurrentPlayerName());
  }

  @Test
  void gameWon1() {
    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");
    game.startGame();

    // bertie always puts his pieces in column 0
    // ruth always puts her pieces in column 1
    int bertieRow = 0, ruthRow = 0;
    for(int i = 0; i < 5; i++) {
      if (i %2 == 0) {
        // bertie
        game.setValue(bertieRow++, 0);
      } else {
        // ruth
        game.setValue(ruthRow++, 1);
      }
    }

    // verify that the game is won by bertie after 5 moves
    assertEquals(TicTacToeGame.GameState.COMPLETED, game.getState());
    assertEquals("Bertie", game.winnerName());
  }

  @Test
  void gameWon2() {
    // bertie puts his first 2 pices in column 0
    // ruth puts her first 2 pieces in column 1
    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");
    game.startGame();

    int bertieRow = 0, ruthRow = 0;
    for(int i = 0; i < 4; i++) {
      if (i %2 == 0) {
        // bertie
        game.setValue(bertieRow++, 0);
      } else {
        // ruth
        game.setValue(ruthRow++, 1);
      }
    }

    // bertie puts his 3rd piece in row 0, column 2
    game.setValue(0, 2);

    // ruth puts her 3rd piece in row 2, column 1
    game.setValue(2, 1);

    // verify that the game is won by ruth
    assertEquals(TicTacToeGame.GameState.COMPLETED, game.getState());
    assertEquals("Ruth", game.winnerName());
  }

  @Test
  void gameWon3() {
    // bertie puts his first 2 pieces in the diagonal (0,0), (1, 1), (2, 2)
    // ruth puts her first 2 pieces in diagonal (0, 2), (2, 0)

    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");
    game.startGame();

    List<Pair<Integer, Integer>> bertieMoves = List.of(
        new Pair<>(0, 0),
        new Pair<>(2, 2),
        new Pair<>(1, 1)
    );

    List<Pair<Integer, Integer>> ruthMoves = List.of(
        new Pair<>(0, 2),
        new Pair<>(2, 0)
    );

    ;
    for(int bertie = 0, ruth = 0, i = 0; bertie < bertieMoves.size() || ruth < ruthMoves.size(); i++) {
      Pair<Integer, Integer> currentMove;
      if (i %2 == 0) {
        // bertie
        currentMove = bertieMoves.get(bertie++);
      } else {
        // ruth
        currentMove = ruthMoves.get(ruth++);
      }
      game.setValue(currentMove.getKey(), currentMove.getValue());
    }

    // verify that the game is won by bertie
    assertEquals(TicTacToeGame.GameState.COMPLETED, game.getState());
    assertEquals("Bertie", game.winnerName());
  }


}