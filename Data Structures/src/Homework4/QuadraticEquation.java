package Homework4;

/*
Design a class named QuadraticEquation for a quadratic equation ax^2 + bx + c = 0. The class contains:
- Private data fields a, b, and c that represents three coefficients
- A constructor for the arguments a, b, c
- Three get methods for a, b, and c
- A method named getDiscriminant() that returns the discriminant, which is b^2 - 4ac
- The methods getRoot1() and getRoot2() for returning two roots of the equation
    - These methods are useful only if the discriminant is non-negative. Let these methods return 0 if the discriminant
    is negative
*/

public class QuadraticEquation {
    private double a, b, c;

    public QuadraticEquation(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getDiscriminant() {
        return b * b - 4 * a * c;
    }

    public double getRoot1() {
        if(getDiscriminant() >= 0) {
            return (-b + Math.sqrt(getDiscriminant())) / (2 * a);
        }
        return 0;
    }

    public double getRoot2() {
        if(getDiscriminant() > 0) {
            return (-b - Math.sqrt(getDiscriminant())) / (2 * a);
        }
        return getRoot1();
    }
}