package Homework4;

/*
Implement the class Letter and print this letter:
Dear John:

I am sorry we must part.
I wish you all the best.

Sincerely,

Mary
*/

public class Q7 {
    public static void main(String[] args) {
        Letter letter = new Letter("Mary", "John");
        letter.addLine("I am sorry we must part.");
        letter.addLine("I wish you all the best.");
        System.out.println(letter.getText());
    }
}