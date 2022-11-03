package AllGradesCalculator;

import java.util.Scanner;
import java.util.ArrayList;

public class Grade {
    static Scanner input = new Scanner(System.in);

    public static void getClassGrade() {
        System.out.print("Amount of grade types (e.g. Homework, Test, etc.): ");
        double gradeCount = input.nextDouble();
        System.out.println();
        ArrayList<Double> grades = new ArrayList<>();
        double totalWeight = 0;

        for(int i = 1; i <= gradeCount; i++){
            double weight;
            if(gradeCount == 1){
                weight = 100;
            } else {
                System.out.print("Grade type " + i + " weight: ");
                weight = input.nextDouble();
            }
            totalWeight += weight;
            System.out.print("Grade type " + i + " earned points: ");
            double earnedPoints = input.nextDouble();
            System.out.print("Grade type " + i + " possible points: ");
            double possiblePoints = input.nextDouble();
            double grade = ((earnedPoints / possiblePoints) * weight);
            grades.add(grade);
            System.out.println();
        }

        if(totalWeight == 100) {
            double totalGrade = 0;
            for(double g : grades) {
                totalGrade += g;
            }
            if(totalGrade < 100) {
                System.out.print("Class grade: " + Double.toString(totalGrade + 0.05).substring(0, 4));
            } else {
                System.out.print("Class grade: " + Double.toString(totalGrade + 0.05).substring(0, 5));
            }
            System.out.print("% (" + getLetterGrade(totalGrade) + ")");
        } else {
            System.out.println("Cannot compute. Please try again.");
        }
    }

    public static void getFinalGrade() {
        System.out.println("First semester: ");
        System.out.print("First quarter grade: ");
        double firstQ = input.nextDouble();
        System.out.print("Second quarter grade: ");
        double secondQ = input.nextDouble();
        System.out.print("Midterm grade: ");
        double midterm = input.nextDouble();
        System.out.print("Midterm weight: ");
        double midtermWeight = input.nextDouble();
        System.out.println();
        System.out.println("Second semester: ");
        System.out.print("Third quarter grade: ");
        double thirdQ = input.nextDouble();
        System.out.print("Fourth quarter grade: ");
        double fourthQ = input.nextDouble();
        System.out.print("Final exam grade: ");
        double finalExam = input.nextDouble();
        System.out.print("Final exam weight: ");
        double finalExamWeight = input.nextDouble();
        System.out.println();
        System.out.print("Quarter weight: ");
        double qWeight = input.nextDouble();
        System.out.println();

        double firstQWeighted = firstQ * (qWeight / 100);
        double secondQWeighted = secondQ * (qWeight / 100);
        double thirdQWeighted = thirdQ * (qWeight / 100);
        double fourthQWeighted = fourthQ * (qWeight / 100);
        double finalQ = (firstQWeighted + secondQWeighted + thirdQWeighted + fourthQWeighted);

        double finalMidterm = midterm * (midtermWeight / 100);
        double finalFinal = finalExam * (finalExamWeight / 100);

        double finalGrade = (finalQ + finalMidterm + finalFinal) + 0.05;
        System.out.println("Final grade: " + Double.toString(finalGrade).substring(0, 4) + "% (" + getLetterGrade(finalGrade) + ")");
    }

    public static void getShortGPA() {
        System.out.print("Amount of classes enrolled in: ");
        double classCount = input.nextInt();
        ArrayList<Double> grade = new ArrayList<>();
        double totalBoost = 0;

        if(classCount >= 1 && classCount <= 8) {
            for(int i = 1; i <= classCount; i++){
                System.out.print("\nPeriod " + i + " grade: ");
                double theGrade = input.nextDouble();
                grade.add(theGrade);
                System.out.print("Boost: ");
                double theBoost = input.nextDouble();
                totalBoost += theBoost;
            }
            double weightedFinalGPA = totalBoost;
            double unweightedFinalGPA = 0;
            for(Double aDouble : grade) {
                weightedFinalGPA += Grade.getGPA(aDouble);
                unweightedFinalGPA += Grade.getGPA(aDouble);
            }
            weightedFinalGPA /= classCount;
            unweightedFinalGPA /= classCount;
            if(totalBoost != 0) {
                if(Double.toString(weightedFinalGPA).length() >= 4) {
                    System.out.println("\nWeighted GPA: " + Double.toString(weightedFinalGPA).substring(0, 4));
                } else {
                    System.out.println("\nWeighted GPA: " + weightedFinalGPA);
                }

                if(Double.toString(unweightedFinalGPA).length() >= 4) {
                    System.out.println("Unweighted GPA: " + Double.toString(unweightedFinalGPA).substring(0, 4));
                } else {
                    System.out.println("Unweighted GPA: " + unweightedFinalGPA);
                }
            } else {
                if(Double.toString(weightedFinalGPA).length() >= 4) {
                    System.out.println("\nGPA: " + Double.toString(weightedFinalGPA).substring(0, 4));
                } else {
                    System.out.println("\nGPA: " + weightedFinalGPA);
                }
            }
        } else {
            System.out.println("\nInvalid input. Please try again.");
        }
    }

    public static void getLongGPA() {
        double firstYear;
        double secondYear = 0;
        double thirdYear = 0;
        double fourthYear = 0;
        System.out.print("What grade are you in? ");
        String year = input.nextLine();

        int grade = switch (year.toUpperCase()) {
            case "FRESHMAN" -> 1;
            case "SOPHOMORE", "SOPHMORE" -> 2;
            case "JUNIOR" -> 3;
            case "SENIOR" -> 4;
            default -> 0;
        };

        System.out.println();

        if(grade > 0) {
            System.out.print("GPA freshman year: ");
            firstYear = input.nextDouble();
            if(grade >= 2) {
                System.out.print("GPA sophomore year: ");
                secondYear = input.nextDouble();
            }
            if(grade >= 3) {
                System.out.print("GPA junior year: ");
                thirdYear = input.nextDouble();
            }
            if(grade == 4) {
                System.out.print("GPA senior year: ");
                fourthYear = input.nextDouble();
            }
            double finalGPA = ((firstYear + secondYear + thirdYear + fourthYear) / grade) + 0.005;
            System.out.println("\nGPA: " + Double.toString(finalGPA).substring(0, 4));
        } else {
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static String getLetterGrade(double grade) {
        grade += 0.005;
        if(grade >= 100.45) {
            return "A+";
        } else if(grade <= 100.44 && grade >= 92.45) {
            return "A";
        } else if(grade <= 92.44 && grade >= 89.45) {
            return "A-";
        } else if(grade <= 89.44 && grade >= 86.45) {
            return "B+";
        } else if(grade <= 86.44 && grade >= 82.45) {
            return "B";
        } else if(grade <= 82.44 && grade >= 79.45) {
            return "B-";
        } else if(grade <= 79.44 && grade >= 76.45) {
            return "C+";
        } else if(grade <= 76.44 && grade >= 72.45) {
            return "C";
        } else if(grade <= 72.44 && grade >= 69.45) {
            return "C-";
        } else if(grade <= 69.44 && grade >= 66.45) {
            return "D+";
        } else if(grade <= 66.44 && grade >= 63.45) {
            return "D";
        } else {
            return "F";
        }
    }

    private static double getGPA(double grade) {
        if(grade >= 92.45) {
            return 4.0;
        } else if(grade <= 92.44 && grade >= 89.45) {
            return 3.7;
        } else if(grade <= 89.44 && grade >= 86.45) {
            return 3.3;
        } else if(grade <= 86.44 && grade >= 82.45) {
            return 3.0;
        } else if(grade <= 82.44 && grade >= 79.45) {
            return 2.7;
        } else if(grade <= 79.44 && grade >= 76.45) {
            return 2.3;
        } else if(grade <= 76.44 && grade >= 72.45) {
            return 2.0;
        } else if(grade <= 72.44 && grade >= 69.45) {
            return 1.7;
        } else if(grade <= 69.44 && grade >= 66.45) {
            return 1.3;
        } else if(grade <= 66.44 && grade >= 64.45) {
            return 1.0;
        }
        return 0.0;
    }
}