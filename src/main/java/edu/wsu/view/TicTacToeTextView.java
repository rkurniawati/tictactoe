package edu.wsu.view;

import edu.wsu.model.TicTacToeGame;
import edu.wsu.model.TicTacToeImplementation;

import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import static edu.wsu.model.TicTacToeImplementation.*;

public class TicTacToeTextView implements Observer {

    private final PrintStream out;

    public TicTacToeTextView(PrintStream out) {
        this.out = out;
    }

    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof TicTacToeImplementation;
        assert arg instanceof TicTacToeGame;

        TicTacToeGame gameInfo = (TicTacToeGame) arg;
        printHUD(gameInfo);
        printBoard(gameInfo);
    }

    private void printHUD(TicTacToeGame gameInfo) {
        out.println("\nTic Tac Toe game");
        switch (gameInfo.getState()) {
            case NOT_STARTED:
                out.println("Game not started");
                return;
            case COMPLETED_DRAW:
                out.println("Game over, NO winner (draw)");
                break;
            case COMPLETED:
                out.println("Game over, winner: " + gameInfo.winnerName());
                break;
            default:
                assert gameInfo.getState() == GameState.RUNNING;
                out.println("Current player: " + gameInfo.getCurrentPlayerName() + "("  + gameInfo.getCurrentPlayer() + ")");
                break;
        }
        assert gameInfo.getState() != GameState.NOT_STARTED;
    }

    private void printBoard(TicTacToeGame gameInfo) {
        out.println("Board:");
        for (int r = 0; r < BOARD_SIZE; r++) {
            for (int c = 0; c < BOARD_SIZE; c++) {
                switch(gameInfo.getValue(r, c)) {
                    case PLAYER_X:
                        out.print(" x ");
                        break;
                    case PLAYER_O:
                        out.print(" o ");
                        break;
                    default:
                        out.print(" - ");
                        break;
                }
            }
            out.println();
        }
        out.println();
    }
}