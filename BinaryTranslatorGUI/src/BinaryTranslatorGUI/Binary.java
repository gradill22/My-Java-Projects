package BinaryTranslatorGUI;

import java.util.ArrayList;

public class Binary {
    public static String toText(String binaryString) {
        StringBuilder theText = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for(int i = 0; i < binaryString.length(); i++) {
            if(Character.isWhitespace(binaryString.charAt(i))) {
                theText.append((char) binaryToDecimal(temp.toString()));
                temp = new StringBuilder();
            } else {
                temp.append(binaryString.charAt(i));
            }
        }
        if(theText.toString().isBlank()) {
            theText.append((char) binaryToDecimal(temp.toString()));
        }
        return theText.toString();
    }

    public static String toBinary(String text) {
        ArrayList<String> binary = new ArrayList<>();
        for(int i = 0; i < text.length(); i++) {
            binary.add(Integer.toBinaryString(text.charAt(i)));
        }
        StringBuilder output = new StringBuilder();
        for(String s : binary) {
            output.append(s).append(" ");
        }
        return output.toString();
    }

    public static String toBinary(int binaryInt){
        return Integer.toBinaryString(binaryInt);
    }

    private static int binaryToDecimal(String binaryString) {
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