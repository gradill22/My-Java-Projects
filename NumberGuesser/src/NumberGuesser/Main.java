package NumberGuesser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static NumberGuesser.CPU.cpuWins;

public class Main extends JPanel implements KeyListener {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static JFrame frame, frameCPU, scoreboard;
    private static JLabel label;
    private static JTextField guess;
    private static JButton enter, quit;
    private static JCheckBox cpu;
    public static final int min = 1;
    public static final int max = 100;
    public static int r = (int) ((Math.random() * (max - min)) + min);
    private static int guesses = 0;
    private static int lowGuess = min;
    private static int highGuess = max;
    public static boolean playerWins, playCPU = false;
    public static final ArrayList<Guess> attempts = new ArrayList<>();

    public Main() {
        add(label = new JLabel("Welcome to Number Guesser! Guess a number between " + min + " and " + max + "!"));
        add(guess = new JTextField(10));
        guess.addKeyListener(this);
        add(enter = new JButton("Guess"));
        enter.addActionListener(new Enter());
        add(quit = new JButton("Quit"));
        quit.setVisible(false);
        quit.addActionListener(new Quit());
        add(cpu = new JCheckBox("Play CPU"));
        cpu.addActionListener(new CPUListener());
    }

    public static void main(String[] args) {
        frame = new JFrame("Number Guesser");
        frame.setContentPane(new Main());
        frame.setSize(410, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setAlwaysOnTop(true);
        frameCPU = new JFrame("Number Guesser CPU");
        frameCPU.setContentPane(new CPU());
        frameCPU.setSize(410, 100);
        frameCPU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameCPU.setAlwaysOnTop(true);
        frame.setVisible(true);
        if(playCPU) {
            frame.setLocation((int) (screenSize.getWidth() / 4) - (frame.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frame.getHeight() / 2));
            frameCPU.setLocation((int) (screenSize.getWidth() * 3 / 4) - (frameCPU.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frameCPU.getHeight() / 2));
            frameCPU.setVisible(true);
        } else {
            frame.setLocation((int) (screenSize.getWidth() / 2) - (frame.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frame.getHeight() / 2));
            frameCPU.setVisible(false);
        }
    }

    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER -> enter.doClick();
            case KeyEvent.VK_ESCAPE -> quit.doClick();
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    private static class Enter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if(isInteger(guess.getText())) {
                int g = Integer.parseInt(guess.getText());
                if(g >= lowGuess && g <= highGuess) {
                    if(playCPU) {
                        CPU.parseGuess();
                    }
                    guesses++;
                    if(g < r) {
                        if(g > lowGuess) {
                            lowGuess = g;
                        }
                        label.setText("Higher, between " + lowGuess + " and " + highGuess + "...");
                        cpu.setVisible(false);
                    } else if(g > r) {
                        if(g < highGuess) {
                            highGuess = g;
                        }
                        label.setText("Lower, between " + lowGuess + " and " + highGuess + "...");
                        cpu.setVisible(false);
                    } else {
                        label.setText("You got " + r + " in " + guesses + " tries! Guess another number between " + min + " and " + max + "!");
                        attempts.add(new Guess(guesses, playCPU));
                        guesses = 0;
                        if(!cpuWins && playCPU) {
                            playerWins = true;
                            CPU.parseGuess();
                        }
                        cpuWins = false;
                        r = (int) ((Math.random() * (max - min)) + min);
                        quit.setVisible(true);
                        lowGuess = min;
                        highGuess = max;
                        cpu.setVisible(true);
                    }
                    guess.setText("");
                }
            }
        }
    }

    private static class Quit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            scoreboard = new JFrame("Scoreboard");
            scoreboard.setLayout(new FlowLayout());
            boolean playedCPU = CPU.attempts.size() > 0;
            if(playedCPU) {
                scoreboard.add(new JLabel("Player: " + Guess.playerWins() + "-" + Guess.cpuWins() + "-" + Guess.ties()));
            }
            scoreboard.add(new JLabel("Player avg tries: " + Guess.playerTriesAvg()));
            if(playedCPU) {
                scoreboard.add(new JLabel("CPU: " + Guess.cpuWins() + "-" + Guess.playerWins() + "-" + Guess.ties()));
                scoreboard.add(new JLabel("CPU avg tries: " + Guess.cpuTriesAvg()));
            }
            JButton playAgain = new JButton("Play again");
            scoreboard.add(playAgain);
            playAgain.addActionListener(new PlayAgain());
            scoreboard.setSize(100, 120);
            scoreboard.setLocation((int) (screenSize.getWidth() / 2) - (scoreboard.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (scoreboard.getHeight() / 2));
            scoreboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(false);
            frameCPU.setVisible(false);
            scoreboard.setVisible(true);
        }
    }

    private static class CPUListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            playCPU = !playCPU;
            if(playCPU) {
                frame.setLocation((int) (screenSize.getWidth() / 4) - (frame.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frame.getHeight() / 2));
                frameCPU.setLocation((int) (screenSize.getWidth() * 3 / 4) - (frameCPU.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frameCPU.getHeight() / 2));
                frameCPU.setVisible(true);
            } else {
                frame.setLocation((int) (screenSize.getWidth() / 2) - (frame.getWidth() / 2), (int) (screenSize.getHeight() / 2) - (frame.getHeight() / 2));
                frameCPU.setVisible(false);
            }
        }
    }

    private static class PlayAgain implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            main(new String[0]);
            if (attempts.size() > 0) {
                attempts.subList(0, attempts.size()).clear();
            }
            if (CPU.attempts.size() > 0) {
                CPU.attempts.subList(0, CPU.attempts.size()).clear();
            }
            scoreboard.setVisible(false);
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
}