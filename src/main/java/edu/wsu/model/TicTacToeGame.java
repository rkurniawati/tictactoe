package edu.wsu.model;

import java.util.Observable;

public abstract class TicTacToeGame extends Observable {

    /**
     * Tic Tac Toe game state: not started, running, completed w/ a winner, completed & draw
     */
    public enum GameState {
        NOT_STARTED,
        RUNNING,
        COMPLETED,
        COMPLETED_DRAW
    }

    /**
     *  symbol for the first player
     */
    public static final char PLAYER_X = 'X';

    /**
     *  symbol for the second player
     */
    public static final char PLAYER_O = 'O';

    /**
     *  board size (3 x 3)
     */
    public static final int BOARD_SIZE = 3;

    /**
     * Get the board value at a specific row and column.
     *
     * @param row the row on the board [0..3)
     * @param col the row on the board [0..3)
     * @return 0, 'X', or 'O' (capital letter o)
     */
    public abstract char getValue(int row, int col);

    /**
     * Set a board position to the current player's symbol.
     *
     * @param row the row on the board [0..3)
     * @param col the row on the board [0..3)
     */
    public abstract void setValue(int row, int col);

    /**
     * Start the tic tac toe game
     * Pre-req: state == NOT_STARTED
     * Changes the state of the game to RUNNING
     * and sets the current player to PLAYER_1
     */
    public abstract void startGame();

    /**
     * Get the current player's symbol.
     * @return the current player's symbol 'X' or 'O'
     */
    public abstract char getCurrentPlayer();

    /**
     * Get the name of the current player.
     * @return player1's or player2's name or null if the game is not started
     */
    public abstract String getCurrentPlayerName();

    /**
     * Set the name of player 1 (as specified by the user).
     * @param playerName the player name
     */
    public abstract void setPlayer1Name(String playerName);

    /**
     * Set the name of player 2 (as specified by the user).
     * @param playerName the player name
     */
    public abstract void setPlayer2Name(String playerName);

    /**
     * Get the current game state
     * @return one of the GameState enums
     */
    public abstract GameState getState();

    /**
     * Get the name of the winner.
     * @return the name of the winner of the game, null if the game doesn't have a winner or game
     *   is not completed yet.
     */
    public abstract String winnerName();

}
