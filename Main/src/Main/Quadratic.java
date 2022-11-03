package Main;

public class Quadratic {
    private final int a;
    private final int b;
    private final int c;
    private final double negInt;
    private final double posInt;

    public Quadratic(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
        negInt = getNegInt();
        posInt = getPosInt();
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }

    public double getNegInt() {
        return (-b - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public double getPosInt() {
        return (-b + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    }

    public double getDiscriminant() {
        return (b * b) - (4 * a * c);
    }

    public int getRealSolutions() {
        if(getDiscriminant() > 0) {
            return 2;
        } else if(getDiscriminant() < 0) {
            return 0;
        }
        return 1;
    }

    public static String factors(Quadratic quad) {
        return Factors.factors(quad);
    }

    public static String psFactors(Quadratic quad) {
        return Factors.psFactors(quad);
    }

    public String toString() {
        String qEquation = a + "x^2 + " + b + "x + " + c;
        String output = "There ";
        if(getRealSolutions() == 2) {
            output += "are " + getRealSolutions() + " real solutions to the quadratic " + qEquation + " which are " + negInt + " and " + posInt + ".";
        } else if(getRealSolutions() == 1) {
            output += "is " + getRealSolutions() + " real solution to the quadratic " + qEquation + " which is " + negInt + ".";
        } else {
            output += "are " + getRealSolutions() + " real solutions to the quadratic " + qEquation + ". All solutions are imaginary.";
        }
        if(getRealSolutions() > 0) {
            output += " The factored quadratic is: ";
            if(psFactors(this).length() > 0) {
                output += psFactors(this);
            } else {
                output += factors(this);
            }
        }
        return output;
    }
}