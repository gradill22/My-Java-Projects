package GPACalculatorGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GPACalculatorGUI extends JPanel implements KeyListener {
    static JFrame frame;
    JTextField grade, boost;
    JLabel gradeLabel, boostLabel, weighted, unweighted, classes;
    JButton enter, undo;

    ArrayList<Double> grades, boosts;
    static int i = 0;

    public GPACalculatorGUI() {
        add(gradeLabel = new JLabel("Grade:"));
        add(grade = new JTextField(9));
        grade.addKeyListener(this);
        add(boostLabel = new JLabel("Boost:"));
        add(boost = new JTextField(9));
        boost.addKeyListener(this);
        add(classes = new JLabel("Classes added: " + i));
        add(enter = new JButton("   Enter   "));
        enter.addActionListener(new EnterListener());
        enter.addKeyListener(this);
        add(undo = new JButton("   Undo   "));
        undo.addActionListener(new UndoListener());
        undo.addKeyListener(this);
        add(weighted = new JLabel());
        add(unweighted = new JLabel());
        grades = new ArrayList<>();
        boosts = new ArrayList<>();
    }

    public static void main(String[] args) {
        frame = new JFrame("GPA Calculator");
        frame.setSize(200, 150);
        frame.setLocation(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GPACalculatorGUI());
        frame.setResizable(false);
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
    }

    private class EnterListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!grade.getText().isEmpty()){
                grades.add(getGPA(Double.parseDouble(grade.getText())));
                if(!boost.getText().isEmpty()) {
                    boosts.add(Double.parseDouble(boost.getText()));
                } else {
                    boosts.add(0.0);
                }
                i = grades.size();
                double weightedGPA = (sumOfArray(grades) + sumOfArray(boosts)) / i;
                double unweightedGPA = sumOfArray(grades) / i;
                weighted.setText(formString(weightedGPA, "Weighted"));
                unweighted.setText(formString(unweightedGPA, "Unweighted"));
            }
            resetText();
        }
    }

    private class UndoListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(grades.size() > 0) {
                grades.remove(i - 1);
                boosts.remove(i - 1);
                i = grades.size();
                resetText();
            }
            if(grades.size() == 0) {
                frame.setSize(200, 150);
            }
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> enter.doClick();
            case KeyEvent.VK_SHIFT -> undo.doClick();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private static double getGPA(double grade) {
        grade += 0.005;
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
        } else {
            return 0.0;
        }
    }

    private double sumOfArray(ArrayList<Double> arr) {
        double sum = 0;
        for(double i : arr) {
            sum += i;
        }
        return sum;
    }

    private String formString(double d, String s) {
        if(Double.toString(d).length() >= 4) {
            return s + " GPA: " + Double.toString(d).substring(0, 4);
        } else {
            return s + " GPA: " + d;
        }
    }

    private void resetText() {
        classes.setText("Classes added: " + i);
        frame.setSize(200, 190);
        grade.setText("");
        boost.setText("");
    }
}