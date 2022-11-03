package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Length of array: ");
        int length = scan.nextInt();
        int[] arr = new int[length];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        System.out.print("Find index of: ");
        int num = scan.nextInt();
        if(num > 0 && num < arr.length) {
            System.out.println("Binary iterations: " + binaryIterations(arr, num));
            System.out.println("Selection iterations: " + selectionIterations(arr, num));
        }
    }

    private static int binaryIterations(int[] arr, int num) {
        int high = arr.length;
        int mid = arr.length / 2;
        int low = arr[0];
        int loops = 0;
        while(mid != num) {
            if(mid > num) {
                high = mid;
            } else {
                low = mid;
            }
            mid = ((high - low) / 2) + low;
            loops++;
        }
        return loops;
    }

    private static int selectionIterations(int[] arr, int num) {
        int loops = 0;
        for(int i : arr) {
            loops++;
            if(i == num) {
                return loops;
            }
        }
        return -1;
    }
}