package Homework3;

public class RectangleDriver {
    public static void main(String[] args) {
        Rectangle r1 = new Rectangle(4, 40);
        Rectangle r2 = new Rectangle(3.5, 35.9);
        printRectangle(r1);
        printRectangle(r2);
    }

    private static void printRectangle(Rectangle r) {
        System.out.printf("Width: %.1f\nHeight: %.1f\nArea: %.1f\nPerimeter: %.1f\n", r.getWidth(), r.getHeight(), r.getArea(), r.getPerimeter());
    }
}