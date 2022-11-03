package AllGradesCalculator;
import java.util.Scanner;

public class AllGradesCalculator {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("1) Quarter grade");
        System.out.println("2) Final grade");
        System.out.println("3) GPA for the quarter/year");
        System.out.println("4) GPA for all of high school ");
        System.out.print("What would you like to calculate? ");
        int option = input.nextInt();

        System.out.println();

        if(option == 1){
            Grade.getClassGrade();
        } else if(option == 2){
            Grade.getFinalGrade();
        } else if(option == 3){
            Grade.getShortGPA();
        } else if(option == 4){
            Grade.getLongGPA();
        } else{
            System.out.println("Invalid input. Please try again.");
        }
    }
}