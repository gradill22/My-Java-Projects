package Homework1;

import java.util.Scanner;

public class Q7 {
    public static void main(String[] args) {
        int length = 5;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter a %d-digit number: ", length);
        String num = input.nextLine();
        while(num.length() != 5) {
            System.out.printf("Number must be %d digits long.\nEnter a %d-digit number: ", length, length);
            num = input.nextLine();
        }
        if(isPalindrome(num)) {
            System.out.printf("%s is a palindrome!", num);
        } else {
            System.out.printf("%s is not a palindrome.", num);
        }
    }

    private static boolean isPalindrome(String seq) {
        int s = 0;
        int e = seq.length() - 1;
        for(int i = 0; i < seq.length() / 2; i++) {
            if(!(seq.charAt(s) == seq.charAt(e))) {
                return false;
            }
            s += 1;
            e -= 1;
        }
        return true;
    }
}