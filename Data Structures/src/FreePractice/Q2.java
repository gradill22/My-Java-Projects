package FreePractice;

import java.util.Scanner;

public class Q2 {
    public static void main(String[] args) {
        int circleRadius = 10;
        Scanner input = new Scanner(System.in);
        System.out.print("x = ");
        double x = input.nextDouble();
        System.out.print("y = ");
        double y = input.nextDouble();
        System.out.printf("Point (%.1f, %.1f) is ", x, y);
        if(x * x + y * y <= circleRadius * circleRadius) {
            System.out.print("in the circle");
        } else {
            System.out.print("not in the circle");
        }
    }
}