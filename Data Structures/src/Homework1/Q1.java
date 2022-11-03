package Homework1;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a, b, c: ");
        String[] nums = input.nextLine().split(" ");
        double a = Double.parseDouble(nums[0]);
        double b = Double.parseDouble(nums[1]);
        double c = Double.parseDouble(nums[2]);
        double d = disc(a, b, c);
        if(d > 0) {
            System.out.printf("The roots are %f and %f", root1(a, b, c), root2(a, b, c));
        } else if (d == 0) {
            System.out.printf("The root is %f", root1(a, b, c));
        } else {
            System.out.println("The equation has no real roots");
        }
    }

    private static double root1(double a, double b, double c) {
        return (-b + Math.sqrt(disc(a, b, c))) / (a * 2);
    }

    private static double root2(double a, double b, double c) {
        return (-b - Math.sqrt(disc(a, b, c))) / (a * 2);
    }

    private static double disc(double a, double b, double c) {
        return (b * b) - (a * c * 4);
    }
}