package HelloNamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelloNamePanel extends JPanel {
    JLabel helloMsg;
    JTextField box;
    JButton button;

    public HelloNamePanel() {
        setLayout(new FlowLayout());

        helloMsg = new JLabel("Type your name, then press Enter");
        add(helloMsg);

        box = new JTextField(20);
        add(box);

        button = new JButton("Enter");
        add(button);
        button.addActionListener(new Listener());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Name");
        frame.setSize(250, 100);
        frame.setLocation(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new HelloNamePanel());
        frame.setVisible(true);
    }

    private class Listener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) {
            if(box.getText().isEmpty()) {
                helloMsg.setText("Please enter your name");
            }
            else {
                helloMsg.setText("Hello " + box.getText());
            }
            box.setText("");
        }
    }
}