package FreePractice;

import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a hex character: ");
        char hex = input.nextLine().toLowerCase().charAt(0);
        switch(hex) {
            case '0' -> System.out.println("The decimal value is 0");
            case '1' -> System.out.println("The decimal value is 1");
            case '2' -> System.out.println("The decimal value is 2");
            case '3' -> System.out.println("The decimal value is 3");
            case '4' -> System.out.println("The decimal value is 4");
            case '5' -> System.out.println("The decimal value is 5");
            case '6' -> System.out.println("The decimal value is 6");
            case '7' -> System.out.println("The decimal value is 7");
            case '8' -> System.out.println("The decimal value is 8");
            case '9' -> System.out.println("The decimal value is 9");
            case 'a' -> System.out.println("The decimal value is 10");
            case 'b' -> System.out.println("The decimal value is 11");
            case 'c' -> System.out.println("The decimal value is 12");
            case 'd' -> System.out.println("The decimal value is 13");
            case 'e' -> System.out.println("The decimal value is 14");
            case 'f' -> System.out.println("The decimal value is 15");
            default -> System.out.println("Invalid input");
        }
    }
}