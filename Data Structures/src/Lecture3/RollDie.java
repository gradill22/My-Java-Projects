package Lecture3;

import java.util.Random;

public class RollDie {
    public static void main(String[] args) {
        Random r = new Random();

        int frequency1 = 0;
        int frequency2 = 0;
        int frequency3 = 0;
        int frequency4 = 0;
        int frequency5 = 0;
        int frequency6 = 0;

        int face;

        for(int roll = 0; roll < 6000; roll++) {
            face = r.nextInt(6) + 1;
            switch(face) {
                case 1:
                    frequency1++;
                    break;
                case 2:
                    frequency2++;
                    break;
                case 3:
                    frequency3++;
                    break;
                case 4:
                    frequency4++;
                    break;
                case 5:
                    frequency5++;
                    break;
                case 6:
                    frequency6++;
            }
        }

        System.out.println("Face      Frequency");
        System.out.printf("%-10d%d\n", 1, frequency1);
        System.out.printf("%-10d%d\n", 2, frequency2);
        System.out.printf("%-10d%d\n", 3, frequency3);
        System.out.printf("%-10d%d\n", 4, frequency4);
        System.out.printf("%-10d%d\n", 5, frequency5);
        System.out.printf("%-10d%d", 6, frequency6);
    }
}