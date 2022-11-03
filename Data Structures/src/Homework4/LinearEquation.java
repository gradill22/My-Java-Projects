package Homework4;

/*
Design a class named LinearEquation for a 2x2 system of linear equations:
ax + by = e     x = (ed - bf) / (ad - bc)
cx + dy = f     y = (af - ec) / (ad - bc)
The class contains:
- Private data fields a, b, c, d, e, and f
- A constructor with the arguments for a, b, c, d, e, and f
- Six get methods for a, b, c, d, e, and f
- A method named isSolvable() that returns true if ad - bc is not 0
- Methods getX() and getY() that return the solution for the equation
*/

public class LinearEquation {
    private double a, b, c, d, e, f;

    public LinearEquation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
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

    public double getD() {
        return d;
    }

    public void setD(double d) {
        this.d = d;
    }

    public double getE() {
        return e;
    }

    public void setE(double e) {
        this.e = e;
    }

    public double getF() {
        return f;
    }

    public void setF(double f) {
        this.f = f;
    }

    public boolean isSolvable() {
        return a * d != b * c;
    }

    public double getX() {
        if(isSolvable()) {
            return (e * d - b * f) / (a * d - b * c);
        }
        return 0;
    }

    public double getY() {
        if(isSolvable()) {
            return (a * f - e * c) / (a * d - b * c);
        }
        return 0;
    }
}