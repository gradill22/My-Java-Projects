package Lecture3;

public class JackSprat {
    public static void main(String[] args) {
        int n = 11;
        if(isJackSprat(n)) {
            System.out.printf("%d is Jack Sprat", n);
        } else {
            System.out.printf("%d is not Jack Sprat", n);
        }
    }

    public static int sumOfDivisors(int n) {
        int sum = 0;
        for(int i = 1; i <= n; i++) {
            if(n % i == 0) {
                sum += i;
            }
        }
        return sum;
    }

    public static boolean isFat(int n) {
        return sumOfDivisors(n) > 3 * n;
    }

    public static boolean isLean(int n) {
        return sumOfDivisors(n) == n + 1;
    }

    public static boolean isJackSprat(int n) {
        return isFat(n + 1) && isLean(n);
    }
}