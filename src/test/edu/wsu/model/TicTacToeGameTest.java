package edu.wsu.model;

import static edu.wsu.model.TicTacToeGame.PLAYER_O;
import static edu.wsu.model.TicTacToeGame.PLAYER_X;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TicTacToeGameTest {


  private TicTacToeGame game;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    game = new TicTacToeImplementation();
  }

  @Test
  void createGame() {
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
    // bertie always put his pieces in column 0
    // ruth always put her pieces in column 1
    game.setPlayer1Name("Bertie");
    game.setPlayer2Name("Ruth");
    game.startGame();

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

    // verify that the game is won by bertie
    assertEquals(TicTacToeGame.GameState.COMPLETED, game.getState());
    assertEquals("Bertie", game.winnerName());


  }
}