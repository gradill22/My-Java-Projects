package Homework1;

import java.util.Scanner;

public class Q5 {
    public static void main(String[] args) {
        int length = 5;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter %d integers\n", length);
        int[] nums = new int[length];
        for(int i = 0; i < length; i ++) {
            System.out.printf("n%d = ", i + 1);
            nums[i] = input.nextInt();
        }
        System.out.printf("Largest number: %d\n", maxNum(nums));
        System.out.printf("Smallest number: %d", minNum(nums));
    }

    private static int maxNum(int[] nums) {
        int largest = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] > largest) {
                largest = nums[i];
            }
        }
        return largest;
    }

    private static int minNum(int[] nums) {
        int smallest = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < smallest) {
                smallest = nums[i];
            }
        }
        return smallest;
    }
}