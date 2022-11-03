package Homework4;

/*
Implement the QuadraticEquation class. Write a test program that prompts the user to enter values for a, b, and c and
displays the result based on the discriminant. If the discriminant is positive, display the two roots. If the
discriminant is 0, display the one root. Otherwise, display "The equation has no roots."
*/

import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        String[] nums = input.nextLine().split(" ");
        double a = Integer.parseInt(nums[0]);
        double b = Integer.parseInt(nums[1]);
        double c = Integer.parseInt(nums[2]);
        QuadraticEquation qe = new QuadraticEquation(a, b, c);
        if(qe.getDiscriminant() > 0) {
            System.out.printf("The roots are %.3f and %.3f", qe.getRoot1(), qe.getRoot2());
        } else if(qe.getDiscriminant() == 0) {
            System.out.printf("The root is %.3f", qe.getRoot1());  // can call getRoot2() for same result
        } else {
            System.out.println("The equation has no roots.");
        }
    }
}