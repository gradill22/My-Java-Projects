package Homework2;

import java.util.Scanner;

public class Q4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the number of lines: ");
        int num_lines = input.nextInt();
        if(num_lines >= 1 && num_lines <= 15) {
            for(int i = 1; i <= num_lines; i++) {
                String next_line = "";
                int j = i;
                while(j > 0) {
                    next_line += String.format("%d ", j);
                    j -= 1;
                }
                j = 2;
                while(j <= i) {
                    next_line += String.format("%d ", j);
                    j += 1;
                }
                next_line = next_line.substring(0, next_line.length() - 1);
                int length = 33 - next_line.length() / 2;
                for(int k = 0; k <= length; k++) {
                    System.out.print(" ");
                }
                System.out.print(next_line + "\n");
            }
        }
    }
}