package Clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class Clock extends JPanel{
    static Timer timer = new Timer(100, new Listener());
    private static JLabel timeLabel;
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm:ss a");

    public Clock() {
        setLayout(new FlowLayout());
        timeLabel = new JLabel();
        add(timeLabel);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setResizable(false);
        frame.setSize(100, 100);
        frame.setLocation(800, 375);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Clock());
        frame.setVisible(true);
        timeLabel.setText(dtf.format(LocalDateTime.now()));
        timer.setRepeats(true);
        timer.start();
    }

    private static class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            timeLabel.setText(dtf.format(LocalDateTime.now()));
        }
    }
}