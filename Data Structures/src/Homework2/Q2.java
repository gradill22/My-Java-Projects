package Homework2;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a positive number: ");
        int num = input.nextInt();
        int prod = num;
        for(int i = 2; i < num; i++) {
            prod *= i;
        }
        System.out.printf("%d! = %d", num, prod);
    }
}