package Binary;
import java.util.ArrayList;

public class Binary{
    public static String toText(String binaryString){
        StringBuilder theText = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < binaryString.length(); i++){
            if(Character.isWhitespace(binaryString.charAt(i))){
                theText.append((char) binaryToDecimal(temp.toString()));
                temp = new StringBuilder();
            }
            else{
                temp.append(binaryString.charAt(i));
            }
        }
        return theText.toString();
    }

    public static int toDecimal(int binaryInt){
        int currentExponent = Integer.toString(binaryInt).length() - 1;
        int decimalValue = 0;

        for(int i = 0; i < Integer.toString(binaryInt).length(); i++){
            int placeValue = (int) Math.pow(2, currentExponent);
            char currentDigit = Integer.toString(binaryInt).charAt(i);
            int digitValue = Character.getNumericValue(currentDigit);
            decimalValue += digitValue * placeValue;
            currentExponent--;
        }
        return decimalValue;
    }

    public static String toBinary(String text){
        ArrayList<String> binary = new ArrayList<String>();
        for(int i = 0; i < text.length(); i++){
            binary.add(Integer.toBinaryString((int) text.charAt(i)));
        }
        StringBuilder output = new StringBuilder();
        for (String s : binary){
            output.append(s).append(" ");
        }
        return output.toString();
    }

    public static String toBinary(int binaryInt){
        return Integer.toBinaryString(binaryInt);
    }

    private static int binaryToDecimal(String binaryString){
        int currentExponent = binaryString.length() - 1;
        int decimalValue = 0;
        for(int i = 0; i < binaryString.length(); i++){
            int placeValue = (int) Math.pow(2, currentExponent);
            char currentDigit = binaryString.charAt(i);
            int digitValue = Character.getNumericValue(currentDigit);
            decimalValue += digitValue * placeValue;
            currentExponent--;
        }
        return decimalValue;
    }
}