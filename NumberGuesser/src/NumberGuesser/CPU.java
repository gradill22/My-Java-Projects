package NumberGuesser;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class CPU extends JPanel {
    private static JLabel label;
    public static JTextField guess;
    public static JButton enter;
    private static final int min = Main.min;
    private static final int max = Main.max;
    private static int guesses = 0;
    public static int lastR = -1;
    public static int lowGuess = min;
    public static int highGuess = max;
    public static boolean cpuWins = false;
    public static final ArrayList<Integer> attempts = new ArrayList<>();

    public CPU() {
        add(label = new JLabel("Welcome to Number Guesser! Guess a number between " + min + " and " + max + "!"));
        add(guess = new JTextField(10));
        guess.setVisible(false);
        enter = new JButton();
        enter.addActionListener(new Enter());
    }

    public static void parseGuess() {
        if(lastR != Main.r) {
            guess.setText(String.valueOf((lowGuess + highGuess) / 2));
            enter.doClick();
        }
    }

    private static class Enter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int g = Integer.parseInt(guess.getText());
            if(Main.playerWins) {
                while(lastR != Main.r) {
                    guesses++;
                    g = (lowGuess + highGuess) / 2;
                    if(g < Main.r) {
                        if(g > lowGuess) {
                            lowGuess = g;
                        }
                        label.setText("Guess: " + guesses);
                    } else if(g > Main.r) {
                        if(g < highGuess) {
                            highGuess = g;
                        }
                        label.setText("Guess: " + guesses);
                    } else {
                        label.setText("You got it in " + guesses + " tries! Guess another number between " + min + " and " + max + "!");
                        attempts.add(guesses);
                        guesses = 0;
                        lowGuess = min;
                        highGuess = max;
                        lastR = Main.r;
                    }
                }
                Main.playerWins = false;
            } else {
                guesses++;
                if(g < Main.r) {
                    if(g > lowGuess) {
                        lowGuess = g;
                    }
                    label.setText("Guess: " + guesses);
                } else if(g > Main.r) {
                    if(g < highGuess) {
                        highGuess = g;
                    }
                    label.setText("Guess: " + guesses);
                } else {
                    label.setText("You got it in " + guesses + " tries! Guess another number between " + min + " and " + max + "!");
                    attempts.add(guesses);
                    guesses = 0;
                    lowGuess = min;
                    highGuess = max;
                    lastR = Main.r;
                    cpuWins = true;
                }
            }
            guess.setText("");
        }
    }
}