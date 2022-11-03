package RegressionCalculator;

import java.util.ArrayList;

public class Regression {
    public static ArrayList<Double> xData;
    private static ArrayList<Double> yData;
    private double linA, linB, quadA, quadB, quadC;

    public Regression() {
        xData = new ArrayList<>();
        yData = new ArrayList<>();
    }

    public void addX(double x) {
        xData.add(x);
    }

    public void addY(double y) {
        yData.add(y);
    }

    public String toLinString() {
        if(xData.size() <= 1) {
            return "";
        } else {
            int n = xData.size();
            double sumY = 0;
            for(Double i : yData) {
                sumY += i;
            }
            double sumX = 0;
            for(Double i : xData) {
                sumX += i;
            }
            double sumX2 = 0;
            for(double i : xData) {
                sumX2 += (i * i);
            }
            double sumXY = 0;
            for(int i = 0; i < n; i++) {
                sumXY += (xData.get(i) * yData.get(i));
            }
            linA = ((n * sumXY) - (sumX * sumY)) / ((n * sumX2) - (sumX * sumX));
            linB = ((sumY * sumX2) - (sumX * sumXY)) / ((n * sumX2) - (sumX * sumX));
            return "y = " + formNum(linA) + "x + " + formNum(linB) + "; r^2 = " + formNum(getR2(1));
        }
    }

    public String toQuadString() {
        if(xData.size() <= 2) {
            return "";
        } else {
            int n = xData.size();
            double sumX = 0;
            for(double i : xData) {
                sumX += i;
            }
            double sumX2 = 0;
            for(double i : xData) {
                sumX2 += (i * i);
            }
            double sumX3 = 0;
            for(double i : xData) {
                sumX3 += (i * i * i);
            }
            double sumX4 = 0;
            for(double i : xData) {
                sumX4 += (i * i * i * i);
            }
            double sumXY = 0;
            for(int i = 0; i < n; i++) {
                sumXY += (xData.get(i) * yData.get(i));
            }
            double sumX2Y = 0;
            for(int i = 0; i < n; i++) {
                sumX2Y += (xData.get(i) * xData.get(i) * yData.get(i));
            }
            double sumY = 0;
            for(double i : yData) {
                sumY += i;
            }
            double sumXX = (sumX2 - ((sumX * sumX) / n));
            double sumXnY = (sumXY - ((sumX * sumY) / n));
            double sumXX2 = (sumX3 - ((sumX2 * sumX) / n));
            double sumX2nY = (sumX2Y - ((sumX2 * sumY) / n));
            double sumX2X2 = (sumX4 - ((sumX2 * sumX2) / n));

            quadA = ((sumX2nY * sumXX) - (sumXnY * sumXX2)) / ((sumXX * sumX2X2) - (sumXX2 * sumXX2));
            quadB = ((sumXnY * sumX2X2) - (sumX2nY * sumXX2)) / ((sumXX * sumX2X2) - (sumXX2 * sumXX2));
            quadC = (sumY / n) - (quadB * (sumX / n)) - (quadA * (sumX2 / n));
            return "y = " + formNum(quadA) + "x^2 + " + formNum(quadB) + "x + " + formNum(quadC) + "; r^2 = " + formNum(getR2(2));
        }
    }

    private double getR2(int num) {
        int n = xData.size();
        double[] newY = new double[n];
        if(num == 1) {
            for(int i = 0; i < n; i++){
                newY[i] = ((linA * xData.get(i)) + linB);
            }
        }
        if(num == 2) {
            for(int i = 0; i < n; i++) {
                newY[i] = (quadA * (xData.get(i) * xData.get(i)) + (quadB * xData.get(i)) + quadC);
            }
        }
        double mean = 0;
        for(double i : newY) {
            mean += i;
        }
        mean /= n;
        double rss = 0;
        for(int i = 0; i < n; i++){
            rss += ((yData.get(i) - newY[i]) * (yData.get(i) - newY[i]));
        }
        double tss = 0;
        for(int i = 0; i < n; i++) {
            tss += ((yData.get(i) - mean) * (yData.get(i) - mean));
        }
        double r2 = 1 - (rss / tss);
        if(tss == 0) {
            return 1.0;
        } else {
            return r2;
        }
    }

    private String formNum(double n) {
        if(n > 0) {
            if(Double.toString(n).length() >= 5) {
                return Double.toString(n).substring(0, 5);
            } else {
                return Double.toString(n);
            }
        } else {
            if(Double.toString(n).length() >= 6) {
                return Double.toString(n).substring(0, 6);
            } else {
                return Double.toString(n);
            }
        }
    }
}