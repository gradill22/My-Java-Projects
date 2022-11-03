package Homework2;

import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double total_miles = 0;
        double total_gallons = 0;
        double gallons;
        System.out.print("Enter miles (-1 to quit): ");
        double miles = input.nextInt();
        while(miles != -1) {
            System.out.print("Enter gallons: ");
            gallons = input.nextInt();
            System.out.printf("MPG this tankful: %.2f\n", (miles / gallons));
            total_miles += miles;
            total_gallons += gallons;
            System.out.printf("Total MPG: %.2f\n", (total_miles / total_gallons));
            System.out.print("Enter miles (-1 to quit): ");
            miles = input.nextInt();
        }
    }
}