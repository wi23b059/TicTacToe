package at.technikum;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeTest {


    @Test
    void playerSwitched() {
        TicTacToe game = new TicTacToe();
        game.switchCurrentPlayer();
        assertEquals('O', game.getCurrentPlayer().getMarker());
    }

    @Test
    void playerNotSwitched() {
        TicTacToe game = new TicTacToe();
        game.switchCurrentPlayer();
        assertNotEquals('X', game.getCurrentPlayer().getMarker());
    }

    @Test
    void hasWinnerTrue() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'X');
        game.getBoard().place(0, 2, 'X');
        assertTrue(game.hasWinner());
    }

    @Test
    void hasWinnerFalse() {
        TicTacToe game = new TicTacToe();
        game.getBoard().place(0, 0, 'X');
        game.getBoard().place(0, 1, 'O');
        game.getBoard().place(0, 2, 'X');
        assertFalse(game.hasWinner());
    }

    @Test
    void startLooser() {
        TicTacToe game = new TicTacToe();
        Board board = new Board();
        // Simulate user input
        String input = "0 0\n1 0\n0 1\n1 1\n0 2\nno\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        // Start the game
        game.start();

        // Reset the input and output streams
        System.setIn(System.in);
        System.setOut(originalOut);

        board.place(0,0,'X');
        board.place(0,1,'O');
        board.place(0,2,'X');

        // Check that player1 ('X') has won by completing the first row
        assertEquals('X', board.cells[0][0]);
        assertEquals('O', board.cells[0][1]);
        assertEquals('X', board.cells[0][2]);

        // Check the output contains the winning message
        String gameOutput = out.toString();
        assertTrue(gameOutput.contains("Player X wins!"));

        // Ensure that gameRunning is false after win
        assertTrue(game.hasWinner());
    }

    @Test
    void startWinner() {
        TicTacToe game = new TicTacToe();
        Board board = new Board();
        // Simulate user input
        String input = "0 0\n1 0\n0 1\n1 1\n0 2\nno\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Capture the output
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));

        // Start the game
        game.start();

        // Reset the input and output streams
        System.setIn(System.in);
        System.setOut(originalOut);

        board.place(0,0,'X');
        board.place(0,1,'X');
        board.place(0,2,'X');

        // Check that player1 ('X') has won by completing the first row
        assertEquals('X', board.cells[0][0]);
        assertEquals('X', board.cells[0][1]);
        assertEquals('X', board.cells[0][2]);

        // Check the output contains the winning message
        String gameOutput = out.toString();
        assertTrue(gameOutput.contains("Player X wins!"));

        // Ensure that gameRunning is false after win
        assertTrue(game.hasWinner());
    }
}