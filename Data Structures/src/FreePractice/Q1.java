package FreePractice;

import java.util.Scanner;

public class Q1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter year: ");
        int year = input.nextInt();
        System.out.print("Enter month (1-12): ");
        int month = input.nextInt();
        if(month == 1 || month == 2) {
            year -= 1;
            month += 12;
        }
        System.out.print("Enter day of month (1-31): ");
        int day = input.nextInt();
        int k = year % 100;
        int j = Math.floorDiv(year, 100);
        int dayOfWeek = (day + (13 * (month + 1) / 5) + k + (k / 4) + (j / 4) + (j * 5)) % 7;
        System.out.print("Day of the week is ");
        switch (dayOfWeek) {
            case 0 -> System.out.print("Saturday");
            case 1 -> System.out.print("Sunday");
            case 2 -> System.out.print("Monday");
            case 3 -> System.out.print("Tuesday");
            case 4 -> System.out.print("Wednesday");
            case 5 -> System.out.print("Thursday");
            case 6 -> System.out.print("Friday");
        }
    }
}