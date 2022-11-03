package Homework4;

/*
Implement the LinearEquation class. Write a test program that prompts the user to enter a, b, c, d, e, and f and
displays the result. If ad - bc is 0, report that "The equation has no solution."
*/

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("a = ");
        double a = input.nextInt();
        System.out.print("b = ");
        double b = input.nextInt();
        System.out.print("c = ");
        double c = input.nextInt();
        System.out.print("d = ");
        double d = input.nextInt();
        System.out.print("e = ");
        double e = input.nextInt();
        System.out.print("f = ");
        double f = input.nextInt();
        LinearEquation le = new LinearEquation(a, b, c, d, e, f);
        if(le.isSolvable()) {
            System.out.printf("x = %.2f\ny = %.2f", le.getX(), le.getY());
        } else {
            System.out.println("The equation has no solution.");
        }
    }
}