import java.util.Arrays;
import java.util.Scanner;

public class TicTacToe {
    private static final String[][] plays = new String[3][3];

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // fill matrix with non-null Strings
        for(String[] row : plays) Arrays.fill(row, " ");
        String[] players = {"X", "O"};
        int turn = 0;
        printBoard();
        while(!(checkWinner().equals("X") || checkWinner().equals("O")) && playable()) {
            String chr = players[turn % 2];
            String rowPrompt = "Player " + chr + ": Enter row (0, 1, or 2): ";
            String colPrompt = "Player " + chr + ": Enter row (0, 1, or 2): ";
            System.out.println("Player " + chr + "'s turn.");
            System.out.print(rowPrompt);
            int row = input.nextInt();
            System.out.print(colPrompt);
            int col = input.nextInt();
            while(!plays[row][col].equals(" ")) { // to make sure the same spot isn't played twice
                System.out.println("Player " + plays[row][col] + " already played there.");
                System.out.print(rowPrompt);
                row = input.nextInt();
                System.out.print(colPrompt);
                col = input.nextInt();
            }
            plays[row][col] = chr;
            turn++;
            printBoard();
        }
        String winner = checkWinner();
        if(winner.equals("X") || winner.equals("O")) {
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println(winner);
        }
    }

    private static void printBoard() {
        System.out.println(" _________________ ");
        for(String[] play : plays) {
            System.out.println("|     |     |     |");
            System.out.println("|  " + play[0] + "  |  " + play[1] + "  |  " + play[2] + "  |");
            System.out.println("|_____|_____|_____|");
        }
    }

    private static boolean foundWinner(String[] row) {
        for(String play: row) {
            if(play.equals(" ")) return false;
        }
        return row[0].equals(row[1]) && row[1].equals(row[2]);
    }

    private static String checkWinner() {
        // check rows
        for(String[] row: plays) {
            if(foundWinner(row)) return row[0];
        }
        // check columns
        for(int i = 0; i < plays[0].length; i++) {
            String[] col = new String[3];
            for(int j = 0; j < col.length; j++) col[j] = plays[j][i];
            if(foundWinner(col)) return col[0];
        }
        // check diagonals
        String[] diag1 = new String[plays.length];
        String[] diag2 = new String[plays.length];
        for(int i = 0; i < plays.length; i++) {
            diag1[i] = plays[i][i];
            diag2[i] = plays[plays.length - i - 1][i];
        }
        if(foundWinner(diag1)) return diag1[0];
        if(foundWinner(diag2)) return diag2[0];
        return "Game is a draw.";
    }

    private static boolean playable() {
        for(String[] row: plays) {
            for(String play: row) {
                if(play.equals(" ")) return true;
            }
        }
        return false;
    }
}