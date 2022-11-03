package Homework2;

import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter numbers: ");
        String[] nums = input.nextLine().split(" ");
        int max = Integer.parseInt(nums[0]);
        int count = 1;
        for(int i = 1; i < nums.length; i++) {
            int num = Integer.parseInt(nums[i]);
            if(num > max) {
                max = num;
                count = 1;
            } else if(num == max) {
                count += 1;
            }
        }
        System.out.printf("The largest number is %d\nThe occurrence count of the largest number is %d", max, count);
    }
}