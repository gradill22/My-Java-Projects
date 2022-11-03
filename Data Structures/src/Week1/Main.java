package Week1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("First number: ");
        int num1 = input.nextInt();
        System.out.print("Second number: ");
        int num2 = input.nextInt();
        int sum = num1 + num2;
        if(num1 > num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        System.out.printf("%d + %d = %d", num1, num2, sum);
    }
}