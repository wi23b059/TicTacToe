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

    @Test
    void printOK() {
        Board board = new Board();
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                board.cells[i][j] = 'X';
            }
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));

        try {
            // Call the print method
            board.print();

            // Get the output and check if it contains the expected content
            String output = outContent.toString();
            assertTrue(output.contains("X"));
        } finally {
            // Restore the original System.out
            System.setOut(originalOut);
        }
    }

        @Test
        void printNOK () {
            Board board = new Board();
            for (int i = 0; i < board.cells.length; i++) {
                for (int j = 0; j < board.cells[i].length; j++) {
                    board.cells[i][j] = 'X';
                }
            }
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outContent));

            try {
                // Call the print method
                board.print();

                // Get the output and check if it contains the expected content
                String output = outContent.toString();
                assertFalse(output.contains("O"));
            } finally {
                // Restore the original System.out
                System.setOut(originalOut);
            }
        }
    }