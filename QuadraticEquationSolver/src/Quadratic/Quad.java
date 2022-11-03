package Quadratic;

public class Quad {
    private static double a;
    private static double b;
    private static double c;
    private static double axisOfSymmetry;

    public Quad(double a, double b, double c) {
        Quad.a = a;
        Quad.b = b;
        Quad.c = c;
    }

    private double getYInt()
    {
        return c;
    }

    private double getPosInt() {
        double negativeB = (b * -1);
        double squaredB = (b * b);
        double fourAC = (a * c * 4);
        double twoA = (a * 2);
        double sqrt = squaredB - fourAC;
        double base = Math.sqrt(sqrt);
        if(sqrt >= 0) {
            return ((negativeB + base) / twoA);
        } else {
            return 0;
        }
    }

    private double getNegInt() {
        double negativeB = (b * -1);
        double squaredB = (b * b);
        double fourAC = (a * c * 4);
        double twoA = (a * 2);
        double sqrt = squaredB - fourAC;
        double base = Math.sqrt(sqrt);
        if(sqrt >= 0) {
            return ((negativeB - base) / twoA);
        } else {
            return 0;
        }
    }

    private double getAOS() {
        double negativeB = (b * -1);
        double twoA = (a * 2);
        axisOfSymmetry = (negativeB / twoA);
        return axisOfSymmetry;
    }

    private double getVertex() {
        double x = axisOfSymmetry;
        double squaredX = (x * x);
        double squaredXA = (a * squaredX);
        double bTimesX = (b * x);
        return (squaredXA + bTimesX + c);
    }

    public void print() {
        System.out.println("Y-Intercept: (0, " + formNum(getYInt()) + ")");
        if(getPosInt() != 0 && getNegInt() != 0) {
            if(getPosInt() == getNegInt()) {
                System.out.println("X-Intercepts: (" + formNum(getPosInt()) + ", 0)");
            } else {
                System.out.println("X-Intercepts: (" + formNum(getPosInt()) + ", 0), (" + formNum(getNegInt()) + ", 0)");
            }
        } else if(getPosInt() == 0 && getNegInt() != 0) {
            System.out.println("X-Intercept: (" + formNum(getNegInt()) + ", 0)");
        } else if(getPosInt() != 0) {
            System.out.println("X-Intercept: (" + formNum(getPosInt()) + ", 0)");
        } else {
            System.out.println("X-Intercepts: None");
        }
        System.out.println("Axis of Symmetry: x = " + formNum(getAOS()));
        System.out.println("Vertex: (" + formNum(getAOS()) + ", " + formNum(getVertex()) + ")");
        System.out.println("Domain: {x | x ∈ ℝ)");
        if(a > 0) {
            System.out.println("Range: {y | y ≥ " + formNum(getVertex()) + "}");
            System.out.println("Min or Max: Minimum");
        }
        if(a < 0)
        {
            System.out.println("Range: {y | y ≤ " + formNum(getVertex()) + "}");
            System.out.println("Min or Max: Maximum");
        }
    }

    private String formNum(double i) {
        if(Double.toString(i).charAt(0) == '-') {
            if(Double.toString(i + 0.005).length() >= 5) {
                if(Double.toString(i + 0.005).charAt(4) == '.') {
                    return Double.toString(i + 0.05).substring(0, 6);
                } else {
                    return Double.toString(i + 0.005).substring(0, 5);
                }
            } else if(Double.toString(i + 0.05).length() == 4) {
                return Double.toString(i + 0.05).substring(0, 4);
            } else {
                return Double.toString(i);
            }
        } else {
            if(Double.toString(i + 0.005).length() >= 4) {
                if(Double.toString(i + 0.005).charAt(3) == '.') {
                    return Double.toString(i + 0.05).substring(0, 5);
                } else {
                    return Double.toString(i + 0.005).substring(0, 4);
                }
            } else if(Double.toString(i + 0.05).length() == 3) {
                return Double.toString(i + 0.05).substring(0, 3);
            } else {
                return Double.toString(i);
            }
        }
    }

    public String toString() {
        return "0 = " + a + "x^2 + " + b + "x + " + c;
    }
}