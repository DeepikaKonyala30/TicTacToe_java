/*
 A simple 2-player Tic Tac Toe game played in Command Line.
 Players take turns in making their moves. Until one wins.
 The game continues until one player wins or it's a draw.
 */
import java.util.*;

public class TicTacToe {
    // Game board
    static char board[][] = new char[3][3];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean again = true;
        //Rules
        System.out.println("=== Welcome to Tic Tac Toe! ===");
        System.out.println("How to Play:");
        System.out.println("1. The game is played on a 3x3 board.");
        System.out.println("2. Enter the row and column number (0, 1, or 2) to make a move.");
        System.out.println("3. You cannot place your symbol on an occupied cell.");
        System.out.println("4. Player 1 chooses X or O — Player 2 gets the remaining one.");
        System.out.println("5. Get 3 in a row or column or diagonal to win — or it's a draw if the board fills!");
        //Each game loop
         while (again) {
            System.out.print("\nEnter Player 1 name: ");
            String player1 = sc.next();
            System.out.print("Enter Player 2 name: ");
            String player2 = sc.next();
            System.out.print(player1 + ", choose your symbol (X/O): ");
            char p1Symbol = sc.next().charAt(0);
            p1Symbol = Character.toUpperCase(p1Symbol);
            while (p1Symbol != 'X' && p1Symbol != 'O') {
                System.out.print("Invalid choice! Please choose X or O: ");
                p1Symbol = sc.next().charAt(0);
                p1Symbol = Character.toUpperCase(p1Symbol);
            }
            char p2Symbol = (p1Symbol == 'X') ? 'O' : 'X';
            String currPlayer = player1;
            char currSymbol = p1Symbol;
            System.out.println("Game has started!Player 1: " + player1 + " (" + p1Symbol + "), Player 2: " + player2 + " (" + p2Symbol + ")");

            // Initializing board
            initializeB();

            while (true) {
                //printing board after every move
                printBoard();
                int row = -1, col = -1;
                while (true) {
                    //taking input
                    System.out.print(currPlayer + "'s turn (" + currSymbol + "). Enter row and column: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("Please enter NUMBERS only (0, 1, or 2).");
                        sc.nextLine();
                        continue;
                    }
                    row = sc.nextInt();
                    if (!sc.hasNextInt()) {
                        System.out.println("Please enter NUMBERS only (0, 1, or 2).");
                        sc.nextLine();
                        continue;
                    }
                    col = sc.nextInt();
                    sc.nextLine();
                    if (row < 0 || row > 2 || col < 0 || col > 2) {
                        System.out.println("Please provide valid indices (0, 1, or 2).");
                        continue;
                    }
                    if (board[row][col] != ' ') {
                        System.out.println("You can only write in empty cells!");
                        continue;
                    }
                    break;
                }
                board[row][col] = currSymbol;
                //checking for win or any draw possible
                if (Win(currSymbol)) {
                    printBoard();
                    System.out.println(currPlayer + " wins!");
                    break;
                }
                if (Draw()) {
                    printBoard();
                    System.out.println("It's a draw!");
                    break;
                }
                //switching players
                if (currPlayer.equals(player1)) {
                    currPlayer = player2;
                    currSymbol = p2Symbol;
                } else {
                    currPlayer = player1;
                    currSymbol = p1Symbol;
                }
            }
            System.out.print("Do you want to play again? (y/n): ");
            char choice = sc.next().charAt(0);
            again = (choice == 'y' || choice == 'Y');
        }
        System.out.println("Thanks for playing!");
    }

    public static void initializeB() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public static void printBoard() {
        System.out.println("    0   1   2");
        System.out.println("  -------------");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " |");
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
            System.out.println("  -------------");
        }
    }

    public static boolean Win(char player) {
        for (int i = 0; i < 3; i++) {
            //row and column check for winning
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true;
            }
        }
        //diagonal check for winning
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
            (board[0][2] == player && board[1][1] == player && board[2][0] == player)){
                return true;
            }
        return false;
    }

    public static boolean Draw() {
        //checking for empty cells
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') 
                    return false;
            }
        }
        return true;
    }
}

