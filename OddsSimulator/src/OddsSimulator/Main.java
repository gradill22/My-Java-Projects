package OddsSimulator;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Double> arr = new ArrayList<>();
        int loop = 750000000;
        for(int i = 0; i < 10000; i++) {
            double pearls = 0;
            for(int j = 0; j < loop; j++) {
                if(Math.random() <= 0.047) {
                    pearls++;
                }
            }
            arr.add((pearls / loop) * 100);
        }
        radixSort(arr);
        print(arr);
    }

    private static double getMax(ArrayList<Double> arr) {
        double max = arr.get(0);
        for(double i : arr) {
            if(i > max) {
                max = i;
            }
        }
        return max;
    }

    private static void countSort(ArrayList<Double> arr, int exp) {
        double[] output = new double[arr.size()];
        double[] count = new double[10];
        Arrays.fill(count, 0);
        for(double i : arr) {
            count[(int) ((i / exp) % 10)]++;
        }
        for(int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for(int i = arr.size() - 1; i >= 0; i--) {
            output[(int) (count[(int) ((arr.get(i) / exp) % 10)] - 1)] = arr.get(i);
            count[(int) ((arr.get(i) / exp) % 10)]--;
        }
        for(int i = 0; i < arr.size(); i++) {
            arr.set(i, output[i]);
        }
    }

    private static void radixSort(ArrayList<Double> arr) {
        for(int exp = 1; getMax(arr) / exp > 0; exp *= 10) {
            countSort(arr, exp);
        }
    }

    private static void print(ArrayList<Double> arr) {
        System.out.print("[");
        for(int i = 0; i < arr.size() - 1; i++) {
            System.out.print(Double.toString(arr.get(i)).substring(0, 4) + "%, ");
        }
        System.out.print(arr.get(arr.size() - 1) + "]");
    }
}