package Quadratic;

public class Quadratic {
    private static double a, b, c;

    public Quadratic(double a, double b, double c) {
        Quadratic.a = a;
        Quadratic.b = b;
        Quadratic.c = c;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getYInt() {
        return c;
    }

    public double getPosInt() {
        if((b * b) - (4 * a * c) >= 0) {
            return ((-b + Math.sqrt((b * b) - (4 * a * c))) / (a * 2));
        } else {
            return 0;
        }
    }

    public double getNegInt() {
        if((b * b) - (4 * a * c) >= 0) {
            return ((-b - Math.sqrt((b * b) - (4 * a * c))) / (a * 2));
        } else {
            return 0;
        }
    }

    public double getAxisOfSymmetry() {
        return -b / (a * 2);
    }

    public double getVertex() {
        double x = getAxisOfSymmetry();
        return (a * x * x) + (b * x) + c;
    }

    public static String formNum(double i) {
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
}