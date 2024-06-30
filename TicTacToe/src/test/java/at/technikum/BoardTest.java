package at.technikum;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void isCellEmptyYes() {
        Board board = new Board();
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                board.cells[i][j] = ' ';
            }
            assertTrue(board.isCellEmpty(1, 1));
        }
    }

    @Test
    void isCellEmptyNo() {
        Board board = new Board();
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                board.place(i, j, 'O');
            }
        }
        assertFalse(board.isCellEmpty(1, 1));
    }

    @Test
    void isMarkerO() {
        Board board = new Board();
        board.clear();
        board.place(2, 2, 'O');
        assertEquals('O', board.cells[2][2]);
    }

    @Test
    void isMarkerX() {
        Board board = new Board();
        board.clear();
        board.place(2, 2, 'X');
        assertEquals('X', board.cells[2][2]);
    }

    @Test
    void isFull() {
        Board board = new Board();
        board.clear();
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                board.cells[i][j] = 'X';
            }
        }
        assertTrue(board.isFull());
    }

    @Test
    void isNotFull() {
        Board board = new Board();
        board.clear();
        assertFalse(board.isFull());
    }

    @Test
    void isClear() {
        Board board = new Board();
        board.clear();
        assertEquals(' ', board.cells[2][2]);
    }

    @Test
    void isNotClear() {
        Board board = new Board();
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                board.cells[i][j] = 'X';
            }
        }
        assertEquals('X', board.cells[2][2]);
    }

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Redirect System.out to capture printed output
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    // Restore original System.out
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void printOK() {
        Board board = new Board();
        setUpStreams();

        // Call the print method
        board.print();

        // Define expected output
        String lineSeparator = System.lineSeparator();
        String expectedOutput = " | | " + lineSeparator + "-----" + lineSeparator + " | | " + lineSeparator + "-----" + lineSeparator + " | | " + lineSeparator;

        // Assert that printed output matches expected output
        assertEquals(expectedOutput, outContent.toString());

        restoreStreams();
    }

    @Test
    void printNOK () {
        Board board = new Board();
        setUpStreams();

        // Call the print method (making a change for failure)
        board.cells[1][1] = 'X'; // Simulate a change to cell [1][1]

        // Call the print method
        board.print();

        // Define expected output (expected to fail due to the change)
        String lineSeparator = System.lineSeparator();
        String expectedOutput = " | | " + lineSeparator + "-----" + lineSeparator + " | | " + lineSeparator + "-----" + lineSeparator + " | | " + lineSeparator;

        // Assert that printed output does NOT match expected output
        assertNotEquals(expectedOutput, outContent.toString());

        restoreStreams();

        //TicTacToe game = new TicTacToe();
        //game.start();
        //assertFalse(game.getBoard().isFull());
    }
}