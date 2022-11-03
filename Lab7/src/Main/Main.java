package Main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int correct = 0;
        String[] q2Options = {"A", "B", "C", "D"};
        JOptionPane.showMessageDialog(null, "Hey there!");
        int q1 = JOptionPane.showConfirmDialog(null, "Do you program?");
        if(q1 == JOptionPane.YES_OPTION) {
            correct++;
        }
        String q2 = String.valueOf(JOptionPane.showInputDialog(null, "<Question goes here>", "Question 2", JOptionPane.QUESTION_MESSAGE, null, q2Options, q2Options[0]));
        if(q2.equalsIgnoreCase(q2Options[1])) {
            correct++;
        }
        String q3 = JOptionPane.showInputDialog("<Question goes here>");
        if(q3.equalsIgnoreCase("f")) {
            correct++;
        }
        JTextField q4Answer = new JTextField(10);
        q4Answer.setScrollOffset(JTextField.WHEN_IN_FOCUSED_WINDOW);
        JOptionPane.showInputDialog(q4Answer, "<question 4>");
        JOptionPane.showMessageDialog(null, "You got a " + correct + " / 3 (" + correct * 100 / 3 + "%) and 1 pending answer");
    }
}