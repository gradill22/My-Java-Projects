package FreePractice;

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a number between 0 and 15: ");
        int num = input.nextInt();
        if(num >= 0 && num <= 15) {
            System.out.print("The hex value is ");
            switch(num) {
                default -> System.out.print(num);
                case 10 -> System.out.print("A");
                case 11 -> System.out.print("B");
                case 12 -> System.out.print("C");
                case 13 -> System.out.print("D");
                case 14 -> System.out.print("E");
                case 15 -> System.out.print("F");
            }
        } else {
            System.out.print("Invalid input");
        }
    }
}