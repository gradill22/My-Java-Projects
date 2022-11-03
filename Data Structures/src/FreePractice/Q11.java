package FreePractice;

import java.util.Scanner;

public class Q11 {
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int numStudents = userInt("Number of students: ");
        double highest1 = 0;
        double highest2 = 0;
        String name1 = "";
        String name2 = "";
        for(int i = 0; i < numStudents; i++) {
            String name = userString("Name of student: ");
            double grade = userDouble("Grade: ");
            if(grade > highest1) {
                highest2 = highest1;
                highest1 = grade;
                name2 = name1;
                name1 = name;
            } else if(grade > highest2) {
                highest2 = grade;
                name2 = name;
            }
        }
        System.out.println(name1 + " had the highest grade in the class with a " + highest1);
        System.out.println(name2 + " had the second highest grade in the class with a " + highest2);
    }

    private static int userInt(String prompt) {
        System.out.print(prompt);
        return input.nextInt();
    }

    private static String userString(String prompt) {
        System.out.print(prompt);
        input.nextLine();
        return input.nextLine();
    }

    private static double userDouble(String prompt) {
        System.out.print(prompt);
        return input.nextDouble();
    }
}