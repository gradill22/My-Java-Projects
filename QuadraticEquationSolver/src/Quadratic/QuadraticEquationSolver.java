package Quadratic;
import java.util.Scanner;

public class QuadraticEquationSolver{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("For the equation ax^2 + bx + c = 0, input");
        System.out.print("a: ");
        double a = input.nextDouble();

        if(a != 0) {
            System.out.print("b: ");
            double b = input.nextDouble();
            System.out.print("c: ");
            double c = input.nextDouble();
            Quad quad = new Quad(a, b, c);
            System.out.println("\nEquation: " + quad);
            System.out.println();
            quad.print();
        } else {
            System.out.println();
            System.out.println("Invalid input. Please try again.");
            System.out.println("Error: a = 0");
        }
    }
}