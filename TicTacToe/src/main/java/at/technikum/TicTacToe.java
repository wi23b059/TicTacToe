package at.technikum;

import java.util.Scanner;

public class TicTacToe {
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Board board;

    public TicTacToe() {
        player1 = new Player('X');
        player2 = new Player('O');
        currentPlayer = player1;
        board = new Board();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean gameRunning = true;

        while (gameRunning) {
            board.print();
            System.out.println("Player " + currentPlayer.getMarker() + ", enter your move (row and column): ");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (board.isCellEmpty(row, col)) {
                board.place(row, col, currentPlayer.getMarker());
                if (hasWinner()) {
                    board.print();
                    System.out.println("Player " + currentPlayer.getMarker() + " wins!");
                    gameRunning = false;
                } else if (board.isFull()) {
                    board.print();
                    System.out.println("The game is a draw!");
                    gameRunning = false;
                } else {
                    switchCurrentPlayer();
                }
            } else {
                System.out.println("This cell is already taken. Try again.");
            }

            if (!gameRunning) {
                System.out.println("Do you want to play again? (yes/no)");
                String response = scanner.next();
                if (response.equalsIgnoreCase("yes")) {
                    board.clear();
                    currentPlayer = player1;
                    gameRunning = true;
                }
            }
        }
        scanner.close();
    }

    public void switchCurrentPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean hasWinner() {
        char marker = currentPlayer.getMarker();
        for (int i = 0; i < 3; i++) {
            if (board.cells[i][0] == marker && board.cells[i][1] == marker && board.cells[i][2] == marker) {
                return true;
            }
            if (board.cells[0][i] == marker && board.cells[1][i] == marker && board.cells[2][i] == marker) {
                return true;
            }
        }
        if (board.cells[0][0] == marker && board.cells[1][1] == marker && board.cells[2][2] == marker) {
            return true;
        }
        if (board.cells[0][2] == marker && board.cells[1][1] == marker && board.cells[2][0] == marker) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.start();
    }
}
