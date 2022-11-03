package FreePractice;

public class Q6 {
    public static void main(String[] args) {
        final double KILO_PER_MILE = 1.609;
        int space = 15;
        System.out.printf("%-" + space + "s%-" + space + "s%-" + space + "s%s\n", "Miles", "Kilometers", "Kilometers", "Miles");
        for(int miles = 1; miles <= 10; miles++) {
            double k1 = miles * KILO_PER_MILE;
            int k2 = miles * 5 + 15;
            double m2 = k2 / KILO_PER_MILE;
            System.out.printf("%-" + space + "d%-" + space + ".3f%-" + space + "d%.3f\n", miles, k1, k2, m2);
        }
    }
}