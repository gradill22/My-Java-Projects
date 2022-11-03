package Homework1;

import java.util.Random;
import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Heads or tails: ");
        String guess = input.nextLine();
        if(guess.equalsIgnoreCase("Heads") || guess.equalsIgnoreCase("Tails")) {
            Random r = new Random();
            String coin = "Heads";
            if(r.nextInt(2) == 1) {
                coin = "Tails";
            }
            if(guess.equalsIgnoreCase(coin)) {
                System.out.printf("Correct! It was %s!", coin);
            } else {
                System.out.printf("Incorrect. It was %s.", coin);
            }
        } else {
            System.out.printf("Invalid input. Please try again.\n\"%s\"", guess);
        }
    }
}