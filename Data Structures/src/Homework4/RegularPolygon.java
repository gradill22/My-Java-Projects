package Homework4;

/*
Design a class named RegularPolygon that contains:
- A private int data field named "n" that defines the number of sides in the polygon with a default value of 3
- A private double data field named "side" that stores the length of the side with default value 1
- A private double data field named "x" that defines the x-coordinate of the center of the polygon with default value 0
- A private double data field named "y" that defines the y-coordinate of the center of the polygon with default value 0
- A no-arg constructor that creates a regular polygon with default values
- A constructor that creates a regular polygon with the specified number of sides and length of side, centered at (0, 0)
- A constructor that creates a regular polygon with the specified number of sides, length of side, and
x-and-y-coordinates
The accessor and mutator methods for all data fields
The method getPerimeter() that returns the perimeter of the polygon
The method getArea() that returns the area of the polygon
    - Formula: (n * s^2) / (4 * Math.tan(p / n))
*/

public class RegularPolygon {
    private int n;
    private double side, x, y;

    public RegularPolygon(int numSides, double sideLength, double x, double y) {
        this.n = numSides;
        this.side = sideLength;
        this.x = x;
        this.y = y;
    }

    public RegularPolygon(int numSides, double sideLength) {
        this.n = numSides;
        this.side = sideLength;
        this.x = 0;
        this.y = 0;
    }

    public RegularPolygon() {
        this.n = 3;
        this.side =  1;
        this.x = 0;
        this.y = 0;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getPerimeter() {
        return n * side;
    }

    public double getArea() {
        return (n * side * side) / (4 * Math.tan(getPerimeter() / n));
    }

    public String toString() {
        return String.format("Number of sides: %d\nLength of sides: %.2f\nx-coordinate: %.2f\ny-coordinate: %.2f\n" +
                "Perimeter: %.2f\nArea: %.2f\n", n, side, x, y, getPerimeter(), getArea());
    }
}