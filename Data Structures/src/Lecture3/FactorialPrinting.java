package Lecture3;

import java.util.Scanner;

public class FactorialPrinting {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter end of range: ");
        int rangeEnd = input.nextInt();

        for(int i = 2; i <= rangeEnd; i++) {
            printFactorial(i);
        }
    }

    public static void printFactorial(int n) {
        System.out.print(n + "! = ");
        int factorial = 1;
        for(int i = n; i > 1; i--) {
            factorial *= i;
            System.out.print(i + " * ");
        }
        System.out.println("1 = " + factorial);
    }
}