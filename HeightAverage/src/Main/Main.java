package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JPanel {
    private static ArrayList<Integer> feet, inches;
    private static JLabel avgHeight;
    private static JTextField theFeet, theInches;

    public Main() {
        setLayout(new FlowLayout());
        add(new JLabel("Feet:"));
        add(theFeet = new JTextField(10));
        add(new JLabel("Inches:"));
        add(theInches = new JTextField(10));
        JButton enter, undo;
        add(enter = new JButton("Enter"));
        enter.addActionListener(new EnterInfo());
        add(undo = new JButton("Undo"));
        undo.addActionListener(new Undo());
        add(avgHeight = new JLabel("Average height: "));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Pitch Charting");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(185, 150);
        frame.setLocation((screenSize.width / 2) - (frame.getWidth() / 2),(screenSize.height / 2) - (frame.getHeight() / 2));
        frame.setContentPane(new Main());
        frame.setAlwaysOnTop(true);
        frame.setVisible(true);
        feet = new ArrayList<>();
        inches = new ArrayList<>();
    }

    private static class EnterInfo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(!(theFeet.getText().isBlank() && theInches.getText().isBlank())) {
                if(!theFeet.getText().isBlank() && isInteger(theFeet.getText())) {
                    feet.add(Integer.parseInt(theFeet.getText()));
                } else {
                    feet.add(0);
                }
                if(!theInches.getText().isBlank() && isInteger(theInches.getText())) {
                    inches.add(Integer.parseInt(theInches.getText()));
                } else {
                    inches.add(0);
                }
                theFeet.setText("");
                theInches.setText("");
                updateAvgHeight();
            }
        }
    }

    private static class Undo implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            theFeet.setText(String.valueOf(feet.get(feet.size() - 1)));
            theInches.setText(String.valueOf(inches.get(inches.size() - 1)));
            theFeet.remove(feet.size() - 1);
            theInches.remove(inches.size() - 1);
            updateAvgHeight();
        }
    }

    private static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void updateAvgHeight() {
        int totalInches = 0;
        for(int i : feet) {
            totalInches += (i * 12);
        }
        for(int i : inches) {
            totalInches += i;
        }
        int avgFeet = (totalInches / 12) / feet.size();
        double avgInches = ((double) totalInches / inches.size()) % 12;
        avgHeight.setText("Average height: " + avgFeet + "'" + formatNum(avgInches) + "\"");
    }

    private static String formatNum(double d) {
        switch(String.valueOf(d).indexOf(".")) {
            case 1 -> {
                if(String.valueOf(d).length() >= 4) {
                    return String.valueOf(d + 0.005).substring(0, 4);
                }
            }
            case 2 -> {
                if(String.valueOf(d).length() >= 5) {
                    return String.valueOf(d + 0.05).substring(0, 4);
                }
            }
        }
        return String.valueOf(d);
    }
}