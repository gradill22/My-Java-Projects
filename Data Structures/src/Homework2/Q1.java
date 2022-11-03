package Homework2;

public class Q1 {
    public static void main(String[] args) {
        final double POUNDS_PER_KILO = 2.2;
        System.out.printf("%-15s%-15s%-15s%s\n", "Kilograms", "Pounds", "Pounds", "Kilograms");
        for(int kilo = 1; kilo <= 199; kilo += 2) {
            double p1 = kilo * POUNDS_PER_KILO;
            int p2 = 20 + (kilo / 2) * 5;
            double k2 = p2 / POUNDS_PER_KILO;
            System.out.printf("%-15d%-15.1f%-15d%.2f\n", kilo, p1, p2, k2);
        }
    }
}