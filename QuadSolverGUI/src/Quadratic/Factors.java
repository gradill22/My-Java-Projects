package Quadratic;

public class Factors {
    public static String factors(Quadratic q) {
        double a = q.getA();
        double b = q.getB();
        double c = q.getC();
        double sqrt = Math.sqrt((b * b) - (4 * a * c));
        double posInt = (-b + sqrt) / (a * 2);
        double negInt = (-b - sqrt) / (a * 2);
        String factor;
        if(getGCF(a, b, c) > 1) {
            factor = Quadratic.formNum(getGCF(a, b, c)) + "(x ";
        } else {
            factor = "(x ";
        }
        if (posInt < 0) {
            factor += "+ ";
        } else {
            factor += "- ";
        }
        factor += Quadratic.formNum(Math.abs(posInt)) + ")(x ";
        if (negInt < 0) {
            factor += "+ ";
        } else {
            factor += "- ";
        }
        factor += Quadratic.formNum(Math.abs(negInt)) + ")";
        return factor;
    }

    public static String psFactors(Quadratic q) {
        double a = q.getA();
        double b = q.getB();
        double c = q.getC();
        String psFactor = "";
        if((Math.sqrt(a / getGCF(a, b, c)) * Math.sqrt(c / getGCF(a, b, c)) * 2) == (Math.abs(b / getGCF(a, b, c)))) {
            double psA = Math.sqrt(a / getGCF(a, b, c));
            double psB = Math.sqrt(c / getGCF(a, b, c));
            if(getGCF(a, b, c) > 1) {
                psFactor = getGCF(a, b, c) + "(";
            } else {
                psFactor = "(";
            }
            if(psA != 1.0) {
                psFactor += Quadratic.formNum(psA);
            }
            if(b < 0) {
                psFactor += "x - ";
            } else {
                psFactor += "x + ";
            }
            psFactor += Quadratic.formNum(psB) + ")^2";
        }
        return psFactor;
    }

    private static double lowestNum(double a, double b, double c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        if(a <= b && a <= c) {
            return a;
        } else if(b <= a && b <= c) {
            return b;
        }
        return c;
    }

    public static double getGCF(double a, double b, double c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        double lowestInt = lowestNum(a, b, c);
        double gcf = 1;
        while(lowestInt > gcf) {
            if(lowestInt % a == 0 || a % lowestInt == 0) {
                if(lowestInt % b == 0 || b % lowestInt == 0) {
                    if(lowestInt % c == 0 || c % lowestInt == 0) {
                        gcf = lowestInt;
                    }
                }
            }
            lowestInt--;
        }
        return gcf;
    }
}