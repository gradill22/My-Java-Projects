import java.util.Arrays;
import java.util.Scanner;

public class ExtremeTicTacToe {
    private static final String[] activeChars = "XOZAD".substring(0, 2).split("");
    private static final int SIZE = activeChars.length * 2 - 1; // felt like making this scalable to any square size
    private static final String[][] plays = new String[SIZE][SIZE];

    public static void main(String[] args) {
        // fill matrix with non-null Strings
        for(String[] row : plays) Arrays.fill(row, " ");
        int turn = 0;
        printBoard();
        while(!(checkWinner().equals(activeChars[0]) || checkWinner().equals(activeChars[1])) && turn < SIZE * SIZE) {
            String chr = activeChars[turn % activeChars.length];
            String rowPrompt = "Player " + chr + ": Enter row (1-" + SIZE + "): ";
            String colPrompt = "Player " + chr + ": Enter row (1-" + SIZE + "): ";
            System.out.println("Player " + chr + "'s turn.");
            System.out.print(rowPrompt);
            int row = getInput(rowPrompt);
            System.out.print(colPrompt);
            int col = getInput(colPrompt);
            while(!plays[row][col].equals(" ")) {
                System.out.println("Player " + plays[row][col] + " already played there.");
                System.out.print(rowPrompt);
                row = getInput(rowPrompt);
                System.out.print(colPrompt);
                col = getInput(colPrompt);
            }
            plays[row][col] = chr;
            turn++;
            printBoard();
        }
        String winner = checkWinner();
        if(!winner.equals("Game is a draw.")) {
            System.out.println("Player " + winner + " wins!");
        } else {
            System.out.println(winner);
        }
    }

    private static int getInput(String prompt) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt() - 1;
        while(n < 0 || n > SIZE - 1) {
            System.out.println("Improper input. Try again.");
            System.out.print(prompt);
            n = input.nextInt() - 1;
        }
        return n;
    }

    private static void printBoard() {
        printTopEdge();
        for(String[] play : plays) {
            printSpace();
            printPlays(play);
            printEdge();
        }
    }

    private static void printTopEdge() {
        System.out.print(" ");
        for(int i = 0; i < plays.length - 1; i++) System.out.print("______");
        System.out.println("_____ ");
    }

    private static void printEdge() {
        for(int i = 0; i < plays.length; i++) System.out.print("|_____");
        System.out.println("|");
    }

    private static void printSpace() {
        for(int i = 0; i < plays.length; i++) System.out.print("|     ");
        System.out.println("|");
    }

    private static void printPlays(String[] play) {
        for(String p: play) System.out.print("|  " + p + "  ");
        System.out.println("|");
    }

    private static boolean foundWinner(String[] row) {
        for(String play: row) {
            if(play.equals(" ")) return false;
        }
        int index = 1;
        while(row[index].equals(row[index - 1]) && index < row.length - 1) {
            index++;
        }
        return index == row.length - 1;
    }

    private static String checkWinner() {
        // check rows
        for(String[] row: plays) {
            if(foundWinner(row)) return row[0];
        }
        // check columns
        for(int i = 0; i < plays[0].length; i++) {
            String[] col = new String[SIZE];
            for(int j = 0; j < col.length; j++) col[j] = plays[j][i];
            if(foundWinner(col)) return col[0];
        }
        // check diagonals
        String[] d1 = new String[plays.length];
        String[] d2 = new String[plays.length];
        for(int i = 0; i < plays.length; i++) {
            d1[i] = plays[i][i];
            d2[i] = plays[plays.length - i - 1][i];
        }
        if(foundWinner(d1)) return d1[0];
        if(foundWinner(d2)) return d2[0];
        return "Game is a draw.";
    }
}