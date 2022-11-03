package Homework3;

public class StockDriver {
    public static void main(String[] args) {
        Stock s1 = new Stock("JAVA", "Sun Microsystems Inc", 4.5);
        s1.setCurrentPrice(4.35);
        System.out.println(s1);
    }
}