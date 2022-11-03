package Lecture3;

public class RussianMultiplication {
    public static void main(String[] args) {
        System.out.println(getProduct(42, 35));
    }

    public static int getProduct(int num1, int num2) {
        int sum = 0;
        if(num1 < num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }
        while(num1 != 0) {
            if(num1 % 2 == 1) {
                sum += num2;
            }
            num1 /= 2;
            num2 *= 2;
        }
        return sum;
    }
}