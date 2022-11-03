package Lecture3;

public class SquareRoot {
    public static void main(String[] args) {
        int n = 4;
        System.out.println(squareRoot(n));
        System.out.println(Math.sqrt(n));
    }

    public static double squareRoot(double n) {
        double lastGuess, nextGuess;
        double precision = 0.00000001;

        lastGuess = 1;
        nextGuess = (lastGuess + n / lastGuess) / 2;
        while(Math.abs(lastGuess - nextGuess) > precision) {
            lastGuess = nextGuess;
            nextGuess = (lastGuess + n / lastGuess) / 2;
        }
        return nextGuess;
    }
}