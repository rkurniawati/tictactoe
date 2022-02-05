package edu.wsu.model;


public class TicTacToeImplementation extends TicTacToeGame {
    public static final char PLAYER_X = 'X';
    public static final char PLAYER_O = 'O';
    public static final int BOARD_SIZE = 3;

    // actual private member variables for the instance
    private final char[][] board;
    private char currentPlayer;
    private GameState state;
    private int emptySpots;
    private String player1Name;
    private String player2Name;

    public TicTacToeImplementation() {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        state = GameState.NOT_STARTED;
    }

    public void startGame() {
        state = GameState.RUNNING;
        currentPlayer = PLAYER_X;
        emptySpots = BOARD_SIZE * BOARD_SIZE;

        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                board[r][c] = 0;
            }
        }
        setChanged();
        notifyObservers(this);
        clearChanged();
    }

    private GameState checkIfGameIsCompleted() {
        // check if any of the diagonals are comprised of all the same character
        if (board[0][0] != 0 && board[0][0] == board[1][1] && board[1][1] == board[2][2])
            return GameState.COMPLETED;

        if (board[0][2] != 0 && board[0][2] == board[1][1] && board[1][1] == board[2][0])
            return GameState.COMPLETED;

        // check if any of the rows are comprised of all the same character
        for(int r = 0; r < BOARD_SIZE; r++) {
            if (board[r][0] != 0 && board[r][0] == board[r][1] && board[r][1] == board[r][2])
                return GameState.COMPLETED;
        }

        // check if any of the columns are comprised of all the same character
        for(int c = 0; c < BOARD_SIZE; c++) {
            if (board[0][c] != 0 && board[0][c] == board[1][c] && board[1][c] == board[2][c])
                return GameState.COMPLETED;
        }

        if (emptySpots == 0) {
            return GameState.COMPLETED_DRAW;
        }
        return this.state;
    }

    private char nextPlayer() {
        return currentPlayer == PLAYER_X ? PLAYER_O : PLAYER_X;
    }

    @Override
    public char getValue(int row, int col) {
        assert row < board.length;
        assert board.length >0;
        assert col < board[0].length;
        return board[row][col];
    }

    @Override
    public void setValue(int row, int col) {
        if (state != GameState.RUNNING) {
            return;
        }
        if (board[row][col] != 0) {
            throw new IllegalArgumentException("Row " + row + " and col " + col + " already has a piece");
        }
        board[row][col] = currentPlayer;
        emptySpots--;
        state = checkIfGameIsCompleted();
        if (state == GameState.RUNNING) {
            currentPlayer = nextPlayer();
        }
        setChanged();
        notifyObservers(this);
        clearChanged();
    }

    @Override
    public char getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public GameState getState() {
        return this.state;
    }

    @Override
    public String winnerName() {
        // the winner is the same as the current player
        return (state == GameState.COMPLETED)? getCurrentPlayerName() : null;
    }

    @Override
    public String getCurrentPlayerName() {
        if (currentPlayer == 'X') return player1Name;
        return player2Name;
    }

    public void setPlayer1Name(String playerName) {
        this.player1Name = playerName == null ? "Player #1" : playerName;
    }

    public void setPlayer2Name(String playerName) {
        this.player2Name = playerName == null ? "Player #2" : playerName;
    }
}
