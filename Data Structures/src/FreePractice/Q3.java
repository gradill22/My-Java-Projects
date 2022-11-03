package FreePractice;

import java.util.Scanner;

public class Q3 {
    public static void main(String[] args) {
        final double WIDTH = 10;
        final double HEIGHT = 5;
        Scanner input = new Scanner(System.in);
        System.out.print("x = ");
        double x = input.nextDouble();
        System.out.print("y = ");
        double y = input.nextDouble();
        System.out.printf("Point (%.1f, %.1f) is ", x, y);
        if(Math.abs(x) <= WIDTH / 2 && Math.abs(y) <= HEIGHT / 2) {
            System.out.print("in the rectangle");
        } else {
            System.out.print("not in the rectangle");
        }
    }
}