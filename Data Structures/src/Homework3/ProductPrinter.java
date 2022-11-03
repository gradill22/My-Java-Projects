package Homework3;

public class ProductPrinter {
    public static void main(String[] args) {
        Product p1 = new Product("Toaster", 29.95);
        Product p2 = new Product("Printer", 395.95);
        System.out.printf("The %s costs %.2f\n", p1.getName(), p1.getPrice());
        System.out.printf("The %s costs %.2f\n", p2.getName(), p2.getPrice());
        p1.reducePrice(5);
        p2.reducePrice(5);
        System.out.printf("The %s now costs %.2f\n", p1.getName(), p1.getPrice());
        System.out.printf("The %s now costs %.2f\n", p2.getName(), p2.getPrice());
    }
}