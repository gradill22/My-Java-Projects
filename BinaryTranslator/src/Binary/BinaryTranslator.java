package Binary;
import java.util.Scanner;

public class BinaryTranslator{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.print("To text/number or to Binary: ");
        String option = input.nextLine();
        System.out.println();
        switch (option.toLowerCase()) {
            case "text/number" -> {
                System.out.print("Text or number: ");
                String str = input.nextLine();
                if (str.toLowerCase().equals("text")) {
                    System.out.print("Binary: ");
                    String text = input.nextLine();
                    System.out.println(Binary.toText(text));
                } else if (str.toLowerCase().equals("number")) {
                    System.out.print("Binary: ");
                    int digit = input.nextInt();
                    System.out.println(Binary.toDecimal(digit));
                } else {
                    System.out.println("ERROR: Invalid input");
                    System.out.println("Please input either \"text\" or \"number\"");
                }
            }
            case "text" -> {
                System.out.print("Binary: ");
                String text = input.nextLine();
                System.out.println(Binary.toText(text));
            }
            case "number" -> {
                System.out.print("Binary: ");
                int digit = input.nextInt();
                System.out.println(Binary.toDecimal(digit));
            }
            case "binary" -> {
                System.out.print("Text or Number: ");
                String str = input.nextLine();
                if (str.toLowerCase().equals("text")) {
                    System.out.print("Text: ");
                    String text = input.nextLine();
                    System.out.println("Binary of \"" + text + "\": ");
                    System.out.println(Binary.toBinary(text));
                } else if (str.toLowerCase().equals("number")) {
                    System.out.print("Number: ");
                    int digit = input.nextInt();
                    System.out.println("Binary of " + digit + ": ");
                    System.out.println(Binary.toBinary(digit));
                } else {
                    System.out.println("ERROR: Invalid input");
                    System.out.println("Please input either \"text\" or \"number\"");
                }
            }
            default -> {
                System.out.println("ERROR: Invalid input");
                System.out.println("Please input either \"text,\" \"number,\" or \"binary\"");
            }
        }
    }
}