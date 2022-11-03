package Homework1;

import java.util.Scanner;

public class Q6 {
    public static void main(String[] args) {
        int length = 5;
        Scanner input = new Scanner(System.in);
        System.out.printf("Enter %d numbers\n", length);
        double[] nums = new double[length];
        for (int i = 0; i < length; i++) {
            System.out.printf("n%d = ", i + 1);
            nums[i] = input.nextDouble();
        }
        int[] counts = new int[3];
        for(double n: nums) {
            if(n < 0) {
                counts[0] += 1;
            } else if(n > 0){
                counts[2] += 1;
            } else {
                counts[1] += 1;
            }
        }
        System.out.printf("Number of negative numbers: %d\n", counts[0]);
        System.out.printf("Number of zeros: %d\n", counts[1]);
        System.out.printf("Number of positive numbers: %d", counts[2]);
    }
}