package Main;

public class Factors {
    public static String factors(Quadratic q) {
        int a = q.getA();
        int b = q.getB();
        int c = q.getC();
        double sqrt = Math.sqrt((b * b) - (4 * a * c));
        double posInt = (-b + sqrt) / (a * 2);
        double negInt = (-b - sqrt) / (a * 2);
        int posIntDecimal = String.valueOf(posInt).indexOf('.') + 1;
        int negIntDecimal = String.valueOf(negInt).indexOf('.') + 1;
        String factor = "unavailable since the solutions are not integers.";
        if(Double.toString(posInt).charAt(posIntDecimal) == '0' && Double.toString(negInt).charAt(negIntDecimal) == '0') {
            if(getGCF(a, b, c) > 0) {
                factor = getGCF(a, b, c) + "(x ";
            } else {
                factor = "(x ";
            }
            if(posInt < 0) {
                factor += "+ ";
            } else {
                factor += "- ";
            }
            factor += Math.abs((int) posInt) + ")(x ";
            if(negInt < 0) {
                factor += "+ ";
            } else {
                factor += "- ";
            }
            factor += Math.abs((int) negInt) + ")";
        }
        return factor;
    }

    public static String psFactors(Quadratic q) {
        int a = q.getA();
        int b = q.getB();
        int c = q.getC();
        String psFactor = "";
        if((Math.sqrt((double) a / (double) getGCF(a, b, c)) * Math.sqrt((double) c / (double) getGCF(a, b, c)) * 2) == (Math.abs(b / getGCF(a, b, c)))) { // a^2 + 2ab + b^2
            double psA = Math.sqrt((double) a / (double) getGCF(a, b, c));
            double psB = Math.sqrt((double) c / (double) getGCF(a, b, c));
            if(getGCF(a, b, c) > 1) {
                psFactor = getGCF(a, b, c) + "(";
            } else {
                psFactor = "(";
            }
            if(psA != 1.0) {
                psFactor += psA;
            }
            if(b < 0) {
                psFactor += "x - ";
            } else {
                psFactor += "x + ";
            }
            psFactor += psB + ")^2";
        }
        return psFactor;
    }

    private static int lowestInt(int a, int b, int c) {
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

    public static int getGCF(int a, int b, int c) {
        a = Math.abs(a);
        b = Math.abs(b);
        c = Math.abs(c);
        int lowestInt = lowestInt(a, b, c);
        int gcf = 1;
        while(lowestInt > 1) {
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